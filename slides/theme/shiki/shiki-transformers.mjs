const ITALIC = 1
const KEYWORD_BLUE = '#0033B3'

const VALUE = /\bvalue(?=\s+(?:class|record)\b)/g
const STATIC_CALL = /(?<=\b[A-Z][\w$]*\.)[a-z][\w$]*(?=\()/g

function restyle(line, pattern, change) {
  const text = line.map((t) => t.content).join('')
  const hits = [...text.matchAll(pattern)].map((m) => [m.index, m.index + m[0].length])
  if (!hits.length) return line
  const chars = line.flatMap((t) => t.content.split('').map((c) => ({ c, color: t.color, fontStyle: t.fontStyle ?? 0 })))
  for (const [from, to] of hits) for (let i = from; i < to; i++) change(chars[i])
  const out = []
  chars.forEach((ch, i) => {
    const last = out[out.length - 1]
    if (last && last.color === ch.color && last.fontStyle === ch.fontStyle) last.content += ch.c
    else out.push({ content: ch.c, offset: line[0].offset + i, color: ch.color, fontStyle: ch.fontStyle })
  })
  return out
}

function javaOnly(name, pattern, change) {
  return {
    name,
    tokens(lines) {
      if (this.options.lang !== 'java') return
      return lines.map((line) => (line.length ? restyle(line, pattern, change) : line))
    },
  }
}

export const staticCalls = javaOnly('hc-static-calls', STATIC_CALL, (ch) => {
  ch.fontStyle |= ITALIC
})

export const valueKeyword = javaOnly('hc-value-keyword', VALUE, (ch) => {
  ch.color = KEYWORD_BLUE
})

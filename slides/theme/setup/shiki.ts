import { defineShikiSetup } from '@slidev/types'
import { intellijLight } from '../shiki/shiki-intellij-light.mjs'
import { staticCalls, valueKeyword } from '../shiki/shiki-transformers.mjs'

export default defineShikiSetup(() => ({
  theme: intellijLight as any,
  transformers: [staticCalls as any, valueKeyword as any],
}))

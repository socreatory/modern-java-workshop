import { defineAppSetup } from '@slidev/types'

export default defineAppSetup(() => {
  if (typeof window === 'undefined') return
  const root = document.documentElement
  const update = () => root.classList.toggle('hc-print', new URLSearchParams(location.search).has('print'))
  update()
  window.addEventListener('popstate', update)
})

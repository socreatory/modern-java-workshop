<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'

const probe = ref<HTMLElement>()

const SLACK = 1

let observer: ResizeObserver | undefined
let waiting: MutationObserver | undefined
let refit: (() => void) | undefined
let busy = false

function top(el: HTMLElement) {
  let y = 0
  for (let e: HTMLElement | null = el; e; e = e.offsetParent as HTMLElement | null) y += e.offsetTop
  return y
}

function over(body: HTMLElement) {
  const limit = top(body) + body.offsetHeight - Number.parseFloat(getComputedStyle(body).paddingBottom)
  let lowest = top(body)
  for (const el of body.querySelectorAll('*')) {
    if (!(el instanceof HTMLElement) || !el.offsetParent) continue
    lowest = Math.max(lowest, top(el) + el.offsetHeight)
  }
  return lowest - limit
}

function check(body: HTMLElement) {
  if (busy || body.offsetHeight === 0) return
  busy = true
  body.classList.remove('hc-snug')
  if (over(body) > SLACK) body.classList.add('hc-snug')
  busy = false
}

onMounted(async () => {
  const page = probe.value?.closest('.slidev-page')
  if (!page) return
  await document.fonts?.ready

  if (!page.querySelector('.slidev-layout')) {
    await new Promise<void>((resolve) => {
      waiting = new MutationObserver(() => {
        if (!page.querySelector('.slidev-layout')) return
        waiting?.disconnect()
        resolve()
      })
      waiting.observe(page, { childList: true, subtree: true })
    })
  }
  const body = page.querySelector<HTMLElement>('.hc-body')
  if (!body) return

  check(body)
  observer = new ResizeObserver(() => requestAnimationFrame(() => check(body)))
  observer.observe(body)
  refit = () => check(body)
  document.fonts?.addEventListener('loadingdone', refit)
})

onUnmounted(() => {
  waiting?.disconnect()
  observer?.disconnect()
  if (refit) document.fonts?.removeEventListener('loadingdone', refit)
})
</script>

<template>
  <div ref="probe" class="hc-fit-probe" />
</template>

<style>
.hc-fit-probe {
  display: none;
}
</style>

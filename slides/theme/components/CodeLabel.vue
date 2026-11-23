<script setup lang="ts">
defineProps<{ line: number; row?: number; col: number; pointer?: string }>()
</script>

<template>
  <div class="hc-label hc-label-bare" :style="{ '--l': line, '--c': col }">
    <span class="hc-label-text"><slot /></span>
  </div>
</template>

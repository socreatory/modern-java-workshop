<script setup lang="ts">
import { useSlideContext } from '@slidev/client'

defineProps<{ bare?: boolean }>()

const { $slidev, $page } = useSlideContext()
const training = ($slidev.configs as any).training ?? {}
</script>

<template>
  <footer class="soc-footer">
    <template v-if="!bare">
      <span class="soc-page">{{ $page }}</span>
      <span class="soc-training">{{ training.name }}</span>
    </template>
  </footer>
</template>

<script setup lang="ts">
defineProps<{ eyebrow?: string }>()
</script>

<template>
  <div class="slidev-layout hc-cover soc-cover">
    <SocHeader />
    <div class="hc-cover-body">
      <p v-if="eyebrow" class="hc-eyebrow">{{ eyebrow }}</p>
      <slot />
    </div>
    <SocFooter />
  </div>
</template>

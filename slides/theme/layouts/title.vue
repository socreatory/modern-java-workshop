<script setup lang="ts">
import { useSlideContext } from '@slidev/client'

const { $slidev } = useSlideContext()
const training = ($slidev.configs as any).training ?? {}
const presenter = ($slidev.configs as any).speaker ?? {}
</script>

<template>
  <div class="slidev-layout soc-title">
    <SocHeader />
    <div class="soc-title-body">
      <h1>{{ training.name }}</h1>
      <p v-if="training.subtitle" class="soc-title-sub">{{ training.subtitle }}</p>
      <div v-if="presenter.name" class="soc-presenter">
        <p class="soc-presenter-name">{{ presenter.name }}</p>
        <p v-if="presenter.role">{{ presenter.role }}</p>
        <p v-if="presenter.org || presenter.date">{{ [presenter.org, presenter.date].filter(Boolean).join(' · ') }}</p>
      </div>
      <slot />
    </div>
    <SocFooter bare />
  </div>
</template>

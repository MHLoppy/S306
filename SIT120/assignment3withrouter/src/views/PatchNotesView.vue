<script setup>
import ThePatchNotesList from "../PatchNotes/components/ThePatchNotesList.vue";
</script>

<template>
  <main class="container">
    <ThePatchNotesList />
  </main>
</template>

<style scoped>
  .container{
    margin-top: var(--header-gap);
  }
</style>
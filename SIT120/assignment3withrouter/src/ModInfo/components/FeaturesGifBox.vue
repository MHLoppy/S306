<!-- this entire component exists in its current state for legacy reasons: it should be ported to use mp4/webm instead of gif -->

<script setup>

defineProps({
    gifLink: {
        type: String,
        required: true,
    },
    pngLink: {
        type: String,
        required: true,
    },
});
</script>
    
<template>
    <div class="container">
        <!-- auto-completing the path saves a bit of effort from typing the full one each time -->
        <div>
            <img :src="gifLink">
        </div>
        <div class="spacer"> </div>
        <div>
            <img :src="pngLink">
        </div>
    </div>
</template>

<style scoped>
  img {
    filter: brightness(85%);
  }

  .container {
    max-width: 200px;
    display: flex;
    flex-direction: column;
  }

  .spacer {
    margin-top: 4px;
  }
</style>
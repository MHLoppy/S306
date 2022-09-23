<script setup>
import FooterLinks from "./FooterLinks.vue";

defineProps({
  msg: {
    type: String,
    required: true,
  },
});
</script>

<template>
  <div class="links">
    <FooterLinks />
  </div>
  <div class="disclaimer">
    {{ msg }}
  </div>

</template>

<style scoped>

.links {
  margin-top: calc(50px);
}

.disclaimer {
  margin-top: 50px;
  bottom: 3px;
}

@media (min-width: 720px) {
  .disclaimer{
    font-size: 11px;
  }
}

@media (min-width: 1024px) {
  .disclaimer{
    font-size: 12px;
  }
}

@media (min-width: 1260px) {

  .links{
  margin-top: calc(70px);
}

  .disclaimer{
    font-size: 13px;

    margin-top: 70px;
    bottom: 3px;
  }
}
</style>

<script setup>
  import {RouterLink} from "vue-router";
</script>

<template>
    <div class="navBar">
        <!-- hover to access mod info from Features? -->
        <RouterLink id="home" to="/">Home</RouterLink>
        <!-- <span class="dropdown">Features</span> -->
        <RouterLink id="features" to="/features">Features</RouterLink>
        <RouterLink id="installguide" to="/install-guide">Installation Guide</RouterLink>
        <RouterLink id="patchnotes" to="/patch-notes">Patch Notes</RouterLink>
        <RouterLink id="credits" to="/credits">Credits</RouterLink>
        <RouterLink id="howtohelp" to="/how-to-help">How To Help</RouterLink>
        <!-- Search? -->
    </div>
</template>

<style scoped>
  /* .dropdown{
    color: #E5D58E;
    padding-top: 1px;
    padding-bottom: 1px;
    padding-left: 14px;
    padding-right: 14px;
  } */

  a.router-link-exact-active {
    color: var(--color-text);
  }

  /* if you're currently on the page, modify the text color (to be not the normal link color) */
  a.router-link-exact-active:hover {
    /* the normal text color, but with some transparency added */
    background-color: rgba(211, 211, 211, 0.17);
  }

  a{
    display: inline-block;
    padding: 0 1rem;
    border-left: 1px solid var(--color-border);
    text-decoration: none;
  }

  a:first-of-type {
    border: 0;
  }
  
  @media (hover: hover) {

  /* .dropdown:hover {
      background-color: rgba(229, 213, 142, 0.17)
    } */
  }

  /* on touchscreen devices, make some inelegant changes to make up for not having delicious vegan hamburgers */
  @media (pointer: coarse) {
    #features, #installguide, #patchnotes, #contributors, #howtohelp{
      visibility: hidden;
    }

    /* tried to unhide the active page, but even when working it doesn't have the intended result because original spacing is retained */
    /* a.router-link-exact-active {
      visibility: visible;
    } */
}

</style>
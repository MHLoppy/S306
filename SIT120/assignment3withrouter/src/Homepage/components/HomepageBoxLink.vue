<script setup>
  import {RouterLink} from "vue-router";

    defineProps({
      msg: {
        type: String,
        required: true,
      },
      link: {
        type: String,
        required: true,
      },
    });
</script>

<template>
    <div class="box">
      <!-- because interpolation isn't supported in attributes, we have to do this for the links -->
        <RouterLink :to="link">{{msg}}</RouterLink>
    </div>
</template>

<style scoped>
  .box {
    width: 230px;
    margin-top: 5px;
    margin-left: 40px;
    margin-right: 40px;
    padding: 4px;

    /* border color is coming from "color" not "border-color" ?? */
    /* and border width is coming from... not border-width */
    border-radius: 3px;
    /* border-width: 1px; */
    border: solid;

    /* even though border-width doesn't work, these do */
    border-top-width: 2px;
    border-right-width: 2px;
    border-bottom-width: 2px;
    border-left-width: 2px;

    /* background-color: rgb(133, 78, 15); */
    background-color: var(--color-brown);
    color:rgb(61, 42, 6);
    font-weight: 600;
    transition: 0.5s ease;
  }

  a{
    text-decoration: none;
    font-size: 20px;
    font-weight: 600;

    /* color:rgb(165, 117, 62) */
    /* color: dimgray; */
    /* filter: brightness(85%); */

    color: #F2CA8A;
  }

  @media (hover: hover) {
  /* prevent weird hover highlighting behaviour on the images-that-are-links */
  a:hover {
    background-color: var(--color-brown);
  }

  /* instead, do a highlight effect + size increase */
  .box:hover {
    filter: brightness(120%);
    transform: scale(103%);
    transition: ease-out;
  }
}
</style>
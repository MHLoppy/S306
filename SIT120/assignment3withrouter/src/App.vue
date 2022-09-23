<script setup>
import {RouterLink, RouterView} from "vue-router";
import TheNavbar                from "./Navigation/components/TheNavbar.vue";
import TheFooter                from "./Footer/components/TheFooter.vue";
import TheHamburger             from "./Navigation/components/TheHamburger.vue";
</script>

<template>
  <div class="pageContainer">
    <header>
      <nav class="navbar">
        <TheNavbar />
      </nav>
    </header>

    <!-- for reasons I don't understand, a blank section gets rendered between the navbar and the app on subsequent (SPA/routerview) loads of the home page -->

    <body id="primary">
      <!-- have a simple fade transition between pages -->
      <RouterView v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component"/>
        </transition>
      </RouterView>
    </body>

    <footer>
      <TheFooter
        msg="Not endorsed by or affiliated with Microsoft, Xbox Game Studios, Skybox Labs, or Big Huge Games." />
    </footer>
  </div>

</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  /* make the transition pretty snappy */
  transition: opacity 0.25s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

  /* god bless you https://stackoverflow.com/a/45762747/16367940 */
.pageContainer{
  display: flex;
  position: relative;
  flex-direction: column;
  min-height: 100vh;
  overflow-x: hidden; /* Hide horizontal scrollbar */
}

/* header {
  line-height: 1.5;
  max-height: 20vh;
} */

/* would prefer to figure out how to style more of this in TheNavbar itself, but don't have time */
nav {
  /* put it on the top and use z-index to make sure it renders on top of everything else */
  position:fixed;
  left:0;
  top:0;
  z-index:100;

  display: flex;
  justify-content: center;
  width: 100vw;
  height: 54px;
  font-size: 16px;
  text-align: center;
  
  padding-top: 4px;

  background-color: rgba(8,8,14, 0.9);

  border-bottom: 1px solid rgba(229, 213, 142, 0.35);
}

/* nav .hamburger{
  position:fixed;
  left:0;
  top:0;
} */

/* this is needed to keep the footer at the bottom even on pages that are shorter than screen height */
#primary {
  flex: 1;
}

footer{
  width: 100vw;
  max-width: 1264px;/* very janky to manually override like this, but I couldn't figure out what's causing the padding/margins on its left side */

  position: relative;
  text-align: center;
  
  color:dimgray;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

/* already handled as the default case */
/* @media (min-width: 540px){
  nav{
    height: 54px;
    font-size: 16px;
  }
} */

/* inelegant: manually increase the height of the navbar to accomodate stacked text */
@media (min-width: 780px) {
  nav{
    height:32px;
  }

  /* #primary{
    margin-top: 32px;
  } */
}

@media (min-width: 1024px) {
  /* header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  } */

  /* header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  } */

  /* nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem; 
  }*/
}

@media (min-width: 1300px) {
  .pageContainer{
    overflow-x: visible; /* Unhide horizontal scrollbar */
  }
}

/* on touchscreen devices, make some inelegant changes to make up for not having delicious vegan hamburgers */
@media (pointer: coarse) {
  nav{
    height: 48px;
    font-size: 24px;
  }

  /* #primary{
    margin-top: 48px;
  } */
}
</style>
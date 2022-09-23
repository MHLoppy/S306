<script setup>
  import HomepageBoxLink   from "./HomepageBoxLink.vue";
</script>

<template>
  <div class="banner">
    <!-- it's low-tech, but using breaks instead of margin ensures we can change a larger area's background without resorting to +height with padding -->
    <br/><br/><br/>

    <h1>Community Balance/Bugfix Patch (CBP)</h1>
    <img src="../assets/RoN_CBP_logo_01_388px.png" id="logo">
    <p>
      Available  for the Steam version of Rise of Nations: Extended Edition, CBP aims to be the 
      best version of the game that you already know and love. It's not a whole new game which you'll 
      have to re-learn – it's the same great gameplay that's existed for nearly two decades.
    </p>
    <br/><br/>
    <div class="topBoxes">
      <HomepageBoxLink msg="INSTALLATION GUIDE" link="/install-guide"/>
      <HomepageBoxLink msg="PATCH NOTES" link="/patch-notes"/>
    </div>
  </div>
</template>

<style scoped>

  .banner{
    padding-left: calc(5vw - 10px);
    padding-right: calc(5vw - 10px);
    text-align: center;

    /* radial gradient background behind the banner area, with the midpoint set above the middle value to reduce banding near the edges */
    background: radial-gradient(farthest-side at center, #2B2D38, #23252D, #14151A);
  }

  h1{
    font-size: 1.8rem;
    font-weight: 600;
    margin-bottom: 0.4rem;
    color: var(--color-heading);
  }

  *{
    font-size: 1.1rem;
    color: #D6D6D6;
  }

  /* resize this dynamically based on screen size */
  #logo{
    width: calc(150px + 10vw);
  }

  .topBoxes{
    display: flex;
    justify-content: center;
    margin-bottom: calc(100px);
  }

  @media (min-width: 540px) {
    .banner{
      padding-left: calc(5vw);
      padding-right: calc(5vw);
    }
  }

  @media (min-width: 760px) {
    .banner{
      padding-left: calc(12vw + 40px);
      padding-right: calc(12vw + 40px);
    }

    h1{
      font-size: 2.0rem;
    }
  }

  @media (min-width: 1024px) {
    .banner{
      padding-left: calc(12vw + 100px);
      padding-right: calc(12vw + 100px);
    }

    *{
    font-size: 1.2rem;
  }
  }

  /* we don't want 1080p phones to trigger the extra size */
  @media (min-width: 1100px) {
    h1{
      font-size: 2.2rem;
    }
  }

  @media (min-width: 1280px) {
    .banner{
      padding-left: calc(15vw + 100px);
      padding-right: calc(15vw + 100px);
    }
  }

  @media (min-width: 1600px) {
    .banner{
      padding-left: calc(17vw);
      padding-right: calc(17vw);
    }

    h1{
      font-size: 2.4rem;
    }
  }
</style>

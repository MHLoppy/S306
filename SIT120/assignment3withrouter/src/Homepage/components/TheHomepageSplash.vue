<!-- this thing is ultra scuffed because the app has a maximum width (making it near-impossible to have a nicely aligned full-page splash image)
     I've partly worked around this with the -<X>px of splash's width etc, and then also done some horizontal-scrollbar-hiding in app.vue
     this covers MOST users and makes it look like I know what I'm doing, but there are a few edge cases where the cracks can be seen -->

<template>
  <div class="splash">
    <img src="../assets/RoN_Background_Romans_v3.jpg">
    <div class="splashText">
    RoN:EE is broken.<br/>Let's fix it.
    </div>
    <div class="splashTextShadow1">
    RoN:EE is broken.<br/>Let's fix it.
    </div>
    <div class="splashTextShadow2">
    RoN:EE is broken.<br/>Let's fix it.
    </div>
    <div class="arrowOne">
      <i class="iArrowOne"></i>
    </div>
    <div class="arrowTwo">
      <i class="iArrowTwo"></i>
    </div>
  </div>
</template>

<style scoped>
  html, body {
    padding: 0;
    margin: 0;
    height: 100%;
  }

  .splash{
    width: calc(100vw - 20px);
    height:100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .splash img {
    width: 100vw;
    height: 100vh;
    object-fit: cover;
    filter:brightness(70%)
  }

  .splashText, .splashTextShadow1, .splashTextShadow2{
    font-size: 24px;
    font-weight: 700;
    text-align: center;
    line-height: 1.2;

    
    /* https://www.w3schools.com/howto/howto_css_image_text.asp */
    position: absolute;
    top: 48%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  
  .splashText{
    color: #F0F0F0;
    z-index: 3;
  }

  /* normal text-shadow doesn't work because we're using position: absolute, so we're doing homebrew text shadows using the blur filter */
  .splashTextShadow1{
    color: black;
    z-index: 2;
    filter:blur(2px);
  }

  .splashTextShadow2{
    color: black;
    z-index: 1;
    filter:blur(4px);
  }

  /* https://www.w3schools.com/howto/howto_css_arrows.asp 
     very difficult to use media queries to resize the arrows well, because this is a case where you have
     to deal with normal desktop / mobile device resolutions but also ultrawide monitors - using no queries 
     and fixed height-from-bottom might be the best balance here without being anal */
  .arrowOne{
    border: solid #F0F0F0;
    border-width: 0 6px 6px 0;
    display: inline-block;
    padding: 15px;

    left: calc(50vw - 15px);
    transform: rotate(45deg);

    position:absolute;
    bottom: calc(20vh - 40px);
  }

  .arrowTwo{
    border: solid #F0F0F0;
    border-width: 0 6px 6px 0;
    display: inline-block;
    padding: 15px;

    left: calc(50vw - 15px);
    transform: rotate(45deg);

    /* second arrow is 20px lower */
    position:absolute;
    bottom: calc(20vh - 60px);
  }

  @media (min-width: 560px) {
    .splashText, .splashTextShadow1, .splashTextShadow2{
      font-size: 32px;
    }

    .splashTextShadow1{
      filter:blur(2px);
    }

    .splashTextShadow2{
      filter:blur(4px);
    }
  }

  @media (min-width: 900px) {
    .splashText, .splashTextShadow1, .splashTextShadow2{
      font-size: 52px;
    }

    .splashTextShadow1{
      filter:blur(4px);
    }

    .splashTextShadow2{
      filter:blur(8px);
    }
  }

  @media (min-width: 1024px) {
  /* scuffed way of getting the image aligned */
    .splash{
      width: calc(100vw - 3px);
      margin-left: -10px;
      margin-right: -10px;
    }
  }

  @media (min-width: 1120px) {
    .splashText, .splashTextShadow1, .splashTextShadow2{
      font-size: 64px;
    }

    .splashTextShadow1{
      filter:blur(5px);
    }

    .splashTextShadow2{
      filter:blur(10px);
    }
  }

  @media (min-width: 1300px) {
    .splash{
      width: calc(100vw - 10px);
      /* --sides: calc(var(100vw - 1300px));
      --halfSides: calc(var(--sides) / 2);
      --negHalfSides: calc(var(--halfSides) * -1);
      margin-left: var(--negHalfSides); */
      margin-left: calc((100vw - 1280px)/-2);
    }

    .splashText, .splashTextShadow1, .splashTextShadow2{
      font-size: 72px;
    }

    .splashTextShadow1{
      filter:blur(6px);
    }

    .splashTextShadow2{
      filter:blur(12px);
    }
  }

  
  @media (min-width: 2000px) {
    .splashText, .splashTextShadow1, .splashTextShadow2{
      font-size: 84px;
    }

    .splashTextShadow1{
      filter:blur(7px);
    }

    .splashTextShadow2{
      filter:blur(14px);
    }
  }
</style>

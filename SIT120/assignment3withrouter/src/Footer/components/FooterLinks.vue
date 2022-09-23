<template>
  <div class="footerLinks">
    <a target="_blank" href="https://steamcommunity.com/sharedfiles/filedetails/discussions/2287791153" rel="noopener">
      <img class="main" src="../assets/Steam_logo_256px.png" title="Steam discussion forum for CBP">
      <!-- <img class="glow" src="../assets/Steam logo 256px.png" title="Steam discussion forum for CBP"> -->
    </a>
    <a target="_blank" href="https://discord.gg/wh7YWJgjwf" rel="noopener">
      <img class="main" src="../assets/Discord-Logo-White.png" title="The unofficial RoN Discord server">
      <!-- <img class="glow" src="../assets/Discord-Logo-White.png" title="The unofficial RoN Discord server"> -->
    </a>
  </div>
  <div class="descriptions">
    <div class="descOne">Steam forums</div>
    <div class="descTwo">RoN Discord</div>
  </div>
</template>

<style scoped>
.footerLinks a{
  margin-left: 20px;
  margin-right: 20px;
}

img {
  max-width: 64px;
  max-height: 64px;

  /* fancify the highlight effect */
  transition: .5s ease;

  /* this both reduces page contrast and allows us to do a brighten (instead of darken) effect on-hover */
  filter: brightness(75%);
}

/* simulated glow effect that supports transparency https://stackoverflow.com/a/41900257/16367940 */
/* img.main{
  z-index: 2;
}

img.glow{
  z-index: 1;
  transform: scale(1.01) translate(-50%, -50%);
  filter: brightness(0) invert(1) blur(5px);
} */

.descriptions{
  display:flex;
  justify-content: center;
  color: gray;
}

.descriptions .descOne, .descTwo{
  margin-left:20px;
  margin-right:20px;
  text-align: center;
  
  font-size: 14px;
  font-weight: 600;
  line-height: 1.1rem;
}

.descriptions .descOne{
  width: 64px;
}

.descriptions .descTwo{
  width: 56.8px;
}

@media (hover: hover) {
  /* prevent weird hover highlighting behaviour on the images-that-are-links */
  a:hover {
    background-color: #14151A;
  }

  /* instead, do a highlight effect + size increase */
  img:hover {
    filter: brightness(120%);
    transform: scale(115%);
  }
}

@media (min-width: 720px) {
  .footerLinks{
    font-size: 12px;
  }
}

@media (min-width: 1024px) {
  .footerLinks{
    font-size: 13px;
  }
}

@media (min-width: 1260px) {
  .footerLinks{
    font-size: 14px;
  }
}
</style>

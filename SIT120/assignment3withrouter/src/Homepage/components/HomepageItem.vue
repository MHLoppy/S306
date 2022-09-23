<template>
  <div class="item">
    <div class="mediaDiv">
      <span class="nestedMediaDiv"></span>
      <slot name="media"></slot>
    </div>
    <div class="details">
      <h3>
        <slot name="heading"></slot>
      </h3>
      <slot></slot>
    </div>
  </div>
</template>

<style scoped>
.item {
  margin-top: 2rem;
  margin-bottom: 2rem;
  display: flex;
  flex-direction: column;
  max-width: 1020px;
}

.mediaDiv {
  display: flex;
  place-content: center;
  object-fit: contain;
  width: 100%;
  height: 96px;
}

 /* https://stackoverflow.com/a/7310398 tried to get vertical alignment, but didn't quite get it working - still looks okay without it though */
.nestedMediaDiv{
  display: inline-block;
  height: 100%;
  vertical-align: middle;
}

img{
  height: 100%;
  vertical-align: middle;
  max-height:128px;
  max-width: 128px;
}

.details {
  flex: 1;
  margin-left: 1rem;
  margin-right: 1rem;
  background: radial-gradient(farthest-side at center, #1D1E26, #14151A);
}

/* at low widths, give up on side-by-side: just stack images and text*/
@media (min-width: 540px) {
  .item {
    flex-direction: column;
  }

  .mediaDiv {
    width: 100%;
    height: 128px;
  }
}

/* once there's enough width, use the preferred side-by-side view */
@media (min-width: 720px) {
  .item {
    flex-direction: row;
    margin-left: 25px;
    margin-right: 25px;
  }

  .mediaDiv {
    width: 15vw;
    height: 15vw;

  /* since vertical alignment failed, just add a little margin to the top when side-by-side*/
    margin-top: 30px;
  }
}

@media (min-width: 850px) {
  .mediaDiv {
    width: 128px;
    height: 128px;
    margin-left: 10px;
    margin-right: 10px;
  }
}

@media (min-width: 1024px) {
  .item {
    margin-top: 0px;
    margin-left: 0px;
    margin-right: 0px;
    padding: 0.4rem 0 1rem calc(10vw - 20px);
  }

  .mediaDiv {
    width: 128px;
    height: 128px;
    margin-right: 20px;
  }
}
</style>

<script setup>
import HomepageItem from "./HomepageItem.vue";
import {RouterLink} from "vue-router";
</script>

<template>
  <HomepageItem>
    <template #media>
      <img src="../assets/Exclamation_mark_icon_v2.png">
    </template>
    <template #heading>Corrected Damage Modifiers</template>

      In RoN:EE, every unit and building in the game is affected by a
      <RouterLink to="/features#object_masks_bug_workaround">major damage bug</RouterLink> 
      which makes everything deal or receive the wrong amount of damage. This means that <strong>
      EE's balance is bugged</strong> and is quite different from what the original developers 
      intended!
      <br/><br/>
      Fixing this is the solid foundation upon which all of CBP's other balance changes are built 
      on top of. With this fixed, CBP's overall balance is actually much closer to "normal" RoN 
      balance than RoN:EE is.
  </HomepageItem>

  <HomepageItem>
    <template #media>
      <img src="../assets/wrench_by_Freepik.png">
    </template>
    <template #heading>70+ Other Bugfixes (and counting!)</template>

    A fully-patched copy of RoN still includes a lot of both gameplay and cosmetic bugs, so 
    we've tried to 
    <RouterLink to="/features#other_bugfixes">fix as many of these</RouterLink> 
    as we can. Some of them can be a big deal (Dutch ship UUs!), while some of them are very minor.
    <br/><br/>
    But whether big or small, every bug that's fixed makes the game just that little bit better.
  </HomepageItem>

  <HomepageItem>
    <template #media>
      <img src="../assets/scales_by_Freepik.png">
    </template>
    <template #heading>Balance Tweaks</template>

    With the baseline balance restored to how it's supposed to be, we can 
    <RouterLink to="/features#balance_changes">tweak that balance</RouterLink> 
    with <em>small</em>, targeted changes to further improve the game. We try to balance across 
    different skill levels and game modes, and use data and consensus to support changes.
    <br /><br />
    Some of RoN's best players have been involved in this process to ensure that CBP's changes 
    make sense even at the highest levels of play.
  </HomepageItem>

  <HomepageItem>
    <template #media>
      <img src="../assets/replace_by_Freepik.png">
    </template>
    <template #heading>Replacement Launcher</template>

    To make patching (and unpatching) the game as quick and easy as possible, 
    <RouterLink to="/features#launcher">CBP Launcher</RouterLink> 
    was created. Once installed, switching between patched and unpatched RoN takes only seconds.
    <br /><br />
    The launcher also includes some additional functionality, such as its own basic plugin system.
    It's all completely open source and all completely optional to use – none of it's strictly 
    needed to use the patch (although using CBP Launcher is <em>much</em> more convenient!).
  </HomepageItem>

  <HomepageItem>
    <template #media>
      <img src="../assets/fist-bump_by_Freepik.png">
    </template>
    <template #heading>An Ongoing Community Effort</template>

    This is an ongoing project and the existing changes aren't set in stone. If you think something 
    needs to be changed, make your case and the community can discuss and test the idea.
    <br/><br/>
    Just like its name implies, CBP is made possible by the community's contributions. Check out 
    <RouterLink to="/credits">the people</RouterLink> 
    who've helped to create it, or find out 
    <RouterLink to="/how-to-help">how you can help</RouterLink> 
    to make the patch better.
  </HomepageItem>
</template>

<style scoped>
  img{
    filter: brightness(90%);
  }
</style>
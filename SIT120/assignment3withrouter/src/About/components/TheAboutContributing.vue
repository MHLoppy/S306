<script setup>
    import AboutContributingItem from "./AboutContributingItem.vue";
</script>

<template>
  <div class="wrapper">
    <div>
      <h3>Where to discuss</h3>
      There are specific threads for CBP on the Steam forums, or you can discuss CBP on the 
      unofficial Rise of Nations Discord server – check the links at the bottom of each page.
    </div>
    <br/><br/>
    <div>
      <h3>Focus areas</h3>
      There are currently two main areas where extra help is particularly needed:
    </div>
    <br/>
    <div class="areas">
        <AboutContributingItem header="Balancing / Playtesting">

            Right now we don't have enough high-level players testing the updated balance. There's 
            only so far that we can get with small-scale testing with number crunching and matchup 
            testing – we need more full games played between skilled players!
            <br/><br/>
            Basically just play more games while paying attention to any units / nations etc that
            seem to be consistently too weak or too strong. Then bring it up so that it can be tested 
            and discussed further! 
        </AboutContributingItem>
        <br/><br/>
        <AboutContributingItem header="Translating">

            Rise of Nations is remarkably international game, but the core team has limited fluency 
            in other languages. Machine translation has improved over the years but still lacks 
            the nuance that a fluent speaker has. We're especially looking to support these
            languages in both documentation and in-game tooltips:
            <ul>
                <li>Chinese (中文)</li>
                <li>French (Français)</li>
                <li>German (Deutsch)</li>
                <li>Italian (Italiano)</li>
                <li>Japanese (日本語)</li>
                <li>Korean (한국어)</li>
                <li>Portugese – BR (Português)</li>
                <li>Russian (русский)</li>
                <li>Spanish (Español)</li>
            </ul>
            <br/>
            Other translations are also helpful, but the above languages cover both the languages 
            which RoN officially supports, as well as most players within the community and so are 
            the current focus.
        </AboutContributingItem>
    </div>
    <br/><br/>
        <div>
            <h3>Other contributions</h3>
            Contributions in other areas are welcome (and encouraged!) as well, but if you're able 
            to contribute to the above, please try to do so before focusing on other things.
        </div>
  </div>
</template>

<style scoped>
    .wrapper {
    max-width: 720px;
    margin-left: 12px;
    margin-right: 2px;
  }

  .areas {
    margin-left:52px;
  }

  @media (min-width: 812px){
    .wrapper {
      margin-left: auto;
      margin-right: auto;
    }
  }
</style>
<script setup>
  import { RouterLink } from "vue-router";
  import FeaturesItem from "./FeaturesItem.vue";
  import FeaturesGifBox from "./FeaturesGifBox.vue"
</script>

<template>
  <div class="wrapper">
    <!-- we wrap divs around each item in order to use anchor points more neatly -->
    <div id="object_masks_bug_workaround">
      <FeaturesItem>
        <template #feature>Object masks bug workaround</template>

        <div>
          The damage which every unit or building deals per hit is calculated as:
          <br/>
          (
          <span class="strength">Attacker's attack strength</span>
           * 
          <span class="modifiers">damage modifiers</span>
          ) - 
          <span class="armor">defender's armor</span>
          <br/><br/>
          There are <em>a lot</em> of damage modifiers in Rise of Nations, but the one that we care 
          about here comes from "object masks", which are a property of units which describes what 
          "type" each unit/building is. For example, Hoplites are of the "Heavy Infantry" type, 
          which is represented by the "H" mask.
          <br/><br/>
          Bowmen are of the "Foot Archers" type, which is represented by the "K" mask. Units with 
          the K mask deal bonus damage to units with the H mask. In other words, masks are why 
          Foot Archers deal bonus damage to Heavy Infantry.
          <br/><br/>
          Masks are a fundamental part of rock-paper-scissors style unit counters in RoN, and the 
          game relies on them heavily for balancing purposes. The 
          <span class="modifiers">damage modifiers</span> 
          from masks are split into two parts. Unfortunately in the Extended Edition only one of 
          these two parts works, while the other does nothing and has no effect regardless of 
          what value is set.
          <br/><br/>
          This completely changes the game's balance, and since masks are used so heavily, every 
          unit and building in the game is affected in some of its matchups. One of the most 
          obvious effects is that War Elephants – a unit which was not considered weak by the 
          Rise of Nations community – now takes <em>less than half</em> of its intended damage 
          from Foot Archers (which are supposed to counter it, as it states in the unit's tooltip).
        </div>
        <br/>
        <div class="imgContainer">
          <FeaturesGifBox gif-link="elephant_damage_tp.gif" png-link="banner_tp.png"/>
          <div class="spacer"></div>
          <FeaturesGifBox gif-link="elephant_damage_ee.gif" png-link="banner_ee.png"/>
          <div class="spacer"></div>
          <FeaturesGifBox gif-link="elephant_damage_cbp.gif" png-link="banner_cbp.png"/>
        </div>
        <div>
          CBP includes a workaround for this bug, restoring the intended result of masks.
          <br/><br/>
          You can find out more about how damage is calculated in Rise of Nations on the 
          <a target="_blank" href="https://riseofnations.fandom.com/wiki/Damage" rel="noopener">RoN wiki page</a>.
        </div>
      </FeaturesItem>
    </div>
    <br/><br/>
    <div id="other_bugfixes">
      <FeaturesItem>
        <template #feature>Other bugfixes</template>

        Other than the object masks bug, there are still plenty of other bugs in RoN. These 
        broadly fit into four categories:
        <ul>
          <li>Gameplay bugs</li>
          <li>Graphics bugs</li>
          <li>Audio bugs</li>
          <li>Tooltip errors</li>
        </ul>
        <br/>
        CBP takes the philosophy of trying to fix as many of these bugs as possible by default, 
        whether the bug is significant:
        <br/><br/>
        <div class="imageSingle">
          <img src="../assets/Dutch_UUs_comparison.jpg">
        </div>
        <br/>
        or trivial:
        <br/><br/>
        <div class="imageSingle">
          <img src="../assets/Armored_Car_comparison.jpg">
        </div>
        <br/>
        Most of the bugfixes included in CBP were ported from the earlier Misc Fixes mod. Those 
        fixes all include 
        <a target="_blank" href="https://steamcommunity.com/sharedfiles/filedetails/discussions/2218254050" rel="noopener">documentation</a> 
        if you're interested in finding out more about what they are and how they've been fixed.
      </FeaturesItem>
    </div>
    <br/><br/>
    <div id="balance_changes">
      <FeaturesItem>
        <template #feature>Balance changes</template>

        The most important balance change in CBP – by far – is the fix to the object masks bug.
        <!-- p.s. putting an internal link here has side effects due to e.g. router fade transition delay -->
        <!-- <RouterLink to="/features#object_mask_bug_workaround">object masks bug</RouterLink>. -->
        <br/><br/>
        With that working as intended, there are still some areas where game balance can be 
        further improved. The original developers knew this as well, which is why they themselves 
        released 
        <a target="_blank" href="https://riseofnations.fandom.com/wiki/Game_versions" rel="noopener">multiple official balance patches</a> 
        back in the day.
        <br/><br/>
        The changes made in CBP are <em>tweaks</em>: they don't fundamentally change how the game 
        is played, and the goal is not to be changing core gameplay mechanics.
        <br/><br/>
        One area of focus has been reducing the effect of luck on the outcome of a game by reducing 
        the power of random elements such as ruins and rares. For example:
        <br/><br/>
        <div class="indented">
          <strong>Base ruins bonus (Sci0):</strong>
        <ul>
          <li>Original: 25 resources</li>
          <li>CBP: 20 resources</li>
        </ul>
        <br/>
        <strong>Copper bonus HP for ships:</strong>
        <ul>
          <li>Original: 20%</li>
          <li>CBP: 12%</li>
        </ul>
        </div>

        <br/>
        Changes are tested before release. For the Copper change, every Heavy Ship and every Fire 
        Ship from Ages I-VI 
        <a target="_blank" href="https://docs.google.com/spreadsheets/d/1HRIUySiZxvGxkEO7YjJGjD8_-Tim3j6Nf1jE9QkCdVU" rel="noopener">was tested</a> 
        to ensure that there were no unintended effects on the number of Fire Ships needed to 
        destroy a single Heavy Ship during any given age. CBP's changes are carefully and 
        deliberately implemented.
        <br/><br/>
        All changes (including bugfixes) which affect balance are publicly documented to provide 
        complete transparency about what the differences are. All changes are signed off by 
        the Advisory Group which includes (or included) some of the game's best players such as 
        <RouterLink id="credits" to="/credits">Jatin and dave</RouterLink>.
        <br/><br/>
        <div class="imageSingle">
          <img src="../assets/cbp_testing.jpg">
        </div>
      </FeaturesItem>
    </div>
    <br/><br/>
    <div id="launcher">
      <FeaturesItem>
        <template #feature>CBP Launcher</template>

        CBP Launcher is an open source replacement for the RoN:EE's launcher. Its primary purpose 
        is making using the patch easier, such as by allowing you to patch or unpatch the game 
        quickly and easily with a single button press.
        <br/><br/>
        Additional features are integrated, such as:
        <ul>
          <li>Integrated patch notes summary viewer</li>
          <li>Automated installation of user-selectable cosmetic changes ("Optional Changes")</li>
          <li>Optional automatic insertion/removal of a CBP icon into game lobbies that you host</li>
          <li>Separate plugin system to further extend functionality</li>
        </ul>
        <br/>
        The whole thing is 
        <a target="_blank" href="https://github.com/MHLoppy/CBP-Launcher" rel="noopener">completely open source</a> 
        – including first-party plugins and the 
        <a target="_blank" href="https://github.com/MHLoppy/CBP-SDK" rel="noopener">plugin SDK</a> 
        itself.
        <br/><br/>
        CBP Launcher is also an <strong>optional</strong> part of CBP. It remains possible to 
        <a target="_blank" href="https://steamcommunity.com/workshop/filedetails/discussion/2287791153/2971776551518918150/#c3163209341715773492" rel="noopener">manually install CBP</a> 
        without it, although using the launcher makes the process <em>substantially</em> easier.
        <br/><br/>
        <div class="imageSingle">
          <img src="../assets/CBP_Launcher_touchup.png">
        </div>
        <br/>
        A few first-party plugins are bundled with CBP Launcher to provide what are considered 
        core features. One example is emulating the popular population limit / armageddon limit 
        scripts. Using these plugins maintains CBP compatibility and imposes no baseline 
        multiplayer stability penalty, two issues which plague the script-based implementation of 
        these features.
        <br/><br/>
        Much like CBP Launcher itself, the entire plugin system is 
        <strong>completely optional to use</strong> and can be disabled entirely from the Options 
        menu within CBP Launcher if you so desire.
        <br/><br/>
        <div class="imageSingle">
          <img src="../assets/CBP_RE.png">
        </div>
      </FeaturesItem>
    </div>
  </div>
</template>

<style scoped>
  .wrapper {
    max-width: 800px;
    margin-left: 12px;
    margin-right: 2px;
  }

  /* color elements in the damage equation */
  .wrapper .strength {
    color: #E68783;
  }

  .wrapper .modifiers {
    color: #A5E6BD;
  }

  .wrapper .armor {
    color: #7778E6;
  }

  .indented {
    margin-left: 20px;
  }

  .imgContainer {
    display: flex;
    flex-direction: row;
  }

  .imageSingle {
    max-width: 720px;
  }

  .imageSingle img {
    filter: brightness(84%);
    object-fit: contain;
    height: 100%;
    width: 100%;
  }

  .spacer {
    margin-left:20px;
    margin-right:20px;
  }

  @media (min-width: 812px){
    .wrapper {
      margin-left: auto;
      margin-right: auto;
    }
  }
</style>
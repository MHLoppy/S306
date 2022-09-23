<script setup>
  import AboutTeamItem from "./AboutTeamItem.vue";
</script>

<template>
  <!-- with more time some of this layout logic could be moved to the underlying 
       component, but it takes a lot longer to set that up properly -->
  <div class="wrapper">
    <div class="container">
      <AboutTeamItem 
        name="MHLoppy" 
        image-link="MHLoppy_128px.png">
        <ul>
          <li>CBP Launcher lead developer</li>
          <li>Website lead designer/developer</li>
          <li>Technical test lead</li>
          <li>QA lead</li>
          <li>Advisory group (core)</li>
        </ul>
      </AboutTeamItem>
    </div>

    <div class="container">
      <AboutTeamItem
        name="(1)[H]nZ88"
        image-link="HnZ88_128px.png">
        <ul>
          <li>Marketing lead</li>
          <li>Advisory group (core)</li>
          <li class="secondary">QA</li>
        </ul>
      </AboutTeamItem>
    </div>

    <div class="container">
      <AboutTeamItem
        name="Jatin"
        image-link="Jatin_128px.png">
        <ul>
          <li>Advisory group (core)</li>
        </ul>
      </AboutTeamItem>
    </div>

    <div class="container">
      <AboutTeamItem
        name="dave (retired)"
        image-link="dave_128px.png">
        <ul>
          <li>Advisory group (core)</li>
        </ul>
      </AboutTeamItem>
    </div>

    <div class="container">
      <AboutTeamItem
        name="Hockeystar"
        image-link="placeholder_128px.png">
        <ul>
          <li class="secondary">Advisory group</li>
        </ul>
      </AboutTeamItem>
    </div>

    <div class="container">
      <AboutTeamItem
        name="[AS]_Serman (retired)"
        image-link="placeholder_128px.png">
        <ul>
          <li class="secondary">Advisory group</li>
        </ul>
      </AboutTeamItem>
    </div>

    <div class="container">
      <h4>Additional credits</h4>
      <ul>
        <!-- first additional credits -->
        <li><strong>mjn33: </strong>
          <a target="_blank" href="https://github.com/mjn33/ron-objmask-workaround" rel="noopener">ron-objmask-workaround</a> utility
        </li>
        <li><strong>prolaze: </strong>additional balance input</li>
        <li><strong>MoMo: </strong>emotional support</li>

        <!-- second additional credits -->
        <li><strong>Tingey Injury Law Firm (via Unsplash): </strong>
          <a target="_blank" href="https://unsplash.com/photos/yCdPU73kGSc" rel="noopener">Lady Justice holding scales</a>
        </li>
        <li><strong>Vanshilar (via RoN Heaven): </strong>
          <a target="_blank" href="https://ron.heavengames.com/downloads/showfile.php?fileid=2136" rel="noopener">damage calculations spreadsheet</a>
        </li>
        <li><strong>motter28218 (via RoN Heaven): </strong>
          <a target="_blank" href="https://ron.heavengames.com/downloads/showfile.php?fileid=1681" rel="noopener">sorted balance.xml</a>
        </li>

        <!-- third additional credits -->
        <li><strong>RoN community: </strong>additional input and testing</li>
      </ul>
    </div>

    <div class="container">
      <h4>Website image credits</h4>
      <ul>
        <!-- homepage image credits -->
        <li><strong>Freepik (via Flaticon): </strong>
          <a target="_blank" href="https://www.flaticon.com/free-icon/wrench_31056" rel="noopener">wrench icon</a>, 
          <a target="_blank" href="https://www.flaticon.com/free-icon/balance_924954" rel="noopener">scales icon</a>, 
          <a target="_blank" href="https://www.flaticon.com/free-icon/alter_5592598" rel="noopener">swap icon</a>, 
          <a target="_blank" href="https://www.flaticon.com/free-icon/fist-bump_6436277" rel="noopener">fistbump icon</a> 
        </li>
        <li>Splash image courtesy Microsoft Game Studios / Skybox Labs</li>
        <li>Rise of Nations logo courtesy Microsoft Game Studios / Big Huge Games</li>
      </ul>
    </div>
    <!-- <div class="community">
      asdf
    </div> -->
  </div>

</template>

<style scoped>
  /* do single-column when there's not enough width for double */
  .wrapper {
    max-width: 800px;
    margin-left: 12px;
    margin-right: 2px;
    display: grid;
    grid-template-columns: auto;
  }

  /* .community {
    max-width: 800px;
    margin-left: 12px;
    margin-right: 2px;
  } */

  .container {
    margin: 20px;
  }

  ul {
    padding-left: 15px;
  }

  li {
    list-style-type: disc;
  }

  .secondary {
    list-style-type: circle;
  }

  /* do double-column when there's enough width */
  @media (min-width: 812px){
    .wrapper {
      margin-left: auto;
      margin-right: auto;
      grid-template-columns: auto auto;
    }
  }
</style>
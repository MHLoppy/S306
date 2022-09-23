<script setup>
    import InstallGuideStep from "./InstallGuideStep.vue";
</script>

<template>
    <div id="guide">
        <InstallGuideStep>
            <template #step>Prerequisites</template>

            <em>CBP only works on the Steam version of RoN:EE. The Windows Store version does 
                not support modding.</em>
            <br/><br/>
            If your OS is on this list:
            <ul>
                <li>Windows 7</li>
                <li>Windows 8</li>
                <li>Windows 8.1</li>
            </ul>
            then you may need to first 
            <a target="_blank" href="https://dotnet.microsoft.com/en-us/download/dotnet-framework" rel="noopener">update .NET Framework</a>
            before continuing, as CBP Launcher needs .NET Framework 4.7.2 (or higher) to run. Note 
            that ".NET" and ".NET Framework" are different.
            <br/><br/>
            If you use Windows 10 or above and have installed updates anytime in the past 2 years, 
            then you probably have a compatible version already installed and can skip this step.
            <br/><br/>
        </InstallGuideStep>
        <br/><br/>
        <InstallGuideStep>
            <template #step>Step 1</template>

            Go to 
            <a target="_blank" href="https://steamcommunity.com/sharedfiles/filedetails/?id=2287791153" rel="noopener">CBP's page</a> 
            on the Steam Workshop and Subscribe to the mod.
            <br/><br/>
            Check that the files have finished downloading before continuing. You can do this by 
            opening Steam and checking the Downloads section at the bottom.
            <br/><br/>
            <em>Note that if you have RoN open, or if you don't have automatic updates enabled 
            for Rise of Nations on Steam, then the download may not happen automatically.</em>
            <br/>
            <div class="media">
                <img src="../assets/install_guide_steam.png">
            </div>
        </InstallGuideStep>
        <br/><br/>
        <InstallGuideStep>
            <template #step>Step 2</template>

            Open Rise of Nations. From the main menu:
            <ol>
                <li>
                    Go to <strong>Tools & Extras</strong>.
                </li>
                <li>
                    Go to <strong>Steam Workshop</strong>.
                </li>
                <li>
                    On the list of installed mods, select CBP and then press the 
                    <strong>Open Directory</strong> button.
                </li>
            </ol>
            <br/>
            This will open Windows Explorer will at the location where the Workshop files are downloaded.

            <div class="mediaSmall">
                <img src="../assets/install_guide_01.png">
                <img src="../assets/install_guide_02.png">
            </div>
            <div class="media">
                <img src="../assets/install_guide_03.png">
            </div>
        </InstallGuideStep>
        <br/><br/>
        <InstallGuideStep>
            <template #step>Step 3</template>

            <strong>IMPORTANT: Close Rise of Nations.</strong>
            <br/><br/>
            <i>DO NOT TRY TO PATCH YOUR GAME FILES WHILE THE GAME IS RUNNING.</i>
            <br/><br/>
            In the window which you just opened, run CBPSetupGUI.exe. You don't need to run 
            the CBP Launcher exe directly from this folder – you only need to run the setup exe.
            <br/>
            <div class="media">
                <img src="../assets/install_guide_explorer.png">
            </div>
            <div class="mediaContrast">
                <img src="../assets/install_guide_04.png">
            </div>
        </InstallGuideStep>
        <br/><br/>
        <InstallGuideStep>
            <template #step>Step 4</template>

            You'll be asked a couple of questions about default settings. <em>Any setting you 
            select now can be changed later if you need to.</em>
            <br/><br/>
            It's recommended (but not required) that you allow CBP Launcher to replace the default 
            launcher, since it can launch both patched and unpatched versions of the game for you. 
            If you don't replace it with CBP Launcher, you'll need to re-run this setup process 
            when you want to patch or unpatch the game.
            <br/><br/>
            After successfully copying files, you'll be asked if you want to start CBP Launcher. 
            Say <strong>yes</strong>.
            <div class="mediaSmall">
                <img src="../assets/install_guide_05.png">
            </div>
        </InstallGuideStep>
        <br/><br/>
        <InstallGuideStep>
            <template #step>Installation Complete</template>

            <div class="mediaFloat">
                <img src="../assets/load_unload.gif">
            </div>
            CBP is now ready to use. You can use the Load and Unload buttons just above the Launch 
            Game button to quickly patch and unpatch your game.
            <br/><br/>
            <strong>Multiplayer compatibility: </strong>
            <br/>Note that ALL players in a CBP game lobby need the patch when playing patched 
            multiplayer (not just the host). To play with unpatched players, simply unload CBP before 
            starting RoN.
            <br/><br/>
            Your menus will be slightly different while CBP is loaded – you can hover over the CBP 
            button on the menus to get a status readout (pressing the button doesn't do anything).
            <div class="mediaSmall">
                <img src="../assets/install_guide_06.png">
            </div>
            <br/>
            If you need any help or have any feedback, feel free to use the links below!
        </InstallGuideStep>
    </div>
</template>

<style scoped>
  img {
    margin: 5px;
    max-width: 750px;

    filter: brightness(84%);
  }

  #guide {
    max-width: 760px;
    margin-left: 12px;
    margin-right: 2px;

    /* brighten the background a hair? it doesn't have a huge effect but maybe it's better than nothing */
    background: radial-gradient(farthest-side at center, #1D1E26, #14151A)
  }

  #guide .media .mediaSmall .mediaContrast{
    object-fit: contain;
  }

  #guide .media img {
    width: 100%;
  }

  /* some images don't need to be full-size */
  #guide .mediaSmall img {
    max-width: 370px;
  }

  /* because of its blue background, we need to make this stand out from the site's background */
  #guide .mediaContrast img{
    width: 100%;
    max-width: 584px;

    filter: brightness(84%) contrast(91%);
  }

  #guide .mediaFloat {
    float:left;
  }

  /* using i as a substitute for "yell" */
  i {
    color: var(--color-brown);
    font-weight: 700;
  }

  /* looks like this page only needs a basic margin */
  @media (min-width: 812px){
    #guide {
      margin-left: auto;
      margin-right: auto;
    }
  }
</style>
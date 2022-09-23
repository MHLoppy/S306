<script setup lang="ts">
import WelcomeItem       from "./HomepageItem.vue";
import DocumentationIcon from "./icons/IconDocumentation.vue";
import ToolingIcon       from "./icons/IconTooling.vue";
import EcosystemIcon     from "./icons/IconEcosystem.vue";
import CommunityIcon     from "./icons/IconCommunity.vue";
import SupportIcon       from "./icons/IconSupport.vue";
</script>

<template>
  <div>
    <WelcomeItem>
      <template #icon>
        <DocumentationIcon />
      </template>
      <template #heading>Correct Damage Modifiers</template>
      In RoN:EE, every unit and building in the game is affected by a
      <a target="_blank" href="/object-masks-damage-bug">major damage bug</a>.
      This means that EE's balance is <strong>not</strong> what the original developers intended. 
      Unfortunately this bug was never fixed in EE's official patches, so CBP includes a workaround 
      for this bug instead.
      <br/><br/>
      This is the foundation upon which all of the other changes are built on top of.
    </WelcomeItem>

    <WelcomeItem>
      <template #icon>
        <ToolingIcon />
      </template>
      <template #heading>Tooling</template>

      This project is served and bundled with
      <a href="https://vitejs.dev/guide/features.html" target="_blank">Vite</a>.
      The recommended IDE setup is
      <a href="https://code.visualstudio.com/" target="_blank">VSCode</a> +
      <a href="https://github.com/johnsoncodehk/volar" target="_blank">Volar</a
      >. If you need to test your components and web pages, check out
      <a href="https://www.cypress.io/" target="_blank">Cypress</a> and
      <a
        href="https://docs.cypress.io/guides/component-testing/introduction"
        target="_blank"
        >Cypress Component Testing</a
      >.

      <br />

      More instructions are available in <code>README.md</code>.
    </WelcomeItem>

    <WelcomeItem>
      <template #icon>
        <EcosystemIcon />
      </template>
      <template #heading>Ecosystem</template>

      Get official tools and libraries for your project:
      <a target="_blank" href="https://pinia.vuejs.org/">Pinia</a>,
      <a target="_blank" href="https://v3.router.vuejs.org/">Vue Router</a>,
      <a target="_blank" href="https://vue-test-utils.vuejs.org/"
        >Vue Test Utils</a
      >, and
      <a target="_blank" href="https://github.com/vuejs/devtools"
        >Vue Dev Tools</a
      >. If you need more resources, we suggest paying
      <a target="_blank" href="https://github.com/vuejs/awesome-vue"
        >Awesome Vue</a
      >
      a visit.
    </WelcomeItem>

    <WelcomeItem>
      <template #icon>
        <CommunityIcon />
      </template>
      <template #heading>Community</template>

      Got stuck? Ask your question on
      <a target="_blank" href="https://chat.vuejs.org">Vue Land</a>, our
      official Discord server, or
      <a
        target="_blank"
        href="https://stackoverflow.com/questions/tagged/vue.js"
        >StackOverflow</a
      >. You should also subscribe to
      <a target="_blank" href="https://news.vuejs.org">our mailing list</a> and
      follow the official
      <a target="_blank" href="https://twitter.com/vuejs">@vuejs</a>
      twitter account for latest news in the Vue world.
    </WelcomeItem>

    <WelcomeItem>
      <template #icon>
        <SupportIcon />
      </template>
      <template #heading>Support Vue</template>

      As an independent project, Vue relies on community backing for its
      sustainability. You can help us by
      <a target="_blank" href="https://vuejs.org/sponsor/">becoming a sponsor</a
      >.
    </WelcomeItem>

    This is the main text area...
    Footers and stuff will be down here somewhere asdf
  </div>
</template>

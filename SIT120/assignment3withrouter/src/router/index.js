import { createRouter, createWebHistory } from "vue-router";
import HomepageView from "../views/HomepageView.vue";

const router = createRouter({
  mode: 'history',
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Home",
      component: HomepageView,
    },
    // route level code-splitting
    // this generates a separate chunk (About.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    {
      path: "/features",
      name: "Features",
      component: () => import("../views/FeaturesView.vue"),
    },
    {
      path: "/install-guide",
      name: "InstallGuide",
      component: () => import("../views/InstallGuideView.vue"),
    },
    {
      path: "/patch-notes",
      name: "PatchNotes",
      component: () => import("../views/PatchNotesView.vue"),
    },
    {
      path: "/credits",
      name: "Credits",
      component: () => import("../views/AboutTeamView.vue"),
    },
    {
      path: "/how-to-help",
      name: "HowToHelp",
      component: () => import("../views/AboutContributingView.vue"),
    },
    // 404 catch-all
    {
      path: '/:catchAll(.*)*',
      name: "PageNotFound",
      component: () => import("../views/PageNotFound.vue"),
    },
  ],
  // after a 250ms delay (transition speed), scroll to top of page when changing views; optionally can use the vars for other things
  scrollBehavior (to, from, savedPosition) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve({ left: 0, top: 0 })
      }, 250)
    })
  }
});

export default router;

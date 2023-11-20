import { createRouter, createWebHistory } from "vue-router";
import LoginForm from "../components/User/LoginForm.vue";
import RegistForm from "../components/User/RegistForm.vue";
import MainPage from "../views/MainPage.vue";
import IntroducePage from "../views/IntroducePage.vue";

const routes = [
  {
    path: "/",
    name: "IntroducePage",
    component: IntroducePage,
  },
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginForm,
  },
  {
    path: "/regist",
    name: "Regist",
    component: RegistForm,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
import { createRouter, createWebHistory } from "vue-router";
import LoginForm from "../components/User/LoginForm.vue";
import RegistForm from "../components/User/RegistForm.vue";
import MainPage from "../views/MainPage.vue";
import IntroducePage from "../views/IntroducePage.vue";
import Diary from "../components/Diary/Diary.vue";
import friendsRequest from "../components/Friends/friendsRequest.vue";
import { useUserStore } from "../stores/userStore";

// 동작 확인
// const requireAuth = () => (to, from, next) => {
//   if (localStorage.getItem("userid") !== null) {
//     console.log("넌 지나간다.");
//     return next();
//   } else {
//     alert("로그인을 해야 사용할 수 있는 기능입니다.");
//     console.log("넌 못지나간다.");
//     return next("/");
//   }
// };

// 로그인이 되어 있지 않을 때의 함수
const requireNoneAuth = () => (to, from, next) => {
  if (localStorage.getItem("userid") === null) {
    // 로그인이 되어있지 않다면 갈 수 있지
    const userStore = useUserStore();
    userStore.loginUser = null;
    console.log("넌 지나간다.");
    return next();
  } else {
    // 로그인이 되어있으면 못가지
    alert("로그인한 상태잖아요");
    const userStore = useUserStore();
    userStore.loginUser = localStorage.getItem("userid");
    console.log("넌 못지나간다.");
    return next(from);
  }
};

// 실험
const requireAuth = () => (to, from, next) => {
  if (localStorage.getItem("userid") !== null) {
    console.log("넌 지나간다.");
    const userStore = useUserStore();
    userStore.loginUser = localStorage.getItem("userid");
    return next();
  } else {
    alert("잘못된 접근입니다.");
    console.log("넌 못지나간다.");
    next("/");
  }
};

const getKey = () => (to, from, next) => {
  if (localStorage.getItem("userid") !== null) {
    console.log("홈으로가자.");
    const userStore = useUserStore();
    userStore.loginUser = localStorage.getItem("userid");
    return next();
  } else {
    console.log("넌 못지나간다.");
    const userStore = useUserStore();
    userStore.loginUser = localStorage.getItem("userid");
    return next();
  }
};

const routes = [
  {
    path: "/",
    name: "IntroducePage",
    component: IntroducePage,
    beforeEnter: getKey(),
  },
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
    beforeEnter: requireAuth(),
  },
  {
    path: "/login",
    name: "Login",
    component: LoginForm,
    beforeEnter: requireNoneAuth(),
  },
  {
    path: "/regist",
    name: "Regist",
    component: RegistForm,
    beforeEnter: requireNoneAuth(),
  },
  {
    path: "/diary/:date",
    name: "Diary",
    component: Diary,
    beforeEnter: requireAuth(),
  },
  {
    path: "/friendsRequest",
    name: "FriendsRequest",
    component: friendsRequest,
    beforeEnter: requireAuth(),
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;

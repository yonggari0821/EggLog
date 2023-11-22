<template>
  <div v-if="flag === null">
    <Navigation />
  </div>
  <div v-else>
    <LoginNavigation />
  </div>
  <div>
    <RouterView />
  </div>
  <footer>
    <MainPageFooter />
  </footer>
</template>

<!-- <script setup>
import { useUserStore } from "@/stores/userStore.js";
import { ref } from "vue";
import { onMounted, computed, onBeforeMount } from "vue"; // 희
import MainPageFooter from "./components/MainPage/Footer/MainPageFooter.vue";
import Navigation from "./views/Navigation.vue";
import LoginNavigation from "./views/loginNavigation.vue";

const userStore = useUserStore();

const flag = computed(() => localStorage.getItem("userid"));
onBeforeMount(() => {
  // flag.value = localStorage.getItem("userid");
  console.log("나 플래그야!!" + flag.value);
  console.log("나는 스토어의 loginUser야 " + userStore.loginUser + " 출력값~");
  console.log("나는 스토리지의 userid야!!" + localStorage.getItem("userid"));
});
</script> -->

<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { ref, watchEffect } from "vue";
import MainPageFooter from "./components/MainPage/Footer/MainPageFooter.vue";
import Navigation from "./views/Navigation.vue";
import LoginNavigation from "./views/loginNavigation.vue";
import { computed } from "@vue/reactivity";

const userStore = useUserStore();

// 되는거
// const flag = ref(localStorage.getItem("userid"));

// 실험
const flag = computed(() => {
  return userStore.loginUser;
});

// watchEffect(() => {
//   // localStorage 값이 변경될 때마다 호출되는 부분
//   flag.value = localStorage.getItem("userid");
//   console.log("나 왓치야!!" + flag.value);
//   console.log("나는 스토어의 loginUser야 " + userStore.loginUser + " 출력값~");
//   console.log("나는 스토리지의 userid야!!" + localStorage.getItem("userid"));
// });
</script>

<style scoped></style>

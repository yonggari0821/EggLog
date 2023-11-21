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

<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { ref } from "vue";
import { onMounted, computed, onBeforeMount } from "vue"; // 희
import MainPageFooter from "./components/MainPage/Footer/MainPageFooter.vue";
import Navigation from "./views/Navigation.vue";
import LoginNavigation from "./views/loginNavigation.vue";

const userStore = useUserStore();
const flag = computed(() => userStore.loginUser);

onMounted(() => {
  // flag.value = localStorage.getItem("userid");
  console.log("나 플래그야!!" + flag.value);
  console.log("나는 스토어의 loginUser야 " + userStore.loginUser);
});
</script>

<style scoped></style>

<template>
  <div>
    <MainPageNav />
    <!-- 페이커님 환영합니다 부분-->
    <section class="main-page">
      <body class="main-section">
        <MainPageSection class="Height" />
      </body>
      <aside class="main-aside">
        <MainPageAside class="Height" />
      </aside>
    </section>
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import MainPageNav from "../components/MainPage/Nav/MainPageNav.vue";
import MainPageAside from "../components/MainPage/Aside/MainPageAside.vue";
import MainPageSection from "../components/MainPage/Body/MainPageSection.vue";

import router from "@/router";
import { useUserStore } from "@/stores/userStore.js";
import { onMounted, computed, onBeforeMount } from "vue"; // 희

const userStore = useUserStore();
onMounted(() => {
  // flag.value = localStorage.getItem("userid");
  console.log("나 메인페이지야야!!");
  console.log("나는 스토어의 loginUser야 " + userStore.loginUser + " 출력값~");
  console.log("나는 스토리지의 userid야!!" + localStorage.getItem("userid"));
});
</script>

<style scoped>
.main-page {
  display: flex;
  height: 75vh;
}

.main-section {
  width: 65%; /* 80% */

  background-color: brown;
}

.main-aside {
  width: 35%; /* 20% */
}

.Height {
  height: 100%;
}
</style>

<template>
  <nav>
    <img src="../../assets/egglogo.jpg" click="moveIntroduce" style="width: 3rem" />
  </nav>
  <div>
    <input type="text" v-model.trim="id" placeholder="id를 입력하시오" />
    <br />
    <input type="text" v-model.trim="pw" placeholder="pw를 입력하시오" />
  </div>
  <div>
    <button @click="moveMain">로그인하기</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";

const id = ref("");
const pw = ref("");

const moveIntroduce = function () {
  router.push({ name: "IntroducePage" });
};

const moveMain = function () {
  router.push({ name: "MainPage" });
};
</script>

<style scoped></style>

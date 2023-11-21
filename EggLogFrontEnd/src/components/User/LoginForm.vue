<template>
  <nav>
    <img src="../../assets/egglogo.jpg" @click="moveIntroduce" style="width: 3rem" />
  </nav>
  <div>
    <input type="text" v-model.trim="id" placeholder="id를 입력하시오" />
    <br />
    <input type="text" v-model.trim="pw" placeholder="pw를 입력하시오" />
  </div>
  <div>
    <button @click="login">로그인하기</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";
import router from "@/router";

const userStore = useUserStore();

const id = ref("");
const pw = ref("");

const login = () => {
  const user = {
    id: id.value,
    password: pw.value,
  };

};

const moveIntroduce = function () {
  router.push({ name: "IntroducePage" });
};

const moveMain = function () {
  router.push({ name: "MainPage" });
};
</script>

<style scoped></style>

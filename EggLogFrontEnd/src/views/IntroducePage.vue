<template>
  <nav>
    <img src="../assets/egglogo.jpg" click="moveIntroduce" style="width: 3rem" />
  </nav>
  <div>
    <button @click="moveLogin">로그인하기</button>
    <button @click="moveRegist">아직 아이디가 없으시다구요...?</button>
  </div>
</template>

<script setup>
import router from "@/router";

const moveLogin = function () {
  router.push({ name: "Login" });
};

const moveRegist = function () {
  router.push({ name: "Regist" });
};
</script>

<style scoped></style>

<template>
  <div>
    <img src="../../assets/egglogo.jpg" click="moveIntroduce" style="width: 3rem" />
    <h1>회원가입 화면입니다.</h1>
  </div>
</template>

<script setup>
import router from "@/router";

const moveIntroduce = function () {
  router.push({ name: "IntroducePagee" });
};
</script>

<style scoped></style>

<template>
  <div>
    <div class="Callender">
      <MainPageCallender v-if="who" />
      <!--내 정보 보내고-->
      <MainPageCallender v-else="who" />
      <!--친구 정보 보내고-->
    </div>
  </div>
</template>

<script setup>
import MainPageCallender from "./MainPageCallender.vue";
import { ref } from "vue";
const who = ref("true");
</script>

<style scoped>
/* @keyframes move {
  0% {
    background-color: #e0e0e0;
  }
  50% {
    background-color: #ffff33;
  }
  100% {
    background-color: #e0e0e0;
  }
} */

.Callender {
  padding: 2vw;
  width: 100%;
  height: 100%;
  /* z-index: -1; */
  background-color: #fefefe;
  /* animation: move 5s infinite; */
  padding-left: 1vw;
}
</style>

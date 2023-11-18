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
.Callender {
  background-color: rgb(56, 165, 42);
  width: 100%;
  height: 100%;
}
</style>

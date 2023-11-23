<template>
  <div class="myinfo">
    <br />
    <div style="display: inline-flex; align-items: flex-end">
      <p style="font-size: 30px; font-weight: bold">{{ user.nickname }}</p>
      <p>&nbsp 님 환영합니다.</p>
    </div>
    <p>{{ user.statusMessage }}</p>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";
import router from "@/router";
import { onMounted, computed } from "vue"; // 희
const userStore = useUserStore();

const user = computed(() => userStore.user);

onMounted(() => {
  userStore.setLoginUser(localStorage.getItem("userid"));
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@700&family=Noto+Sans+KR:wght@500;700&display=swap");
.myinfo {
  flex: 1;

  border: 1px solid purple;
}

p {
  font-family: "Lato", sans-serif;
  font-family: "Noto Sans KR", sans-serif;
}
</style>

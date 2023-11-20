<template>
  <div class="myinfo">
    <p>{{ user.nickname }} 님 환영합니다.</p>
    <p>{{ user.statusMessage }}</p>
    <button @click="logout">로그아웃</button>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";
import router from "@/router";
import { onMounted, computed } from "vue"; // 희
const userStore = useUserStore();

const user = computed(() => userStore.user);

const logout = () => {
  alert("정상적으로 로그아웃 되었습니다.");
  localStorage.removeItem("userid");
  localStorage.removeItem("accesstoken");
  router.push({ name: "IntroducePage" });
};

onMounted(() => {
  userStore.setLoginUser(localStorage.getItem("userid"));
});
</script>

<style scoped>
.myinfo {
  flex: 1;

  border: 1px solid purple;
}
</style>

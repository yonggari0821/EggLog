<template>
  <div class="myinfo">
    <p>{{ user.nickname }} 님 환영합니다.</p>
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
</script>

<style scoped>
.myinfo {
  flex: 1;

  border: 1px solid purple;
}
</style>

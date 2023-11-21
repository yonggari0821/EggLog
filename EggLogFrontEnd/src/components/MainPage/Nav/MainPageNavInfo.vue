<template>
  <div class="myinfo">
    <p>내 정보가 들어갈 자리입니다.</p>

    <p>{{ user.nickname }} 님 환영합니다.</p>
    <p>{{ user.statusMessage }}</p>
    <p></p>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";
import { onMounted, computed, onBeforeMount } from "vue"; // 희
const userStore = useUserStore();

const user = computed(() => userStore.user);
</script>

<style scoped>
.myinfo {
  flex: 1;

  border: 1px solid purple;
}
</style>

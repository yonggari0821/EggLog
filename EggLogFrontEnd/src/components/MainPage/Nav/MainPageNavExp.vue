<template>
  <div class="exp">
    <p>{{ regDate }}</p>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref, computed, onMounted, onBeforeMount } from "vue";

const userStore = useUserStore();
const regDate = ref("");
// regDate가져오기
const exp = computed(() => {
  // const oldDate = new Date("2023-04-20");
  // const newDate = new Date("2023-06-12");
  // let diff = Math.abs(newDate.getTime() - oldDate.getTime());
  // diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
  // console.log(diff);
  console.log(userStore.regDate);
  regDate.value = userStore.regDate;
  //  - userStore.todayDate
});

onBeforeMount(() => {
  // const oldDate = new Date("2023-04-20");
  // const newDate = new Date("2023-06-12");
  // let diff = Math.abs(newDate.getTime() - oldDate.getTime());
  // diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
  // console.log(diff);
  userStore.getRegDate(localStorage.getItem("userid"));
  console.log(userStore.reg);
  regDate.value = userStore.reg;
  //  - userStore.todayDate
});
</script>

<style scoped>
.exp {
  flex: 4;

  border: 1px solid rgb(0, 9, 128);
}
</style>

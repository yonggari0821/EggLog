<template>
  <div class="myinfo">
    <br />
    <div style="display: inline-flex; align-items: flex-end">
      <p style="font-size: 30px; font-weight: bold">{{ user.nickname }}</p>
      <p>&nbsp 님 환영합니다.</p>
    </div>
    <br />
    <!--상메-->
    <div style="display: inline-flex">
      <div>
        <img src="../../../assets/id.png" alt="" style="height: 20px" />
      </div>
      <div>
        <p>{{ user.id }}</p>
      </div>
    </div>
    <br />
    <div style="display: inline-flex">
      <div>
        <img src="../../../assets/상태.png" alt="" style="height: 20px" />
      </div>
      <div>
        <p>{{ user.statusMessage }}</p>
      </div>
    </div>
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

.myinfo {
  flex: 1;
  height: 100%;
  /* border: 1px solid gray; */
  z-index: -1;
  background-color: #FFE77A;

  border: 0.5px solid #2C5F2D;
  /* border-radius: 80px; */
  /* animation: move 5s infinite; */
  padding-left: 1vw;
  color: #2C5F2D;
  
  transition: background-color 0.6s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
}

.myinfo:hover{
  background-color: #FFD000;
  color: white;
}

p {
  font-family: "Lato", sans-serif;
  font-family: "Noto Sans KR", sans-serif;
}
</style>

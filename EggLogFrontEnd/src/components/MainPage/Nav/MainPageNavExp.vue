<template>
  <div class="profile-exp">
    <div class="profile-picture" style="display: flex; justify-content: center">
      <div v-if="userStore.level >= 3">
        <img src="@/assets/3달이상.png" alt="Profile Image" />
        마스터
      </div>
      <div v-else-if="userStore.level >= 2">
        <img src="@/assets/2달이상3달미만.png" alt="Profile Image" />
        레벨3
      </div>
      <div v-else-if="userStore.level >= 1">
        <img src="@/assets/1달이상2달미만.png" alt="Profile Image" />
        레벨2
      </div>
      <div v-else>
        <img src="@/assets/1달미만.png" alt="Profile Image" />
        레벨1
      </div>
    </div>

    <!-- exp를 가로 막대 그래프로 나타내기 -->
    <div class="exp-bar">
      <div class="bar" :style="{ width: expPercentage }">{{ userStore.exp }}/30</div>
    </div>

    <div style="flex: 1"></div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref, computed, onMounted, onBeforeMount } from "vue";

const userStore = useUserStore();
const expPercentage = computed(() => `${(userStore.exp / 30) * 100}%`);

onMounted(() => {
  userStore.expCalculate();
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");

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

p,
div {
  font-size: 1.5vw;
  font-weight: bold;
  font-family: "Noto Sans KR", sans-serif;
}

.profile-exp {
  display: flex;
  align-items: center;
  background-color: #ffe77a;
  color: #2c5f2d;
  height: 100%;
  /* animation: move 5s infinite; */

  transition: background-color 0.6s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
  border: 2px solid gray; /* 테두리 속성 추가 */
}

.profile-exp:hover {
  background-color: #ffd000;
  color: white;
}

.profile-picture {
  flex: 1;
  margin-right: 16px;
}

img {
  width: 5vw;
}

.exp-bar {
  flex: 2;
  height: 100px;
  background-color: #ffffff;
  border-radius: 4px;
  overflow: hidden;
  border: 1px solid gray; /* 테두리 속성 추가 */
}

.bar {
  height: 100%;
  background-color: #faebef;
  transition: width 0.5s ease;
  color: black;

  border-right: 1px solid gray; /* 테두리 속성 추가 */
}
</style>

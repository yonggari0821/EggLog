<template>
  <div>
    <nav style="position: fixed">
      <div style="flex: 1"></div>
      <div style="cursor: pointer; display: inline-flex; flex: 1" @click="moveIntroduce">
        <img src="../assets/egglogo.jpg" style="width: 3vw" />
        <p style="color: white; font-weight: bold; font-size: 1.5vw">Egglog</p>
      </div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
      <!--다이어리 위치-->
      <div style="flex: 1">
        <div
          class="Navp"
          @click="moveLogin"
          style="cursor: pointer; color: white; font-weight: bold; font-size: 1.1vw"
        >
          <p class="Navp">로그인</p>
        </div>
      </div>
      <div style="flex: 1">
        <div
          @click="moveRegist"
          style="cursor: pointer; color: white; font-weight: bold; font-size: 1.1vw"
        >
          <p class="Navp">회원가입</p>
        </div>
      </div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
    </nav>
  </div>
</template>

<script setup>
import router from "@/router";

const moveIntroduce = function () {
  router.push({ name: "IntroducePage" });
};

const moveLogin = function () {
  router.push({ name: "Login" });
};

const moveRegist = function () {
  router.push({ name: "Regist" });
};
</script>

<style scoped>
nav {
  background-color: rgb(255, 208, 0);
  display: inline-flex;
  width: 100%;
  justify-content: center;
  height: 3.5vw;
}

div {
  height: 100%;
  align-items: center;
}
.Navp {
  margin-top: 0.7vw;
}

p{
  transition: color 1s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
  cursor: pointer;
}

p:hover{
  color: red;}
</style>

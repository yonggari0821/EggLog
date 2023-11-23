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
      <div style="flex: 1">
        <div
          class="Navp"
          @click="moveFriendRequest"
          style="cursor: pointer; color: white; font-weight: bold; font-size: 1.1vw"
        >
          <p class="Navp">친구신청</p>
        </div>
      </div>
      <div style="flex: 1">
        <div
          class="Navp"
          @click="moveDiary"
          style="cursor: pointer; color: white; font-weight: bold; font-size: 1.1vw"
        >
          <p class="Navp">다이어리</p>
        </div>
      </div>
      <div style="flex: 1">
        <div
          class="Navp"
          @click="Logout"
          style="cursor: pointer; color: white; font-weight: bold; font-size: 1.1vw"
        >
          <p class="Navp">로그아웃</p>
        </div>
      </div>
      <div style="flex: 1">
        <div
          @click="moveMyPage"
          style="cursor: pointer; color: white; font-weight: bold; font-size: 1.1vw"
        >
          <p class="Navp">마이페이지</p>
        </div>
      </div>
      <div style="flex: 1"></div>
      <div style="flex: 1"></div>
    </nav>
  </div>
</template>

<script setup>
import router from "@/router";
import { useUserStore } from "../stores/userStore";

const userStore = useUserStore();
const moveIntroduce = function () {
  router.push({ name: "IntroducePage" });
};

const moveFriendRequest = function () {
  router.push({ name: "FriendsRequest" });
};

const Logout = function () {
  alert("정상적으로 로그아웃 되었습니다.");
  localStorage.removeItem("userid");
  localStorage.removeItem("accesstoken");
  router.push({ name: "IntroducePage" });
};

const moveMyPage = function () {
  router.push({ name: "MyPage" }); // 마이페이지로 변경해야하구요
};

const moveDiary = function () {
  router.push({ name: "MainPage" }); // 마이페이지로 변경해야하구요
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
</style>

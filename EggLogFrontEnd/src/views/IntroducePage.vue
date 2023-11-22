<template>
  <!-- <nav style="position: fixed">
    <img src="../assets/egglogo.jpg" click="moveIntroduce" style="width: 3rem" />
    <p style="color: white; font-weight: bold; font-size: 1.6rem">Egglog</p>
  </nav> -->

  <body>
    <section class="login">
      <div style="background-color: antiquewhite; height: 30vw">
        <div class="divMargin">
          <div style="width: 50%">
            <img src="../assets/께란이배경삭제.png" alt="" style="width: 100%" />
          </div>
          <div
            style="
              display: inline-block;
              align-items: center;
              width: 50%;
              padding-top: 5vh;
              height: 100%;
            "
          >
            <p style="font-size: 1.5vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              계란이와 함께하는
            </p>
            <p style="font-size: 3vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              운동 추억 다이어리
            </p>
          </div>
        </div>
      </div>

      <!--이미지 2-->
      <div style="background-color: azure">
        <div class="divMargin">
          <div style="display: inline-block; align-items: center">
            <p style="font-size: 1.5vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              하루하루의 기록을 함께할
            </p>
            <p style="font-size: 3vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              EggLog를 소개합니다
            </p>
          </div>
          <div>
            <img src="../assets/접시배경삭제.png" alt="" style="width: 35vw" />
          </div>
        </div>
      </div>

      <!--이미지4-->
      <div style="background-color: rgb(204, 255, 102)">
        <div class="divMargin">
          <div style="justify-content: left">
            <img
              src="../assets/책배경제거.png"
              alt=""
              style="width: 25vw; margin-top: 3vw; margin-bottom: 3vw"
            />
          </div>
          <div style="display: inline-block; align-items: center">
            <p style="font-size: 1.5vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              당신의 근성장을
            </p>
            <p style="font-size: 3vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              일기로 남겨두세요!
            </p>
          </div>
        </div>
      </div>
    </section>
  </body>
</template>

<script setup>
import router from "@/router";
</script>

<style scoped>
/* nav {
  background-color: rgb(255, 208, 0);
  display: inline-flex;
  width: 100%;
  justify-content: center;
  height: 5vh;
} */
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");
.divMargin {
  margin-left: 20rem;
  margin-right: 20rem;
}

body {
  padding-top: 5vh;
  height: 200vh;
  background-color: rgb(246, 250, 122);
}

.login {
  background-color: aliceblue;
}

div {
  width: 100%;
  justify-content: center;
  align-items: center;
  display: flex;
}
</style>

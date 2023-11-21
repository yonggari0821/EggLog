<template>
  <!-- <nav style="position: fixed">
    <img src="../assets/egglogo.jpg" click="moveIntroduce" style="width: 3rem" />
    <p style="color: white; font-weight: bold; font-size: 1.6rem">Egglog</p>
  </nav> -->

  <body>
    <section class="login">
      <div style="background-color: antiquewhite">
        <div class="divMargin">
          <div style="width: 50%">
            <img src="../assets/께란이배경삭제.png" alt="" style="width: 100%" />
          </div>
          <div style="display: inline-block; align-items: center; width: 50%">
            <p style="font-size: 1.5vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              계란이와 함께하는
            </p>
            <p style="font-size: 3vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
              운동 추억 다이어리
            </p>
          </div>
        </div>
      </div>
      <!-- 첫번째 이미지-->

      <!-- <div style="background-color: #0f1937">
        <div class="divMargin">
          <div class="main_image" style="justify-content: center">
            <img src="../assets/혁신.jpg" alt="" style="height: 100%" />
            <p
              class="main_image_text"
              style="font-weight: bold; color: rgb(0, 89, 137); font-size: 2rem"
            >
              EggLog 혁신을 개척해나가다
            </p>
          </div>
        </div>
      </div> -->

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
            <img src="../assets/접시배경삭제.png" alt="" style="width: 30vw" />
          </div>
        </div>
      </div>

      <div style="display: inline-flex; align-items: center">
        <p style="font-size: 2rem; font-weight: bold; font-family: monospace">
          당신의 근성장 단백질 계란을 일기에 구워보는건 어떤가요?
        </p>
        <img src="../assets/이미지2.png" alt="" style="width: 30rem" />
      </div>
      <div style="display: block">
        <img src="../assets/이미지3.jpg" alt="" style="width: 100%; overflow: auto" />
        <p>당신도 늦지 않았습니다.</p>
        <p style="font-size: 3rem">지금 당장 시작하세요!!!</p>
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

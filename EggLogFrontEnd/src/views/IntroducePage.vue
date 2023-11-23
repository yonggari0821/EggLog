<template>
  <div>
    <section class="login">
      <!-- 첫 번째 이미지 -->
      <div style="background-color: antiquewhite; height: 60vh">
        <div class="divMargin">
          <div style="width: 50%" class="test">
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

      <!-- 두 번째 이미지 -->
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
          <div class="test2">
            <img src="../assets/접시배경삭제.png" alt="" style="width: 35vw" />
          </div>
        </div>
      </div>

      <!-- 세 번째 이미지 -->
      <div style="background-color: rgb(204, 255, 102)">
        <div class="divMargin">
          <div style="justify-content: left" class="test3">
            <img
              src="../assets/책배경제거.png"
              alt=""
              style="width: 25vw; margin-top: 3vw; margin-bottom: 3vw"
            />
          </div>
          <div style="display: inline-block; align-items: center">
            <p style="font-size: 1.5vw; font-weight: bold; ">
              당신의 근성장을
            </p>
            <p style="font-size: 3vw; font-weight: bold;" >
              일기로 남겨두세요!
            </p>
          </div>
        </div>
      </div>

      <!-- 네 번째 이미지 -->
      <div style="background-color: azure">
        <div class="divMargin">
          <div style="display: inline-block; align-items: center">
            <p style="font-size: 1.5vw; font-weight: bold; font-family: 'Noto Sans KR', sans-serif">
              하루하루의 기록을 함께할
            </p>
            <p style="font-size: 3vw; font-weight: bold; font-family: 'Noto Sans KR', sans-serif">
              EggLog를 소개합니다
            </p>
          </div>
          <div>
            <img src="../assets/접시배경삭제.png" alt="" style="width: 35vw" />
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import router from "@/router";
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");

@keyframes move {
  0% {
    opacity: 0;
    transform: translateX(100%);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes moveRight {
  0% {
    opacity: 0;
    transform: translateX(-100%);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes moveAlone {
  0% {
    opacity: 0;
    transform: translateX(0%);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}

p,div{
    font-family: 'Noto Sans KR';
    font-family: 'sans-serif';
  }


.test {
  animation: move 1s ease-in-out;
}

.test2 {
  animation: moveRight 1s ease-in-out;
}

.test3 {
  animation: moveAlone ease-in-out 4s;
}
.divMargin {
  margin-left: 20rem;
  margin-right: 20rem;
}

body {
  padding-top: 5vh;
  height: 220vh;
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

<template>
  <nav style="position: fixed">
    <img src="../assets/egglogo.jpg" click="moveIntroduce" style="width: 3rem" />
    <p style="color: white; font-weight: bold; font-size: 1.6rem">Egglog</p>
  </nav>

  <body>
    <section class="login">
      <div>
        <p style="font-weight: bold; color: darkcyan; font-size: 3rem">
          EggLog 혁신을 개척해나가다
        </p>
      </div>
      <div style="display: inline-flex">
        <img src="../assets/이미지1.png" alt="" style="width: 30rem" />
        <p style="font-size: 2rem; font-weight: bold; font-family: monospace">
          때로는 밥을 먹듯이 일기를 쓰고 싶어질때가 있죠..
        </p>
      </div>

      <div style="display: inline-flex">
        <p style="font-size: 2rem; font-weight: bold; font-family: monospace">
          당신의 근성장 단백질 계란을 일기에 구워보는건 어떤가요?
        </p>
        <img src="../assets/이미지2.png" alt="" style="width: 30rem" />
      </div>
      <img src="../assets/이미지3.jpg" alt="" style="width: 100%" />
      <p>당신도 늦지 않았습니다.</p>
      <p style="font-size: 3rem">지금 당장 시작하세요!!!</p>
      <div>
        <button @click="moveLogin">시작하기</button>
      </div>
      <div>
        <button @click="moveRegist">아직 아이디가 없으시다구요...?</button>
      </div>
    </section>
  </body>
</template>

<script setup>
import router from "@/router";

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
  height: 5vh;
}

body {
  padding-top: 5vh;
  height: 200vh;
  background-color: rgb(246, 250, 122);
}

.login {
  margin-left: 15rem;
  margin-right: 15rem;

  background-color: aliceblue;
}

div {
  width: 100%;
  background-color: rgb(250, 250, 170);
  justify-content: center;
  display: flex;
}
</style>

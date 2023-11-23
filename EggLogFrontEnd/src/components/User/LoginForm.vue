<template>
  <div class="login-container">
    <div class="form-container">
      <img src="../../assets/계란후라이배경삭제.png" alt="" style="width: 15vw" />
      <br />
      <!--에그로고 글귀-->
      <p style="color : #FFCC00; font-size: 3vw; font-weight: bold; 'Noto Sans KR', sans-serif;">
        EggLog
      </p>
      <input
        type="text"
        v-model.trim="id"
        placeholder="아이디를 입력하세요"
        class="input-field"
        :class="{ 'input-focused': idFocus }"
        @focus="idFocus = true"
        @blur="idFocus = false"
      />
      <br />
      <input
        type="password"
        v-model.trim="pw"
        placeholder="비밀번호를 입력하세요"
        class="input-field"
        :class="{ 'input-focused': pwFocus }"
        @focus="pwFocus = true"
        @blur="pwFocus = false"
        @keyup.enter="login"
      />
      <br />
      <div class="button-container">
        <button @click="login" class="login-button">시작하기</button>
      </div>
    </div>
  </div>
</template>

<!-- <script setup>
import { ref } from "vue";
import router from "@/router";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();

const id = ref("");
const pw = ref("");
const idFocus = ref(false);
const pwFocus = ref(false);

const login = () => {
  const user = {
    id: id.value,
    password: pw.value,
  };

  userStore.Login(user);
};

const moveIntroduce = function () {
  router.push({ name: "IntroducePage" });
};
</script> -->

<!--상준이형 라인-->
<script setup>
import { ref } from "vue";
import router from "@/router";
import { useUserStore } from "@/stores/userStore";
import { useFriendsStore } from "@/stores/friendsStore";

const userStore = useUserStore();
const friendsStore = useFriendsStore();

const id = ref("");
const pw = ref("");

const login = () => {
  const user = {
    id: id.value,
    password: pw.value,
  };
  friendsStore.getFriendsList(user.id);
  userStore.Login(user);
};

const moveIntroduce = function () {
  router.push({ name: "IntroducePage" });
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");




.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 3vw;
  height: 93vh;
  background-color: azure;
}

.form-container {
  text-align: center;
  height: 100%;
  padding-bottom: 10vw;
  padding-top: 10vw;
  padding-left: 10vw;
  padding-right: 10vw;
  display: block;
  background-color: #ffe5d5;
}

.input-field {
  width: 20vw;
  padding: 10px;
  margin: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  outline: none;
  transition: border-color 0.3s ease; /* Add smooth transition effect */
}

.input-focused {
  border-color: #66afe9; /* Change border color when input is focused */
}

.button-container {
  text-align: center;
}

.login-button {
  background-color: #aad1f1;
  color: rgb(0, 0, 0);
  padding: 10px 15px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.6s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
}

.login-button:hover {
  background-color: #fbff0c;
}
</style>

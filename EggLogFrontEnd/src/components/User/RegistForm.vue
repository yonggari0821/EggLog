<template>
  <div class="registration-container">
    <!-- 여기서부터 값 입력 받는 부분들-->
    <form @submit.prevent="submitForm" class="registration-form">
      <div style="display: flex; justify-content: center">
        <img src="../../assets/계란이2배경삭제.png" style="width: 10vw" />
      </div>
      <div style="display: flex; justify-content: center">
        <p
          style="color : rgb(255, 153, 153); font-size: 3vw; font-weight: bold; 'Noto Sans KR', sans-serif;"
        >
          계란이와 시작하기
        </p>
      </div>
      <div class="form-group">
        <div style="display: flex; flex: 2; justify-content: space-around">
          <label for="id">아이디</label>
        </div>
        <div style="flex: 3">
          <input
            type="text"
            id="id"
            v-model="user.id"
            @blur="checkID"
            required
          />
        </div>
        <span v-if="IDExists" class="error-message"
          >이미 사용 중인 아이디입니다.</span
        >
      </div>

      <div class="form-group">
        <div style="display: flex; flex: 2; justify-content: space-around">
          <label for="password">비밀번호</label>
        </div>
        <div style="flex: 3">
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
      </div>

      <div class="form-group">
        <div style="display: flex; flex: 2; justify-content: space-around">
          <label for="passwordConfirm">비밀번호 확인</label>
        </div>
        <div style="flex: 3">
          <input
            type="password"
            id="passwordConfirm"
            v-model="passwordConfirm"
            required
          />
        </div>
        <span v-if="passwordsDoNotMatch" class="error-message"
          >비밀번호가 일치하지 않습니다.</span
        >
      </div>

      <div class="form-group">
        <div style="display: flex; flex: 2; justify-content: space-around">
          <label for="birth">생년월일</label>
        </div>
        <div style="flex: 3">
          <input type="date" id="birth" v-model="user.birth" required />
        </div>
      </div>

      <div class="form-group">
        <div style="display: flex; flex: 2; justify-content: space-around">
          <label for="nickname">닉네임</label>
        </div>
        <div style="flex: 3">
          <input type="text" id="nickname" v-model="user.nickname" required />
        </div>
      </div>

      <div class="form-group">
        <div style="display: flex; flex: 2; justify-content: space-around">
          <label>성별</label>
        </div>
        <div class="gender-options" style="flex: 3">
          <input
            type="radio"
            id="male"
            value="male"
            v-model="user.gender"
            required
          />
          <label for="male">남성</label>

          <input
            type="radio"
            id="female"
            value="female"
            v-model="user.gender"
            required
          />
          <label for="female">여성</label>

          <input
            type="radio"
            id="else"
            value="else"
            v-model="user.gender"
            required
          />
          <label for="else">그 외</label>
        </div>
      </div>

      <div class="button-group">
        <button
          type="button"
          class="btn btn-primary"
          @click="registUser"
          :disabled="IDExists || passwordsDoNotMatch"
          style="margin-right: 8vw"
        >
          등록
        </button>
        <button type="button" class="btn btn-secondary" @click="cancel">
          취소
        </button>
      </div>
    </form>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUserStore();
const user = ref({
  id: "",
  password: "",
  gender: "",
  birth: "",
  regDate: "",
  nickname: "",
});

const passwordConfirm = ref(""); // 비밀번호 확인 v-model
const IDExists = ref(false);
const passwordsDoNotMatch = ref(false);

const checkID = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/user/checkID", {
      params: {
        id: user.value.id,
      },
    });
    IDExists.value = response.data;
  } catch (error) {
    console.error("아이디 중복 검사 에러:", error);
  }
};

const registUser = function () {
  user.value.regDate = store.todayDate;
  console.log(user.value.regDate);
  if (user.value.password !== passwordConfirm.value) {
    passwordsDoNotMatch.value = true;
    return;
  }
  store.createUser(user.value);

  // 비밀번호 확인 및 이메일 중복 여부 초기화
  passwordsDoNotMatch.value = false;
  IDExists.value = false;
  console.log("확인되었습니다");
  alert("회원가입이 완료되었습니다!");
  router.push({ name: "Login" });
};

const cancel = function () {
  router.push({ name: "Login" });
};
</script>

<style scoped>
.registration-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 3vw;
  height: 93vh;
}

.logo {
  width: 3rem;
  cursor: pointer;
}

.registration-form {
  width: 35vw;
  padding-top: 3vw;
  height: 100%;
  background-color: bisque;
}

.form-group {
  margin-bottom: 15px;
  display: flex;
}

.error-message {
  color: red;
  font-size: 0.9rem;
  margin-top: 5px;
}

.gender-options {
  display: flex;
  gap: 10px;
}

.button-group {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>

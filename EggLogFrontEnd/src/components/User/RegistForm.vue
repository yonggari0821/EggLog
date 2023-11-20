<template>
  <div>
    <img
      src="../../assets/egglogo.jpg"
      click="moveIntroduce"
      style="width: 3rem"
    />
    <h2>회원가입</h2>
    <form @submit.prevent="submitForm">
      <label for="id">아이디:</label>
      <input type="text" id="id" v-model="user.id" @blur="checkID" required />
      <span v-if="IDExists">이미 사용 중인 이메일입니다.</span>

      <label for="password">비밀번호:</label>
      <input type="password" id="password" v-model="user.password" required />

      <label for="passwordConfirm">비밀번호 확인:</label>
      <input
        type="password"
        id="passwordConfirm"
        v-model="passwordConfirm"
        required
      />
      <span v-if="passwordsDoNotMatch">비밀번호가 일치하지 않습니다.</span>

      <label for="birth">생년월일:</label>
      <input type="date" id="birth" v-model="user.birth" required />

      <label for="nickname">닉네임:</label>
      <input type="text" id="nickname" v-model="user.nickname" required />

      <label for="gender">성별:</label>
      <input type="checkbox" id="gender" v-model="user.gender" required />

      <!-- <button type="submit" :disabled="emailExists || passwordsDoNotMatch">
        가입하기
      </button> -->
      <button
        type="button"
        class="btn btn-outline-primary"
        @click="registUser"
        :disabled="IDExists || passwordsDoNotMatch"
      >
        등록
      </button>
      <button type="button" class="btn btn-outline-secondary" @click="cancel">
        취소
      </button>
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
  if (user.value.password !== passwordConfirm.value) {
    passwordsDoNotMatch.value = true;
    return;
  }
  store.createUser(user.value);

  // 비밀번호 확인 및 이메일 중복 여부 초기화
  passwordsDoNotMatch.value = false;
  IDExists.value = false;
  console.log("확인되었습니다");
  router.push({ name: "Login" });
};

const cancel = function () {
  router.push({ name: "Login" });
};
</script>

<style scoped>
/* 필요한 스타일링을 추가하세요 */
</style>

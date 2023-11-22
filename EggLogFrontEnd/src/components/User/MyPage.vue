<template>
  <div class="divPadding">
    <div class="mypage-container">
      <h2>마이페이지</h2>
      <span>
        <p style="color: blueviolet">{{ user.nickname }}</p>
        님 반갑습니다</span
      >
      <!-- 아오 일단 그냥 ㄱㄱ-->
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="nickname">닉네임</label>
          <input
            :disabled="!currentPasswordCorrect"
            type="text"
            id="nickname"
            v-model="user.nickname"
            required
          />
        </div>

        <div class="form-group">
          <label for="currentPassword">현재 비밀번호</label>
          <input
            :disabled="currentPasswordCorrect"
            type="password"
            id="currentPassword"
            v-model="currentPassword"
            required
          />
          <span v-if="!currentPasswordCorrect">
            현재 비밀번호가 일치하지 않습니다.
          </span>
        </div>

        <button @click="checkCurrentPassword">비밀번호 확인</button>

        <div class="form-group">
          <label for="newPassword">새 비밀번호</label>
          <input
            :disabled="!currentPasswordCorrect"
            type="password"
            id="newPassword"
            v-model="newPassword"
            required
          />
        </div>

        <div class="form-group">
          <label for="statusMessage">상태 메시지</label>
          <textarea
            :disabled="!currentPasswordCorrect"
            id="statusMessage"
            v-model="user.statusMessage"
          ></textarea>
        </div>

        <button @click="updateDone" class="btn-save">저장</button>
        <button @click="goBack" class="btn-back">뒤로가기</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref, computed, onBeforeMount, onMounted, watch } from "vue";
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";

const store = useUserStore();

const router = useRouter();
const currentPassword = ref("");
const newPassword = ref("");
const currentPasswordCorrect = ref(false);
const user = ref({
  id: null,
  password: null,
  gender: null,
  birth: null,
  nickname: null,
  statusMessage: null,
  profilePicture: null,
});

// 렌더링 이후 실행
onMounted(async () => {
  await store.searchUserById(localStorage.getItem("userid"));
  console.log("searchedUser after searchUserById:", store.searchedUser);
});

// 감시하다가 store.searchedUser.value에 변화가 있다면(즉, searchUserById 실행됐을때) 함수 실행
watch(
  () => store.searchedUser,
  (newValue, oldValue) => {
    if (newValue) {
      user.value = { ...newValue };
    } else {
      console.error("store.searchedUser.value is undefined");
      // 필요한 처리를 추가하세요.
    }
  }
);

// 제출 완료
const updateDone = async () => {
  const response = await axios.post(
    "http://localhost:8080/api/user/token",
    // 현재 비밀번호 값을 encodeURIComponent로 인코딩하여 폼 데이터로 전송
    `password=${encodeURIComponent(newPassword.value)}`,
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    }
  );
  if (currentPasswordCorrect) {
    user.value.password = response.data;
    console.log(newPassword.value);
    // 사용자 변경 사항 저장
    store.updateUser(user.value);

    // 상태 초기화
    currentPasswordCorrect.value = false;

    alert("회원정보 수정이 완료되었습니다. 다시 로그인 해주세요.");
    // 현재 회원정보를 수정해도 localStorage에 있는 id로
    ///////////////////////////////////////////////////////////
    // logout 로직
    // router.push({ name: Login})
    ///////////////////////////////////////////////////////////
  } else {
    alert("비밀번호 확인을 먼저 해주세요.");
  }
}; // 이후 비밀번호, 닉네임 등 null인 경우 제외하는 로직 추가할 것

// 현재 비밀번호 토큰화해서 사용자가 작성한 것과 비교
const checkCurrentPassword = async () => {
  try {
    // axios 요청 헤더에 Content-Type 설정 및 데이터를 폼 데이터로 전송
    const response = await axios.post(
      "http://localhost:8080/api/user/token",
      // 현재 비밀번호 값을 encodeURIComponent로 인코딩하여 폼 데이터로 전송
      `password=${encodeURIComponent(currentPassword.value)}`,
      {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }
    );
    await store.searchUserById(localStorage.getItem("userid"));
    console.log("ㅎㅇㅇ");
    // 서버에서 받은 데이터와 로컬 스토리지에 저장된 'accesstoken'을 비교
    currentPasswordCorrect.value = response.data === user.value.password;
  } catch (error) {
    console.error("비밀번호 확인 실패!", error);
  }
};

const goBack = () => {
  router.push({ name: "MainPage" });
};

// const updateProfilePicture = (event) => {
//   // 프로필 사진 업데이트 로직 구현
//   const file = event.target.files[0];
//   // 파일을 필요한 대로 사용하십시오 (예: 서버에 업로드)
// };
</script>

<style scoped>
/* 필요한 스타일링 추가 */
.divPadding {
  padding-top: 3.3vw;
}

.mypage-container {
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input,
textarea {
  width: 100%;
  padding: 0.5rem;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}

.btn-save,
.btn-back {
  padding: 0.75rem 1rem;
  margin-right: 0.5rem;
  background-color: #aad1f1;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn-save:hover,
.btn-back:hover {
  background-color: #fbff0c;
}
</style>

<template>
    <div>
      <h2>마이페이지</h2>
      <span>{{ user.id }}님 반갑습니다</span>
      <form @submit.prevent="submitForm">
        <label for="nickname">닉네임:</label>
        <input
          :disabled="!currentPasswordCorrect"
          type="text"
          id="nickname"
          v-model="user.nickname"
          required
        />
  
        <label for="currentPassword">현재 비밀번호:</label>
        <!-- 현재 비밀번호를 맞췄다면 input을 비활성화 -->
        <input
          :disabled="currentPasswordCorrect"
          type="password"
          id="currentPassword"
          v-model="currentPassword"
          required
        />
        <!-- 현재 비밀번호를 틀렸다면 -->
        <span v-if="!currentPasswordCorrect"
          >현재 비밀번호가 일치하지 않습니다.</span
        >
  
        <button @click="checkCurrentPassword">비밀번호 확인</button>
  
        <label for="newPassword">새 비밀번호:</label>
        <!-- 원래 비활성화였다가 현재 비밀번호를 맞췄다면 input을 활성화 -->
        <input
          :disabled="!currentPasswordCorrect"
          type="password"
          id="newPassword"
          v-model="newPassword"
          required
        />
  
        <label for="statusMessage">상태 메시지:</label>
        <textarea
          :disabled="!currentPasswordCorrect"
          id="statusMessage"
          v-model="user.statusMessage"
        ></textarea>
  
        <!-- <label for="profilePicture">프로필 사진 변경:</label>
        <input
          :disabled="!currentPasswordCorrect"
          type="file"
          id="profilePicture"
          @change="updateProfilePicture"
        /> -->
  
        <button @click="updateDone">저장</button>
        <button @click="goBack">뒤로가기</button>
      </form>
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
  </style>
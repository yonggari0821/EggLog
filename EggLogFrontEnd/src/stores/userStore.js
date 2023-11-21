import axios from "axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
// import http from "@/util/http-common.js";

const REST_BOARD_API = "http://localhost:8080/api/user";

export const useUserStore = defineStore("user", () => {
  const user = ref({
    id: null,
    password: null,
    gender: null,
    birth: null,
    nickname: null,
    status_message: null,
    profile_picture: null,
  });
  // 로그인하는거고 객체가 들어옴
  // 함수도 변경

  // 로그아웃하면 localstorage 삭제
  // localstorage에 저장한 id를 기반으로 가져오자.
  const setLoginUser = function (inputuser) {
    axios
      .get(`${REST_BOARD_API}/${inputuser}`)
      .then((response) => {
        user.value = response.data;
        router.push({ name: "MainPage" });
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("존재하지 않는 아이디 입니다.");
      });
  }; //


  const setLogout = function () {
    loginUser.value = false;
  };

});

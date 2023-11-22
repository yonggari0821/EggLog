import axios from "axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
// import http from "@/util/http-common.js";

const REST_USER_API = "http://localhost:8080/api/user";

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
  const setLoginUser = function (userId) {
    axios
      .get(`${REST_USER_API}/${userId}`)
      .then((response) => {
        user.value = response.data;
        router.push({ name: "MainPage" });
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("존재하지 않는 아이디 입니다.");
      });
  }; //

  // 테스트용;

  // Nav 표시에 사용할 loginUser
  const loginUser = ref(null);

  // 세션 jwt

  const Login = function (User) {
    console.log(User);
    axios
      .post(`${REST_USER_API}/login`, User)
      // 트루 반환시 localstorage에 정보 저장,
      // false면 alert로 알람처리
      .then((response) => {
        const user = ref({
          id: response.data.id,
          token: response.data.password,
        });
        console.log("유저정보는? " + user);
        localStorage.setItem("userid", user.value.id);
        localStorage.setItem("accesstoken", user.value.token);
        console.log("유저 밸류는 ? " + user.value.id);
        router.push({ name: "MainPage" });
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("로그인에 실패하였습니다.");
      });
  }; // 테스트용

  const searchedUser = ref({
    id: null,
    password: null,
    gender: null,
    birth: null,
    nickname: null,
    status_message: null,
    profile_picture: null,
  });

  const searchUserById = function (id) {
    axios({
      url: `${REST_USER_API}/${id}`,
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      data: user,
    })
      .then((res) => {
        searchedUser.value = res.data;
      })
      .catch((err) => console.log(err));
  };

  // 만들기1
  const createUser = function (user) {
    // 객체임
    console.log(user);
    axios({
      url: `${REST_USER_API}`,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: user,
    })
      .then((response) => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        if (response.data == "true") {
          console.log("회원가입 성공");
        } else {
          console.log("회원가입 실패");
        }
      })
      .catch((err) => {
        console.log("회원가입 오류:", err);
        console.log(err.response);
      });
  };
  const friendsUsersList = ref([
    {
      id: null,
      password: null,
      gender: null,
      birth: null,
      nickname: null,
      status_message: null,
      profile_picture: null,
    },
  ]);

  const getFriendUsers = function (friendIds) {
    console.log(friendIds);
    const encodedFriendIds = friendIds.map((id) => encodeURIComponent(id));
    const url = `${REST_USER_API}/getFriendUsers?friendIds=${encodedFriendIds.join(",")}`;
    axios
      .get(url)
      .then((response) => {
        friendsUsersList.value = response.data;
        console.log(friendsUsersList.value.length);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  return {
    user,
    loginUser,
    setLoginUser,
    Login,
    searchedUser,
    searchUserById,
    createUser,
    friendsUsersList,
    getFriendUsers,
  }; // LoginCheck
});

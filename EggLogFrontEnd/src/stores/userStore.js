import axios from "@/util/http-common";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
import { functions } from "lodash";
// import http from "@/util/http-common.js";

const REST_USER_API = "http://localhost:8080/api/user";

export const useUserStore = defineStore("user", () => {
  const user = ref({
    id: null,
    password: null,
    gender: null,
    birth: null,
    reg_date: null,
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
        router.push({ name: "MainPage" });
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("로그인에 실패하였습니다.");
      });
  }; // 테스트용

  // 회원 정보 수정
  const updateUser = function (user) {
    axios
      .put(`${REST_USER_API}`, user)
      // 트루 반환시 localstorage에 정보 저장,
      // false면 alert로 알람처리
      .then((response) => {
        if (response === "true") {
          alert("회원 정보를 수정 완료했습니다.");
          router.push({ name: "MainPage" });
        }
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("회원 정보 수정을 실패했습니다.");
      });
  }; // 테스트용

  const searchedUser = ref({
    id: null,
    password: null,
    gender: null,
    birth: null,
    reg_date: null,
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
        currentUser.value = res.data;
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
      reg_date: null,
      nickname: null,
      status_message: null,
      profile_picture: null,
    },
  ]);

  const getFriendUsers = function (friendIds) {
    // console.log(friendIds);
    const encodedFriendIds = friendIds.map((id) => encodeURIComponent(id));
    const url = `${REST_USER_API}/getFriendUsers?friendIds=${encodedFriendIds.join(",")}`;
    axios
      .get(url)
      .then((response) => {
        friendsUsersList.value = response.data;
        // console.log(friendsUsersList.value.length);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const today = new Date();
  const year = today.getFullYear();
  const month = today.getMonth() + 1;
  const date = today.getDate();
  const todayDate = year + "-" + month + "-" + date;
  const reg = ref("");

  const getRegDate = async function (id) {
    await axios
      .get(`${REST_USER_API}/reg_date/${id}`)
      .then((response) => {
        reg.value = response.data;
      })
      .catch((err) => {
        console.log("regDate 받아오기 오류:", err);
        console.log(err.response);
      });
  };

  const level = ref(null);
  const exp = ref(null);

  const expCalculate = async () => {
    await getRegDate(localStorage.getItem("userid"));
    const oldDate = new Date(reg.value);
    const newDate = new Date(todayDate);
    let diff = Math.abs(newDate.getTime() - oldDate.getTime());
    diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
    level.value = Math.floor(diff / 30);
    exp.value = diff % 30;
    console.log(exp.value);
  };

  const getLevel = async function (id) {
    await getRegDate(id);
    const oldDate = new Date(reg.value);
    const newDate = new Date(todayDate);
    let diff = Math.abs(newDate.getTime() - oldDate.getTime());
    diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
    return Math.floor(diff / 30);
  };

  const currentUser = ref({
    id: null,
    password: null,
    gender: null,
    birth: null,
    reg_date: null,
    nickname: null,
    status_message: null,
    profile_picture: null,
  });

  return {
    user,
    setLoginUser,
    Login,
    searchedUser,
    searchUserById,
    createUser,
    updateUser,
    friendsUsersList,
    getFriendUsers,
    getRegDate,
    todayDate,
    reg,
    expCalculate,
    level,
    exp,
    getLevel,
    currentUser,
  };
});

import axios from "axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
import http from "@/util/http-common.js";

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
  const setLoginUser = function (inputuser) {
    axios
      .get(`${REST_BOARD_API}/${inputuser.id}`)
      .then((response) => {
        user.value = response.data;
        router.push({ name: "MainPage" });
        console.log(user.value.nickname);
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("존재하지 않는 아이디 입니다.");
      });
  };

  const setLogout = function () {
    loginUser.value = false;
  };

  // 모든 게시글 불러오는 용도1
  const users = ref({});
  const setUsers = function () {
    axios.get(REST_BOARD_API).then((response) => {
      users.value = response.data;
    });
  };

  //검색용도1
  const searchUsers = ref({});
  const searchName = function (search) {
    axios({
      url: `${REST_BOARD_API}/search`,
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      data: search,
    })
      .then((res) => {
        searchUsers.value = res.data;
        router.push(`/user`);
      })
      .catch((err) => console.log(err));
  };
  const searchUserCnt = computed(() => {
    return searchUsers.value.length;
  });

  // 수정용도1
  const updateUser = function (detail) {
    axios({
      url: `${REST_BOARD_API}`,
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      data: detail,
    })
      .then(() => {
        router.push(`/user`);
      })
      .catch((err) => console.log(err));
  };

  // 삭제1
  const deleteUser = function (id) {
    axios({
      url: `${REST_BOARD_API}/${id}`,
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(() => {
        router.push(`/user`);
      })
      .catch((err) => console.log(err));
  };

  // 만들기1
  const createUser = function (userss) {
    // 객체임
    console.log(userss);
    axios({
      url: `${REST_BOARD_API}`,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: userss,
    })
      .then(() => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        router.push({ name: "List" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return { user, setLoginUser };
});

import axios from "@/util/http-common";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
// import router from "@/router";
import http from "@/util/http-common.js";

const REST_REQUEST_API = "http://localhost:8080/api/request";

export const useRequestStore = defineStore("request", () => {
  const request = ref({
    from: null,
    to: null,
  });

  const friendRequestList = ref([
    {
      from: null,
      to: null,
    },
  ]);

  const getFriendRequestList = function (myId) {
    // console.log(myId + "님에게 도착한 친구요청 리스트입니다.");
    axios
      .get(`${REST_REQUEST_API}/${myId}`)
      .then((response) => {
        friendRequestList.value = response.data;
      })
      .catch((err) => {
        console.log("친구 요청 리스트 받아오는데 오류:", err);
        console.log(err.response);
      });
  };

  const flag = ref(false);

  const addRequest = async function (myId, friendId) {
    console.log(myId + " <=> " + friendId);
    request.value.from = myId;
    request.value.to = friendId;
    console.log("from " + request.value.from);
    console.log("to " + request.value.to);
    await axios({
      url: `${REST_REQUEST_API}`,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: request.value,
    })
      .then((response) => {
        if (response.data === true) {
          // alert("친구 요청 성공! 친구의 응답을 기다리세요!")
          console.log("성공의 냄새");
          console.log("제발" + response.data);
          // return "S";
          flag.value = response.data;
          console.log("플래그 밸류여" + flag.value);
        } else {
          // alert("친구 요청 실패! 이미 보낸 요청입니다.");
          // return "F";
          flag.value = false;
        }
      })
      .catch((err) => {
        console.log("친구 요청 오류:", err);
        console.log(err.response);
      });
  };

  const deleteRequest = function (request) {
    axios({
      url: `${REST_REQUEST_API}`,
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      data: request,
    })
      .then((response) => {
        if (response.data == true) {
          console.log("친구 삭제 성공! 친구의 응답을 기다리세요!");
        } else {
          console.log("친구 삭제 실패! 잘못된 요청입니다.");
        }
      })
      .catch((err) => {
        console.log("친구 삭제 오류:", err);
        console.log(err.response);
      });
  };

  return { flag, request, addRequest, friendRequestList, getFriendRequestList, deleteRequest };
});

import axios from "axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
import http from "@/util/http-common.js";

const REST_REQUEST_API = "http://localhost:8080/api/request";

export const useFriendsStore = defineStore("request", () => {
  const requestList = ref([]);

  const getRequestList = function (id) {
    axios
      .get(`${REST_REQUEST_API}/${id}`)
      .then((response) => {
        requestList.value = response.data;
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("요청 리스트 불러오다가 오류가 발생!!.");
      });
  };







  return { friendsList, getFriendsList,  };
});

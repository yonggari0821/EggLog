import axios from "axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
import http from "@/util/http-common.js";

const REST_FRIENDS_API = "http://localhost:8080/api/friends";

export const useFriendsStore = defineStore("friends", () => {
  const friendsList = ref([]);

  const getFriendsList = function (id) {
    axios
      .get(`${REST_FRIENDS_API}/${id}`)
      .then((response) => {
        friendsList.value = response.data;
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
        alert("친구 리스트 불러오다가 오류가 발생!!.");
      });
  };







  return { friendsList, getFriendsList,  };
});

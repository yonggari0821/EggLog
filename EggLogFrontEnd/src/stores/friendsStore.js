import axios from "@/util/http-common";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
// import router from "@/router";
import http from "@/util/http-common.js";

const REST_FRIENDS_API = "http://localhost:8080/api/friends";

export const useFriendsStore = defineStore("friends", () => {
  const friendsList = ref([]);

  const getFriendsList = async function (id) {
    console.log(id);
    try {
      const res = await axios.get(`${REST_FRIENDS_API}/${id}`);
      friendsList.value = res.data;
    } catch (err) {
      console.error(err);
    }
  };

  const addFriends = function (request) {
    console.log(request.myId + " <=> " + request.friendId);
    axios({
      url: `${REST_FRIENDS_API}`,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: request,
    })
      .then((response) => {
        if (response.data == "Fail to be new friends.") {
          console.log("친구 맺기 실패!");
        } else {
          console.log("친구 맺기 성공!");
        }
      })
      .catch((err) => {
        console.log("친구 맺기 오류:", err);
        console.log(err.response);
      });
  };

  const deleteFriends = function (friends) {
    console.log(friends);
    axios({
      url: `${REST_FRIENDS_API}`,
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      data: friends,
    })
      .then((response) => {
        if (response.data === "Success to break off with friend") {
          console.log("친구 삭제 성공!");
        } else {
          console.log("친구 삭제 실패!");
        }
      })
      .catch((err) => {
        console.log("친구 삭제 오류:", err);
        console.log(err.response);
      });
  };

  return { friendsList, getFriendsList, addFriends, deleteFriends };
});

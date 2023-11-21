import axios from "axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
// import http from "@/util/http-common.js";

const REST_DIARY_API = "http://localhost:8080/api/diary";

export const useDiaryStore = defineStore("diary", () => {
  const diary = ref({});

  const getDiary = function (userId, diaryDate) {
    axios
      .get(`${REST_DIARY_API}`)
      .then((response) => {
        diary.value = response.data;
        router.push({ name: "Diary", params: "diary" });
      })
      .catch((err) => {
        console.error(err); // Logging the error for debugging
      });
  }; //

  return { diary, getDiary };
});

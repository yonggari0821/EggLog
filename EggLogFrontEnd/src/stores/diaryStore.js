import axios from "@/util/http-common";
import { defineStore } from "pinia";
import { ref, computed, onMounted } from "vue";
import router from "@/router";
// import http from "@/util/http-common.js";

const REST_DIARY_API = "http://localhost:8080/api/diary";

export const useDiaryStore = defineStore("diary", () => {
  const diary = ref({
    userId: null,
    title: null,
    content: null,
    diaryDate: null,
    // diaryPicture: null,
    hashtag: [],
    location: null,
  });

  const hashtagList = ref(null);
  const getHashtagList = function (someUserId, someDiaryDate) {
    const params = {
      userId: someUserId,
      diaryDate: someDiaryDate,
    };

    console.log("Server request params:", params);
    axios
      .get(`${REST_DIARY_API}`, { params: params })
      .then((response) => {
        if (response.data && response.data.hashtag) {
          console.log(response.data);
          console.log(response.data.hashtag);
          console.log(response.data.hashtag.split(/\s+/g));
          hashtagList.value = response.data.hashtag.split(/\s+/g);
          return true;
        } else {
          hashtagList.value = null;
          return false;
        }
      })

      .catch((err) => {
        console.error("Error in getHashtagList:", err); // Logging the error for debugging
        if (err.response) {
          console.error("Server responded with:", err.response.data);
        }
        throw err;
      });
  };

  const getDiary = function (someUserId, someDiaryDate) {
    const params = {
      userId: someUserId,
      diaryDate: someDiaryDate,
    };

    axios
      .get(`${REST_DIARY_API}`, { params: params })
      .then((response) => {
        if (response.data === null || response.data === "") {
          router.push({
            name: "DiaryRegist",
            params: {
              user_id: someUserId,
              diary_date: someDiaryDate,
            },
          });
        } else {
          diary.value = response.data;
          router.push({
            name: "Diary",
            params: { diary: JSON.stringify(diary.value) },
          });
        }
      })
      .catch((err) => {
        console.error("Error in getDiary:", err); // Logging the error for debugging
        if (err.response) {
          console.error("Server responded with:", err.response.data);
        }
      });
  };

  // const registDiary = async function (diary) {
  //   try {
  //     console.log(diary);
  //     await axios.post(REST_DIARY_API, diary);
  //   } catch (err) {
  //     console.log("게시물 등록 오류:", err);
  //     console.log(err.response);
  //   }
  // };

  // const updateDiary = async function (diary) {
  //   try {
  //     console.log(diary);
  //     await axios.put(REST_DIARY_API, diary);
  //   } catch (err) {
  //     console.log("게시물 수정 오류:", err);
  //     console.log(err.response);
  //   }
  // };

  //추가 부분
  const registDiary = function (diary) {
    console.log("test" + diary);
    try {
      const str = diary.hashtag ? diary.hashtag.join(" ") : "";
      console.log(diary);
      console.log(str);

      const tDiary = {
        userId: diary.userId,
        title: diary.title,
        content: diary.content,
        diaryDate: diary.diaryDate,
        hashtag: str.trim(), // Remove trailing space
        location: diary.location,
      };

      axios.post(REST_DIARY_API, tDiary);
    } catch (err) {
      console.log("게시물 등록 오류:", err);
      console.log(err.response);
    }
  };

  const updateDiary = async function (diary) {
    console.log("test" + diary);
    try {
      const str = (await diary.hashtag) ? diary.hashtag.join(" ") : "";
      console.log(diary);
      console.log(str);

      const tDiary = {
        userId: diary.userId,
        title: diary.title,
        content: diary.content,
        diaryDate: diary.diaryDate,
        hashtag: str.trim(), // Remove trailing space
        location: diary.location,
      };
      await axios.put(REST_DIARY_API, tDiary);
    } catch (err) {
      console.log("게시물 수정 오류:", err);
      console.log(err.response);
    }
  };

  // 여리까리

  const deleteDiary = async function (someUserId, someDiaryDate) {
    const params = {
      userId: someUserId,
      diaryDate: someDiaryDate,
    };
    try {
      await axios.delete(`${REST_DIARY_API}`, { params: params });
    } catch (err) {
      console.log("게시물 삭제 오류:", err);
      console.log(err.response);
    }
  };

  const diaryList = ref([]);

  const getDiaryList = function (userId) {
    axios
      .get(`${REST_DIARY_API}/${userId}`)
      .then((response) => {
        diaryList.value = response.data;
      })
      .catch((err) => {
        console.log("다이어리 리스트 받아오는데 오류:", err);
        console.log(err.response);
      });
  };

  return {
    diary,
    getDiary,
    registDiary,
    updateDiary,
    deleteDiary,
    diaryList,
    getDiaryList,
    getHashtagList,
    hashtagList,
  };
});

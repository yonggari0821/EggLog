<template>
  <div>
    <h2>게시물 등록</h2>
    <form @submit.prevent="submitForm">
      <label for="title">제목:</label>
      <input type="text" id="title" v-model="postDiary.title" required />

      <label for="content">내용:</label>
      <input type="text" id="content" v-model="postDiary.content" required />

      <label for="location">게시물에 현재 위치 넣기</label>
      <input type="checkbox" id="location" checked />

      <button
        type="button"
        class="btn btn-outline-primary"
        @click="registDiary"
      >
        등록
      </button>

      <!--모달 끄기 버튼 필요-->
    </form>
  </div>
</template>

<script setup>
import { useDiaryStore } from "@/stores/diaryStore";
import { onMounted, ref } from "vue";
import { useRouter, useRoute } from "vue-router";

const route = useRoute();
const router = useRouter();
const diaryStore = useDiaryStore();

const postDiary = ref({
  userId: null,
  title: null,
  content: null,
  diaryDate: null, // MainPage에서 넘어올 때 router.params.date 받아오기
  diaryPicture: null,
  location: null,
});

// router.param을 통해 date와 userId 받아오기

const registDiary = function () {
  postDiary.value.userId = route.params.user_id;
  postDiary.value.diaryDate = route.params.diary_date;
  console.log(postDiary.value.userId);
  console.log(postDiary.value.diaryDate);
  diaryStore.registDiary(postDiary.value);
  alert("일기가 작성되었습니다");
  console.log("등록되었습니다.");
  router.push({
    name: "Diary",
    params: { diary: JSON.stringify(postDiary.value) },
  });
  // router.push({ name: "Diary" });
};

onMounted(() => {});
</script>

<style scoped>

</style>
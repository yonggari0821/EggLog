<template>
  <div class="divMargin">
    <div>
      <div style="width: 100%; display: flex; justify-content: center; height: 65vh; margin-top: 12vh; margin-bottom: 15vh;">
  <div style="width: 50%; display: flex; flex-direction: column;">
    <div style="display: inline-flex; width: 100%; flex : 1">
      <div style="display: flex; flex : 1; align-items: center; justify-content: center;">
      <label for="diaryDate">날짜</label>
      </div>
      <div style="display: flex; align-items: center; flex : 4"></div>
      <h1>{{ readDiary.diaryDate }}</h1>
          </div>
    </div>
    
      <label for="title">제목</label>
      <h2>{{ readDiary.title }}</h2>
      <label for="content">내용</label>
      <p>{{ readDiary.content }}</p>
      <label for="location">게시물에 저장된 위치</label>
      <!--위치 API-->
      <button @click="update">수정</button>
      <button @click="deleteDiary">삭제</button>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import { useDiaryStore } from "@/stores/diaryStore";

const diaryStore = useDiaryStore();
const route = useRoute();
const router = useRouter();
const readDiary = ref({
  userId: null,
  title: null,
  content: null,
  diaryDate: null,
  hashtag: [],
  location: null,
});

const update = () => {
  router.push({
    name: "DiaryUpdate",
    params: { diary: JSON.stringify(readDiary.value) },
  });
};

const deleteDiary = async () => {
  await diaryStore.deleteDiary(
    readDiary.value.userId,
    readDiary.value.diaryDate
  );
  router.push({ name: "MainPage" });
};

onMounted(() => {
  readDiary.value = JSON.parse(route.params.diary);
});
</script>

<style scoped>
.divMargin {
  padding-top: 3.3vw;
}
</style>

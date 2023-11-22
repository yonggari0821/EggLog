<template>
  <div class="divPadding">
    <div class="form-container">
      <h2>다이어리 등록</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="title">제목</label>
          <input type="text" id="title" v-model="postDiary.title" required />
        </div>

        <div class="form-group">
          <label for="content">내용</label>
          <!-- 내용 입력 부분에 style 속성을 추가하여 width를 조절합니다. -->
          <input
            type="text"
            id="content"
            v-model="postDiary.content"
            required
            style="width: 100%"
          />
        </div>

        <div class="form-group">
          <label for="location">게시물에 현재 위치 넣기</label>
          <input type="checkbox" id="location" checked />
        </div>

        <button type="button" class="btn btn-primary" @click="registDiary">
          등록
        </button>

        <!-- 모달 끄기 버튼 필요 -->
      </form>
    </div>
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
.divPadding {
  padding-top: 3.3vw;
}

.form-container {
  display: flex;
  flex-direction: column;
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}

.btn {
  padding: 0.75rem 1rem;
  background-color: #aad1f1;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background-color: #fbff0c;
}
</style>

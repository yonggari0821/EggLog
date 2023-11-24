<template>
  <div class="divMargin">
    <div
      style="
        width: 100%;
        display: flex;
        justify-content: center;
        height: 65vh;
        margin-top: 12vh;
        margin-bottom: 12vh;
      "
    >
      <div style="width: 50%; display: flex; flex-direction: column">
        <div style="display: inline-flex; width: 100%; flex: 1">
          <div style="display: flex; flex: 1; align-items: center; justify-content: center">
            <label for="diaryDate">날짜 </label>
          </div>
          <div style="display: flex; align-items: center; flex: 4">
            <p style="width: 88%; background-color: antiquewhite">{{ readDiary.diaryDate }}</p>
          </div>
        </div>

        <!---->
        <div style="display: inline-flex; width: 100%; flex: 1">
          <div style="display: flex; flex: 1; align-items: center; justify-content: center">
            <p>제목</p>
          </div>
          <div style="display: flex; align-items: center; flex: 4">
            <p style="width: 88%; background-color: antiquewhite">{{ readDiary.title }}</p>
          </div>
        </div>
        <div style="display: inline-flex; width: 100%; flex: 3">
          <div style="display: flex; flex: 1; justify-content: center">
            <p>내용</p>
          </div>
          <div style="flex: 4">
            <p
              style="
                max-width: 35vw;
                word-wrap: break-word;
                background-color: antiquewhite;
                height: 100%;
              "
            >
              {{ readDiary.content }}
            </p>
          </div>
          <br />
        </div>
        <div class="hashtags-container" style="display: flex; justify-content: center; flex: 3">
          <div style="display: flex">
            <div class="tagtag" v-for="hashtag in readDiary.hashtag">
              <p style="background-color: aquamarine">{{ hashtag }}</p>
            </div>
          </div>
        </div>
        <!-- 위치 API -->
        <div style="margin-top: auto; display: flex; justify-content: end">
          <div @click="moveMainPage" class="crud">다이어리</div>
          <div @click="update" class="crud">수정</div>
          <div @click="deleteDiary" class="crud">삭제</div>
        </div>
      </div>
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
  // diaryPicture: null,
  hashtag: [],
  location: null,
});

const update = () => {
  diaryStore.updateDiary(readDiary);
  router.push({
    name: "DiaryUpdate",
    params: { diary: JSON.stringify(readDiary.value) },
  });
};

const deleteDiary = async () => {
  await diaryStore.deleteDiary(readDiary.value.userId, readDiary.value.diaryDate);
  alert("삭제되었습니다!");
  router.push({ name: "MainPage" });
};

const moveMainPage = function () {
  router.push({ name: "MainPage" });
};

onMounted(async () => {
  readDiary.value = await JSON.parse(route.params.diary);
  console.log(readDiary);
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@700&family=Noto+Sans+KR:wght@700&display=swap");

@keyframes move {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

.tagtag {
  display: inline-block;
  font-size: 20px;
  padding: 3px;
  margin: 10px;
  border-radius: 15px;
  white-space: nowrap;
}

/* Container styles for hashtags */
.hashtags-container {
  display: flex;
  flex-wrap: wrap;
  gap: 1px; /* Adjust the gap between hashtags as needed */
  align-items: baseline; /* Adjust the alignment as needed */
}

.divMargin {
  padding-top: 6vh;
  background-image: url("../../assets/다이어리배경제거.png");
  background-size: 60% 80%;
  background-position: center;
  background-repeat: no-repeat;
  height: 92vh;

  animation: move 1s ease-in-out;
}

label,
p,
div {
  font-size: 2vh;
  font-weight: bold;
  font-family: "Noto Sans KR";
  font-family: "sans-serif";
}

.crud {
  border: solid 1px gray;
  width: 8vw;
  text-align: center;
  margin-right: 1vw;
  background-color: #dad8ff;
  border-radius: 0px 0px 10px 10px;
  cursor: pointer;
  transition: background-color 0.3s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
  box-shadow: 0 4px 4px -4px black;
}

.crud:hover {
  background-color: #ffe67c;
  color: #295f2e;
}
</style>

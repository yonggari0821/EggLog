<template>
  <div class="divMargin">
    <div
      style="
        width: 100%;
        display: flex;
        justify-content: center;
        height: 65vh;
        margin-top: 12vh;
        margin-bottom: 15vh;
      "
    >
      <div style="width: 50%; display: flex; flex-direction: column">
        <div style="display: inline-flex; width: 100%; flex: 1">
          <div style="display: flex; flex: 1; align-items: center">
            <label for="diaryDate">날짜 </label>
          </div>
          <div style="display: flex; align-items: center; flex: 4">
            <p>{{ readDiary.diaryDate }}</p>
          </div>
        </div>

        <!---->
        <div style="display: inline-flex; width: 100%; flex: 1">
          <div style="display: flex; flex: 1; align-items: center">
            <p>제목</p>
          </div>
          <div style="display: flex; align-items: center; flex: 4">
            <input
              type="text"
              id="title"
              v-model="putDiary.title"
              required
              :placeholder="readDiary.title"
              style="width: 88%"
            />
          </div>
        </div>
        <div style="display: inline-flex; width: 100%; flex: 3">
          <div style="flex: 1">
            <label for="content">내용</label>
          </div>
          <div style="flex: 4">
            <textarea
              id="content"
              v-model="putDiary.content"
              required
              :placeholder="readDiary.content"
              style="
                height: 100%;
                width: 100%;
                max-width: 35vw;
                word-wrap: break-word;
              "
            />
          </div>
        </div>
        <div style="display: inline-flex; width: 100%; flex: 2">
          <label for="location">게시물에 현재 위치 넣기</label>
          <input type="checkbox" id="location" checked />
        </div>
        <div class="form-group">
          <label>해시태그 선택</label>
          <div>
            <label for="upper">
              <input
                id="upper"
                type="checkbox"
                v-model="putDiary.hashtag"
                value="상체"
              />
              상체
            </label>
          </div>
          <div>
            <label for="lower">
              <input
                id="lower"
                type="checkbox"
                v-model="putDiary.hashtag"
                value="하체"
              />
              하체
            </label>
          </div>
          <div>
            <label for="abs">
              <input
                id="abs"
                type="checkbox"
                v-model="putDiary.hashtag"
                value="복근"
              />
              복근
            </label>
          </div>
          <div>
            <label for="body">
              <input
                id="body"
                type="checkbox"
                v-model="putDiary.hashtag"
                value="전신"
              />
              전신
            </label>
          </div>
        </div>
        <div style="margin-top: auto; display: flex; justify-content: end">
          <div @click="updateDiary" class="crud">수정 완료</div>
          <div @click="moveDiary" class="crud">뒤로가기</div>
        </div>
      </div>
      <!-- 위치 API -->
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

const readDiary = ref({
  userId: null,
  title: null,
  content: null,
  diaryDate: null, // MainPage에서 넘어올 때 router.params.date 받아오기
  hashtag: [],
  location: null,
});

const putDiary = ref({
  userId: null,
  title: null,
  content: null,
  diaryDate: null, // MainPage에서 넘어올 때 router.params.date 받아오기
  hashtag: [],
  location: null,
});

// router.param을 통해 date와 userId 받아오기

const moveDiary = function () {
  router.push({
    name: "Diary",
    params: { diary: JSON.stringify(readDiary.value) },
  });
};

const updateDiary = function () {
  diaryStore.updateDiary(putDiary.value);
  alert("일기가 수정되었습니다");
  console.log("수정되었습니다.");
  router.push({
    name: "Diary",
    params: { diary: JSON.stringify(putDiary.value) },
  });
};

onMounted(() => {
  readDiary.value = JSON.parse(route.params.diary);
  putDiary.value = JSON.parse(route.params.diary);
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

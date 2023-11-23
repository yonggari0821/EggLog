<!-- src/components/Calendar.vue -->
<template>
  <div style="height: 100%" class="entire-calender">
    <div>
      <div class="callender">
        <div class="calendar-header">
          <button @click="goToPreviousMonth">&lt;</button>
          <p
            style="
              color: gray;
              font-size: 30px;
              font-family: 'Lato', sans-serif;
              font-family: 'Noto Sans KR', sans-serif;
            "
          >
            {{ currentMonth }}
          </p>
          <button @click="goToNextMonth">&gt;</button>
        </div>
        <div class="tableCss">
          <table class="table">
            <thead>
              <tr>
                <th v-for="day in daysOfWeek" :key="day">{{ day }}</th>
              </tr>
              <!-- day 는 일월화수목금토-->
            </thead>
            <tbody>
              <!-- weeks 는 4개인가봐?-->
              <tr v-for="week in weeks" :key="week">
                <!-- day 는 1 , 2,  3 , 4 와 같은 일수-->
                <td
                  v-for="day in week"
                  :key="day"
                  @click="selectDate(day)"
                  :class="{
                    selected: isDaySelected(day),
                    hasDiary: hasDiary(day),
                  }"
                >
                  <!-- week 의 da 그러니까 음..-->
                  <!--selected css를 활성화시키겠다.-->
                  <!--내생각엔 getHashtagList 함수로 v-if를 해버리면 저 날짜에 diary가 없어도
                    계속 시도해보니까 따로 이번달 diary를 받아서 해당 날짜에만 true를 반환하는
                    함수가 있으면 좋을 것 같다-->

                  <!-- {{ getHashtagList(day) }} -->
                  <!-- </div> -->
                  {{ day > 0 ? day : "" }}
                  <div v-if="getHashtagList(day)">
                    <div v-for="hashtagA in hashtagList" :key="hashtagA">
                      {{ hashtagA }}
                    </div>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useDiaryStore } from "@/stores/diaryStore";
import router from "../../../router";

const currentDate = ref(new Date());
const selectedDate = ref(null);
const store = useDiaryStore();
const userId = localStorage.getItem("userid");
const diaryList = computed(() => store.diaryList);
const hashtagList = computed(() => {
  // store.hashtagList;
  if (diary.value && diary.value.hashtag) {
    return diary.value.hashtag.split(" ");
  }
  return [];
});

const diary = ref(null);

const getHashtagList = (day) => {
  diary.value = hasDiary(day);
  if (diary.value && diary.value.hashtag) {
    return true;
  }
  return false;

  // const params = {
  //   userId: userId,
  //   diaryDate: hashtagDate(day),
  // };

  // console.log("Request params:", params);

  // await store.getHashtagList(userId, hashtagDate(day));
};

const currentMonth = computed(() => {
  return new Intl.DateTimeFormat("en", {
    month: "long",
    year: "numeric",
  }).format(currentDate.value);
});

const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const weeks = computed(() => {
  const firstDayOfMonth = new Date(
    currentDate.value.getFullYear(),
    currentDate.value.getMonth(),
    1
  );
  const lastDayOfMonth = new Date(
    currentDate.value.getFullYear(),
    currentDate.value.getMonth() + 1,
    0
  );
  const firstDayOfWeek = firstDayOfMonth.getDay();

  let days = [];
  let currentWeek = [];

  for (let i = 0; i < firstDayOfWeek; i++) {
    currentWeek.push(0);
  }

  for (let day = 1; day <= lastDayOfMonth.getDate(); day++) {
    currentWeek.push(day);

    if (currentWeek.length === 7) {
      days.push([...currentWeek]);
      currentWeek = [];
    }
  }

  return days;
});

// const hashtagDate = (day) => {
//   if (!day || day < 1) {
//     return null;
//   }
//   const month =
//     currentDate.value.getMonth() + 1 < 10
//       ? `0${currentDate.value.getMonth() + 1}`
//       : `${currentDate.value.getMonth() + 1}`;

//   const tday = day < 10 ? `0${day}` : `${day}`;
//   console.log("tday:" + tday);
//   console.log("month:" + month);
//   return `${currentDate.value.getFullYear()}${month}${tday}`;
// };

// selectDate day란
// 해당일을 누르면 true로 해줌 selectedDate 값을
// 여기서의 day는 1, 2 ,3 ,4 ,5 임.
const selectDate = (day) => {
  selectedDate.value =
    day > 0
      ? new Date(
          currentDate.value.getFullYear(),
          currentDate.value.getMonth(),
          day
        )
      : null;
  const month = ref(null);
  const tday = ref(null);

  if (selectedDate.value.getMonth() + 1 < 10) {
    month.value = `0${selectedDate.value.getMonth() + 1}`;
  } else {
    month.value = `${selectedDate.value.getMonth() + 1}`;
  }

  if (selectedDate.value.getDate() < 10) {
    tday.value = `0${selectedDate.value.getDate()}`;
  } else {
    tday.value = `${selectedDate.value.getDate()}`;
  }

  const useDate = `${selectedDate.value.getFullYear()}${month.value}${
    tday.value
  }`;

  // // 형함수명();
  store.getDiary(userId, useDate);
};

// selecteddate가 true로 된 날에 대한 액션
const isDaySelected = (day) => {
  if (!selectedDate.value) return false;
  return (
    selectedDate.value.getDate() === day &&
    selectedDate.value.getMonth() === currentDate.value.getMonth() &&
    selectedDate.value.getFullYear() === currentDate.value.getFullYear()
  );
};

const goToPreviousMonth = () => {
  currentDate.value = new Date(
    currentDate.value.getFullYear(),
    currentDate.value.getMonth() - 1,
    1
  );
};

const goToNextMonth = () => {
  currentDate.value = new Date(
    currentDate.value.getFullYear(),
    currentDate.value.getMonth() + 1,
    1
  );
};

const hasDiary = function (day) {
  const year = currentDate.value.getFullYear();
  const month = currentDate.value.getMonth() + 1;
  const paddedMonth = month < 10 ? `0${month}` : `${month}`;
  const formattedDate = `${year}-${paddedMonth}-${day < 10 ? `0${day}` : day}`;

  const foundDiary = diaryList.value.find(
    (diary) => diary.diaryDate === formattedDate
  );

  return foundDiary || null; // 일기가 없을 경우에는 null을 반환
};
onMounted(() => {
  store.getDiaryList(userId);
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

.callender {
  padding: 1vw;
  height: 100%;
  display: flex;
  flex-direction: column;

  overflow: auto;
}
.calendar-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: black;
  flex: 1;
}

.tableCss {
  width: 100%;
  height: 100%;
  flex: 4;
}

th {
  padding: 10px;
  border: 1px solid gray;
  text-align: center;
  background-color: white;
  color: gray;
}
td {
  padding: 1vw;
  border: 1px solid gray;
  text-align: center;
  height: 100px;
  background-color: white;
  color: gray;
}

.selected {
  background-color: rgb(238, 255, 0);
  color: #000000;
  cursor: pointer;
}

button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 1.5rem;
}

button:hover {
  color: #007bff;
}

.entire-calender {
  background-color: #ffe5d5;
  border: 1px solid gray; /* 테두리 속성 추가 */
  border-radius: 80px 80px 10px 10px;
  padding-top: 22px;
  margin: 10px;
  height: 40vh;

  animation: move 1s ease-in-out;
}

.hasDiary {
  background-color: rgb(224, 236, 52);
  color: #000000;
  cursor: pointer;
}
</style>

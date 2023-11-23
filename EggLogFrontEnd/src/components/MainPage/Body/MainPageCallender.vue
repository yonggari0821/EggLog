<!-- src/components/Calendar.vue -->
<template>
  <div style="height: 100%" class="entire-calender">
    <h1>{{ currentUser.nickname }} ({{ currentUser.id }})</h1>
    <h2>의 달력입니다.</h2>
    <div>
      <div class="callender">
        <div class="calendar-header">
          <button @click="goToPreviousMonth">&lt;</button>
          <p
            style="
              color: #333d79;
              font-size: 60px;
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
                <th v-for="day in daysOfWeek" :key="day">
                  <p v-if="day === `Sun`" style="color: red">{{ day }}</p>
                  <p v-else-if="day === `Sat`" style="color: blueviolet">
                    {{ day }}
                  </p>
                  <p v-else>{{ day }}</p>
                </th>
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
import { useUserStore } from "@/stores/userStore";
import router from "../../../router";
const userStore = useUserStore();

const user = computed(() => userStore.user);
const currentUser = computed(() => userStore.currentUser);

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

  //  return diaryList.value.some((diary) => diary.diaryDate === formattedDate);
  const foundDiary = diaryList.value.find(
    (diary) => diary.diaryDate === formattedDate
  );

  return foundDiary || null; // 일기가 없을 경우에는 null을 반환
};

onMounted(async () => {
  await userStore.setLoginUser(localStorage.getItem("userid"));
  await store.getDiaryList(localStorage.getItem("userid"));
  // const nowUser = computed (() => userStore.user);
  // console.log(nowUser);
  if (currentUser.id == null) {
    userStore.searchUserById(localStorage.getItem("userid"));
  }
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
/* 
@keyframes move2 {
  0% {
    background-color: #e0e0e0;
  }
  50% {
    background-color: #ffff33;
  }
  100% {
    background-color: #e0e0e0;
  }
} */

p,
div {
  font-family: "Noto Sans KR";
  font-family: "sans-serif";
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
  border: 1px solid #333d79;
  text-align: center;
  background-color: white;
  color: #333d79;
}
td {
  padding: 1vw;
  border: 1px solid #333d79;
  height: 100px;
  background-color: #fefefe;
  color: #333d79;
  transition: background-color 0.3s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
  font-size: 1vw;
}

td:hover {
  background-color: #ffe67c;
  color: #295f2e;
}

.selected {
  background-color: rgb(35, 36, 30);
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
  background-color: #fefefe;
  border: 1px solid #333d79; /* 테두리 속성 추가 */

  border-radius: 80px 80px 10px 10px;
  padding-top: 22px;
  margin: 10px;
  height: 40vh;

  animation: move 1s ease-in-out;
}

.hasDiary {
  background-color: #8aaae5;
  color: #fefefe;
  cursor: pointer;
}
</style>

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
                  <div v-for="hashtag in store.hashtagList" :key="hashtag">
                    {{ hashtag }}
                  </div>
                  {{ day > 0 ? day : "" }}
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

// selectDate day란
// 해당일을 누르면 true로 해줌 selectedDate 값을
// 여기서의 day는 1, 2 ,3 ,4 ,5 임.
const selectDate = (day) => {
  console.log("오늘 월은 !??" + currentMonth.value);
  console.log("weeks야 !!" + weeks.value);
  console.log("CurrentDate.value 야 !!" + currentDate.value);
  console.log("오늘 날짜는 !? " + day);
  console.log("연을 불러오자 !!" + currentDate.value.getFullYear());
  console.log("월을 불러오자 !!" + currentDate.value.getMonth());
  console.log("일을 불러오자 !!" + currentDate.value.getDay());
  selectedDate.value =
    day > 0
      ? new Date(
          currentDate.value.getFullYear(),
          currentDate.value.getMonth(),
          day
        )
      : null;

  console.log("selectedDate의 값은?" + selectedDate.value);

  console.log("선택된 연을 불러오자 !!" + selectedDate.value.getFullYear());
  console.log("선택된 월을 불러오자 !!" + (selectedDate.value.getMonth() + 1));
  console.log("선택된 일을 불러오자 !!" + selectedDate.value.getDate());

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
  console.log("내가 보내줄 데이터야 ! " + useDate);

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
  const month = currentDate.value.getMonth() + 1; // 월은 0부터 시작하므로 +1 해줍니다.
  const paddedMonth = month < 10 ? `0${month}` : `${month}`;
  const formattedDate = `${year}-${paddedMonth}-${day < 10 ? `0${day}` : day}`;

  return diaryList.value.some((diary) => diary.diaryDate === formattedDate);
};

onMounted(() => {
  store.getDiaryList(userId);
  store.getHashtagList(userId, date);
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

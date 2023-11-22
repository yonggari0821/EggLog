<!-- src/components/Calendar.vue -->
<template>
  <div>
    <div class="calendar-header">
      <button @click="goToPreviousMonth">&lt;</button>
      <h2>{{ currentMonth }}</h2>
      <button @click="goToNextMonth">&gt;</button>
    </div>
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
            :class="{ selected: isDaySelected(day) }"
          >
            <!-- week 의 da 그러니까 음..-->
            <!--selected css를 활성화시키겠다.-->

            {{ day > 0 ? day : "" }}
          </td>
        </tr>
      </tbody>
    </table>
    <div></div>
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

const currentMonth = computed(() => {
  return new Intl.DateTimeFormat("kr", {
    month: "long",
    year: "numeric",
  }).format(currentDate.value);
});

const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

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

onMounted(() => {
  // selectDate(currentDate.value.getDate());
});
</script>

<style scoped>
.calendar-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: rgb(184, 110, 67);
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: center;
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
</style>
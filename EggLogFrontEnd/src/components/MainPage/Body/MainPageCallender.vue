<!-- src/components/Calendar.vue -->
<template>
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
  <div>
    <!-- 모달 창 입력시작-->
    <button @click="openModal">Open Modal</button>
    <div v-if="isModalOpen" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <!-- 모달 내용 -->
        <p>Modal Content Here</p>
      </div>
    </div>
    <!-- 모달 창 입력 종료-->
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
// 모달연습
// 모달 관련 상태
const isModalOpen = ref(false);

// 모달 열기
const openModal = () => {
  isModalOpen.value = true;
};

// 모달 닫기
const closeModal = () => {
  isModalOpen.value = false;
};

// 모달 종료
const currentDate = ref(new Date());
const selectedDate = ref(null);

const currentMonth = computed(() => {
  return new Intl.DateTimeFormat("kr", { month: "long", year: "numeric" }).format(
    currentDate.value
  );
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
  selectedDate.value =
    day > 0 ? new Date(currentDate.value.getFullYear(), currentDate.value.getMonth(), day) : null;
  const useDate = `${selectedDate.value.getFullYear()}${
    selectedDate.value.getMonth() + 1
  }${selectedDate.value.getDate()}`; // 선택날짜 변수 useDate

  // 여기에 모달창을 띄우는 함수
  // 형함수명();
};

// selecteddate가 true로 된 날에 대한 액션
const isDaySelected = (day) => {
  if (!selectedDate.value) return false; // 선택날짜가 비어있으면 false
  return (
    selectedDate.value.getDate() === day &&
    selectedDate.value.getMonth() === currentDate.value.getMonth() &&
    selectedDate.value.getFullYear() === currentDate.value.getFullYear()
  ); // 아니라면 true를 해주고 노란색으로 표시~
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
  selectDate(currentDate.value.getDate());
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

/* 모달 스타일 */
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  max-width: 600px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
</style>

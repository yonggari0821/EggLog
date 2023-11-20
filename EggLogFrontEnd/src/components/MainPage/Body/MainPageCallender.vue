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
      </thead>
      <tbody>
        <tr v-for="week in weeks" :key="week">
          <td
            v-for="day in week"
            :key="day"
            @click="selectDate(day)"
            :class="{ selected: isDaySelected(day) }"
          >
            <!--selected css를 활성화시키겠다.-->
            {{ day > 0 ? day : "" }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";

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

// 해당일을 누르면 true로 해줌 selectedDate 값을
const selectDate = (day) => {
  selectedDate.value =
    day > 0 ? new Date(currentDate.value.getFullYear(), currentDate.value.getMonth(), day) : null;
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
</style>

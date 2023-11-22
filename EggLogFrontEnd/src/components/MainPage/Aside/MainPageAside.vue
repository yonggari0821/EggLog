<template>
  <div>
    <div class="Aside">
      <div class="half">
        <MainPageAsideFriendList />
      </div>
      <div class="half">
        <MainPageAsideHashTag />
      </div>
    </div>
  </div>
</template>

<script setup>
import MainPageAsideFriendList from "./MainPageAsideFriendList.vue";
import MainPageAsideHashTag from "./MainPageAsideHashTag.vue";
</script>

<style scoped>
.Aside {
  background-color: aqua;
  display: flex;
  flex-direction: column; /* Make it a row to split horizontally */
  height: 100%;
}

.half {
  flex: 1; /* Each half takes up equal space */
  box-sizing: border-box; /* Ensure the border width is included in the total width */
}
</style>

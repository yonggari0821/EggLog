<template>
  <div class="Nav">
    <MainPageNavExp />
    <MainPageNavInfo />
  </div>
</template>

<script setup>
import MainPageNavExp from "./MainPageNavExp.vue";
import MainPageNavInfo from "./MainPageNavInfo.vue";
</script>

<style scoped>
.Nav {
  background-color: yellow;
  display: flex;
  width: 100%;
  padding-top: 5vh;
}

/* Media query for smaller screens */
@media (max-width: 600px) {
  .Nav {
    flex-direction: column;
    /* Add additional styles for smaller screens if needed */
  }
}
</style>

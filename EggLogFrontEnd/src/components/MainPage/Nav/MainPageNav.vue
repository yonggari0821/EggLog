<template>
  <div class="Nav">
    <div style="width: 80%">
      <MainPageNavExp />
    </div>
    <div style="width: 20%">
      <MainPageNavInfo />
    </div>
  </div>
</template>

<script setup>
import MainPageNavExp from "./MainPageNavExp.vue";
import MainPageNavInfo from "./MainPageNavInfo.vue";
</script>

<style scoped>
.Nav {
  background-color: azure;
  display: flex;
  width: 100%;
  padding-top: 3.4vw;
}

/* Media query for smaller screens */
@media (max-width: 600px) {
  .Nav {
    flex-direction: column;
    /* Add additional styles for smaller screens if needed */
  }
}
</style>

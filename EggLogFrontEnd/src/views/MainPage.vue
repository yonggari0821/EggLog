<template>
  <div>
    <nav><MainPageNav /></nav>
    <section class="main-page">
      <body class="main-section">
        <MainPageSection class="Height" />
      </body>
      <aside class="main-aside">
        <MainPageAside class="Height" />
      </aside>
    </section>
    <footer>
      <MainPageFooter />
    </footer>
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import MainPageNav from "../components/MainPage/Nav/MainPageNav.vue";
import MainPageFooter from "../components/MainPage/Footer/MainPageFooter.vue";
import MainPageAside from "../components/MainPage/Aside/MainPageAside.vue";
import MainPageSection from "../components/MainPage/Body/MainPageSection.vue";
</script>

<style scoped>
.main-page {
  display: flex;
  height: 85vh;
}

.main-section {
  width: 80%; /* 80% */
  background-color: brown;
}

.main-aside {
  width: 20%; /* 20% */
}

.Height {
  height: 100%;
}
</style>

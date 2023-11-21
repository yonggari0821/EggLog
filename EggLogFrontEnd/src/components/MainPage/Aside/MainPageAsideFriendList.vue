<template>
  <div class="friend">
    <div>
      <p>친구리스트</p>
      <div class="space for friends">
        <div v-if="friendsList.length > 3">친구 많으니깐 페이지</div>
        <div v-else-if="friendsList.length > 0">
          친구 적당히 있으니깐 페이징은 없이
          {{ friendsList[0] }}
        </div>
        <div v-else>친구 좀 사겨라</div>
        <button @click="moveToFriendsRequest()">친구 추가 하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";
import { onMounted, computed, onBeforeMount } from "vue"; // 희
import { useFriendsStore } from "@/stores/friendsStore";

const friendsStore = useFriendsStore();

const friendsList = computed(() => friendsStore.friendsList);

const moveToFriendsRequest = function () {
  router.push({ name: "FriendsRequest" });
};
</script>

<style scoped>
.friend {
  border: 1px solid red;
  height: 100%;
  box-sizing: border-box; /* Ensure the border width is included in the total height */
}
</style>

<template>
  <div>
    <div class="searchZone">
      친구의 아이디를 정확하게 입력해주세요! &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" v-model="searchInput" placeholder="닉네임 말고 아이디!" /> &nbsp;&nbsp;
      <button @click="searchWithInput()">검색</button>
    </div>
    <div class="searchResultZone">
      <div v-if="searchedUser.value == null || searchedUser.value.length == 0" class="foundNoUser">
        <p>검색된 유저가 없습니다! 다시 한 번 정확히 검색해주세요!</p>
      </div>
      <div v-else class="FoundUser">
        <div class="userFound">
          <img :src="searchedUser.profile_picture" />
          <p>{{ searchedUser.nickname }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// import router from '@/router';
import { useFriendsStore } from "@/stores/friendsStore";
import { useUserStore } from "@/stores/userStore";
import { ref, computed } from "vue";

const friendsStore = useFriendsStore();
const userStore = useUserStore();

const friendsList = computed(() => friendsStore.friendsList);
const searchedUser = computed(() => userStore.searchedUser);

const searchInput = ref("");
const searchWithInput = function () {
  console.log(searchInput.value);
  userStore.searchUserById(searchInput.value);
};
</script>

<style scoped>
.searchZone {
  background-color: bisque;
  height: 10vh;
  text-align: center;
}

input {
  height: 5vh;
  text-align: center;
  align-items: center;
}
</style>

<template>
  <div class="friend">
    <div class="makeCenter">
      <p>친구리스트</p>
      <div class="space for friends">
        <div v-if="friendsList.length > 3">친구 많으니깐 페이지</div>
        <div v-else-if="friendsList.length > 0">
          친구 적당히 있으니깐 페이징은 없이
          <div v-for="friend in friendsList" class="eachFriend">
            <p>친구 아이디 : {{ friend }}</p>
            <img src="../../../assets/trash.png" alt="친구 삭제 버튼" class="settingButton" @click="requestDelete(friend)">
          </div>
          
        </div>
        <div v-else>친구 좀 사겨라</div>
        <br>
        <button @click="moveToFriendsRequest()" >친구 추가 하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";
import { onMounted, computed, onBeforeMount } from "vue"; // 희
import { useUserStore } from "../../../stores/userStore";
import { useFriendsStore } from "@/stores/friendsStore";

const friendsStore = useFriendsStore();
const userStore = useUserStore();

const friendsList = computed(() => friendsStore.friendsList);
const friendIds = friendsList.value.map(friend => friend.friendId);

// friendsList를 보내서
// 해당 friend들의 이름과 사진을 받아와야 함

const moveToFriendsRequest = function () {
  router.push({ name: "FriendsRequest" });
};

// eachFriend 클릭 시 해당 친구의 다이어리로 이동하기

</script>

<style scoped>
.friend {
  border: 1px solid red;
  height: 100%;
  box-sizing: border-box; /* Ensure the border width is included in the total height */
}

.makeCenter {
  text-align: center;
}

.eachFriend {
  border: 2px solid black;
  height: 7vh;
  text-align: center;
  margin-top: 8px;
  display: flex;
  justify-content: space-around;
}

.settingButton {
  /* background: url("@/assets/trash.jpg") no-repeat; */
  /* border: none; */
  width: 3vw;
  height: 3vh;
  cursor: pointer;
}
</style>

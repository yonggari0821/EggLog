<template>
  <div class="divMargin">
    <div class="searchZone">
      친구의 아이디를 정확하게 입력해주세요! &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" v-model="searchInput" placeholder="닉네임 말고 아이디!" /> &nbsp;&nbsp;
      <button @click="searchWithInput()">검색</button>
    </div>
    <div class="searchResultZone">
      <div v-if="!searchedUser" class="foundNoUser">
        <p>검색된 유저가 없습니다! 다시 한 번 정확히 검색해주세요!</p>
      </div>
      <div v-else class="FoundAUser">
        <div class="userFound">
          <img :src="searchedUser.profile_picture" />
          <div>{{ searchedUser.nickname }}</div>
          <div>
            <img
              src="@/assets/add.png"
              alt="친구 등록 버튼"
              class="addButton"
              @click="requestAdd(searchedUser.id)"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import { useFriendsStore } from "@/stores/friendsStore";
import { useUserStore } from "@/stores/userStore";
import { useRequestStore } from "@/stores/requestStore";
import { ref, computed } from "vue";

const friendsStore = useFriendsStore();
const userStore = useUserStore();
const requestStore = useRequestStore();

const friendsList = computed(() => friendsStore.friendsList);
const searchedUser = computed(() => userStore.searchedUser);
const flagbada = computed(() => requestStore.flag);

const searchInput = ref("");
const searchWithInput = async function () {
  console.log(searchInput.value + "로 유저 검색");
  await userStore.searchUserById(searchInput.value);
  console.log(searchedUser.value);
};

const requestAdd = async function (friendId) {
  console.log(friendId + "에게 친구 요청 보내기");
  await requestStore.addRequest(localStorage.getItem("userid"), friendId);
  console.log("나 플래그여 " + flagbada.value);
  if (flagbada.value === true) {
    alert("친구 요청 성공!");
    router.push({ name: "MainPage" });
  } else alert("친구 요청 실패!");
};
</script>

<style scoped>
.divMargin {
  padding-top: 3.5vw;
}
.searchZone {
  background-color: bisque;
  height: 10vh;
  text-align: center;
}

.addButton {
  width: 3vw;
  height: 3vh;
  cursor: pointer;
}

.userFound {
  display: inline-block;
}

input {
  height: 5vh;
  text-align: center;
  align-items: center;
}
</style>

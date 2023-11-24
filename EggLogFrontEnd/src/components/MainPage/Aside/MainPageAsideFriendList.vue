<template>
  <div class="friend">
    <div class="makeCenter">
      <p style="text-align: center; font-size: 4vh; font-weight: bold">일기리스트</p>
      <div class="space for friends">
        <div
          v-if="friendRequestList.length > 0 && friendRequestList[0].from != null"
          style="display: flex; justify-content: center"
        >
          <div style="flex: 3; text-align: center; font-size: 1.2vw">
            친구 요청이 {{ friendRequestList.length }} 개 존재합니다.
          </div>
          <div style="display: flex; flex: 1; justify-content: center; align-items: center">
            <p @click="openModal" class="watchDiary" style="font-size: 1vw">확인 하기</p>
          </div>
        </div>

        <div class="friendDiv" style="display: flex">
          <div style="display: flex; flex: 4">
            <div style="display: flex; flex: 1; justify-content: center">
              <div v-if="userLevel >= 3">
                <img src="@/assets/3달이상.png" />
              </div>
              <div v-else-if="userLevel == 2">
                <img src="@/assets/2달이상3달미만.png" />
              </div>
              <div v-else-if="userLevel == 1">
                <img src="@/assets/1달이상2달미만.png" />
              </div>
              <div v-else>
                <img src="@/assets/1달미만.png" />
              </div>
            </div>

            <div style="flex: 1.5">
              <p>내 아이디</p>
              <p>{{ user.id }}</p>
            </div>
            <div style="flex: 1.5">
              <p>내 닉네임</p>
              <p>{{ user.nickname }}</p>
            </div>
          </div>
          <div style="flex: 1; display: flex; align-items: center; justify-content: center">
            <p @click="toMain()" class="watchDiary">보기</p>
          </div>
        </div>

        <div id="modal">
          <div
            v-for="request in friendRequestList"
            class="eachRequest"
            style="display: inline-flex; width: 25vw"
          >
            <p>{{ request.from }}</p>
            <button @click="beFriends(request)">수락</button>
            <button @click="denyFriends(request)">거부</button>
          </div>
          <button @click="closeModal()" class="close-button">닫기</button>
        </div>

        <br />

        <div v-if="friendsList.length > 3">친구 많으니깐 페이지</div>
        <div v-else-if="friendsList.length > 0">
          <div
            v-for="friendUser in friendsUsersList"
            :key="friendUser.id"
            class="friendDiv"
            style="display: inline-flex"
          >
            <div style="display: flex; flex: 4">
              <div style="flex: 1; display: flex; justify-content: center">
                <img v-if="getFriendImagePath(friendUser.regDate) >= 3" :src="level4" />
                <img v-else-if="getFriendImagePath(friendUser.regDate) >= 2" :src="level3" />
                <img v-else-if="getFriendImagePath(friendUser.regDate) >= 1" :src="level2" />
                <img v-else :src="level1" />
              </div>
              <div style="flex: 1.5">
                <p>친구 아이디</p>
                <p>{{ friendUser.id }}</p>
              </div>
              <div style="flex: 1.5">
                <p>친구 닉네임</p>
                <p>
                  {{ friendUser.nickname }}
                </p>
              </div>
            </div>
            <div style="display: flex; flex: 1; align-items: center; justify-content: center">
              <div
                style="
                  display: flex;
                  justify-content: center;
                  flex-direction: column;
                  align-items: center;
                "
              >
                <div>
                  <p
                    @click="moveToFriendDiary(friendUser.id, friendUser.nickname)"
                    class="watchDiary"
                  >
                    보기
                  </p>
                </div>
                <div>
                  <p @click="requestDelete(friendUser.id)" class="deleteDiary">삭제</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-else>친구 좀 사겨라</div>
        <br />
        <!-- <button @click="moveToFriendsRequest()">친구 추가 하기</button> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";
import { onMounted, computed, onBeforeMount } from "vue"; // 희
import { useUserStore } from "@/stores/userStore";
import { useFriendsStore } from "@/stores/friendsStore";
import { useRequestStore } from "@/stores/requestStore";
import { useDiaryStore } from "@/stores/diaryStore";

import level1 from "@/assets/1달미만.png";
import level2 from "@/assets/1달이상2달미만.png";
import level3 from "@/assets/2달이상3달미만.png";
import level4 from "@/assets/3달이상.png";

// const level1 = "@/assets/1달미만.png";
// const level2 = "@/assets/1달이상2달미만.png";
// const level3 = "@/assets/2달이상3달미만.png";
// const level4 = "@/assets/3달이상.png";
// const levelArr =
// [
//   null,
//   require("@/assets/1달미만.png"),
//   require("@/assets/1달이상2달미만.png"),
//   require("@/assets/2달이상3달미만.png"),
//   require("@/assets/3달이상.png")
// ];

const friendsStore = useFriendsStore();
const userStore = useUserStore();
const requestStore = useRequestStore();
const diaryStore = useDiaryStore();

const user = computed(() => userStore.user);
const userLevel = computed(() => userStore.level);
const currentUser = computed(() => userStore.currentUser);

const friendsList = computed(() => friendsStore.friendsList);
const friendsUsersList = computed(() => userStore.friendsUsersList);
const friendRequestList = computed(() => requestStore.friendRequestList);

const getFriendImagePath = function (regDate) {
  const today = new Date();
  const year = today.getFullYear();
  const month = today.getMonth() + 1;
  const date = today.getDate();
  const todayDate = new Date(year + "-" + month + "-" + date);
  const registrationDate = new Date(regDate);
  const diff = Math.ceil(
    (todayDate.getTime() - registrationDate.getTime()) / (1000 * 60 * 60 * 24)
  );

  console.log("diff = " + diff);
  return Math.floor(diff / 30);
};

// 해당 친구의 다이어리로 이동하는 함수
const moveToFriendDiary = async function (friendId, friendNickname) {
  alert(friendNickname + "의 일기로 이동합니다.");
  await diaryStore.getDiaryList(friendId);
  userStore.currentUser = await userStore.searchUserById(friendId);
};

// 친구 요청 보내기 위해서 친구 검색 화면으로 이동
const moveToFriendsRequest = function () {
  router.push({ name: "FriendsRequest" });
};

const toMain = async function () {
  alert("본인 다이어리로 돌아가겠습니다!");
  await diaryStore.getDiaryList(localStorage.getItem("userid"));
  userStore.currentUser = await userStore.searchUserById(localStorage.getItem("userid"));
};

// 새로고침시마다 아래 사항들 리뉴얼
onMounted(async () => {
  await friendsStore.getFriendsList(localStorage.getItem("userid")); // 친구 리스트
  const friendIds = friendsStore.friendsList;
  userStore.getFriendUsers(friendIds); // 친구들의 유저정보들
  console.log(friendsUsersList);
  await requestStore.getFriendRequestList(localStorage.getItem("userid")); // 친구 요청 리스트
  console.log(friendRequestList.value.length);
  await userStore.expCalculate();
});

// 모달 열기
function openModal() {
  document.getElementById("modal").style.display = "block";
}
// 모달 달기
function closeModal() {
  document.getElementById("modal").style.display = "none";
}

// 수락 누르면
// 해당 요청 삭제 및 해당 친구와 친구되기

function beFriends(request) {
  friendsStore.addFriends(request);
  requestStore.deleteRequest(request);
  location.reload();
}

// 거부 누르면 요청만 삭제하고 친구는 안됨!
function denyFriends(request) {
  requestStore.deleteRequest(request);
  location.reload();
}

// eachFriend 클릭 시 해당 친구의 다이어리로 이동하기

// 친구 삭제 버튼 클릭 시 친구 삭제하기 + MainPage로 이동하기
// request 필요 없음!
const requestDelete = function (friendId) {
  const friends = { myId: localStorage.getItem("userid"), friendId: friendId };
  friendsStore.deleteFriends(friends);
  location.reload();
};
</script>

<style scoped>
/* @keyframes move {
  0% {
    background-color: #e0e0e0;
  }
  50% {
    background-color: #ffff33;
  }
  100% {
    background-color: #e0e0e0;
  }
} */

img {
  width: 4vw;
  height: 9vh;
}

.myinfo {
  flex: 1;
  height: 100%;
  border: 1px solid #e0e0e0;
  /* z-index: -1; */
  background-color: azure;
  /* animation: move 5s infinite; */
}

.friendDiv {
  transition: background-color 0.6s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
  border-radius: 15px;
  padding: 1v;
  width: 100%;
}

.friendDiv:hover {
  background-color: rgb(233, 187, 245);
  color: black;
  font-weight: bold;
}

.deleteDiary {
  color: black;
  transition: color 0.6s ease;
  cursor: pointer;
}

.deleteDiary:hover {
  color: red;
}

.watchDiary {
  color: black;
  transition: color 0.6s ease;
  cursor: pointer;
}

.watchDiary:hover {
  color: rgb(61, 248, 23);
}

#modal {
  display: none;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 200px;
  background-color: palegoldenrod;
  border: 3px solid #cc5252;
  box-shadow: 0 30px 90px rgba(0, 0, 0, 0.1); /* Increased box-shadow values by 3 times */
  z-index: 1000;
  font-size: 20px;
}

.eachRequest {
  border: 1px solid #e0e0e0;
  cursor: pointer;
}
.friend {
  height: 100%;
  box-sizing: border-box; /* Ensure the border width is included in the total height */
  /* z-index: -1; */
  background-color: #fefefe;
  color: #2c5f2d;
  /* animation: move 5s infinite; */
  border: solid 1px #333d79;
  border-radius: 80px;
  padding: 1vw;
  margin-top: 2.4vw;
  max-height: 68vh;
}

.makeCenter {
  display: flex;
  flex-direction: column;
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

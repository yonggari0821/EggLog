<template>
  <div class="friend">
    <div class="makeCenter">
      <p>친구리스트</p>
      <div class="space for friends">
        <div v-if="friendRequestList.length > 0">
          친구 요청이 {{ friendRequestList.length }} 개 존재합니다.
          <button @click="openModal">확인 하기</button>
        </div>

        <div @click="toMain()" style="cursor: pointer;">
          <img src="user.profilePicture" alt="본인 프로필 사진">
          <p>{{ user.id }}</p>
          <p>{{ user.nickname }}</p>
        </div>

        <hr>

        <div id="modal">
              <div v-for="request in friendRequestList" class="eachRequest" style="display: inline-flex; width: 25vw;">
                <p>{{ request.from }}</p>
                <button @click="beFriends(request)">수락</button>
                <button @click="denyFriends(request)">거부</button>
              </div>
              <button @click="closeModal()" class="close-button">닫기</button>
        </div>

        <br>
        
        <div v-if="friendsList.length > 3">친구 많으니깐 페이지</div>
        <div v-else-if="friendsList.length > 0">
          <div v-for="friendUser in friendsUsersList" :key="friendUser.id">
            <p>친구 사진 : {{ friendUser.profilePicture }}</p>
            <p>{{ friendUser.id }}</p> <br>
            <p>{{ friendUser.nickname }}</p>
            <img src="../../../assets/trash.png" alt="친구 삭제 버튼" class="settingButton" @click="requestDelete(friendUser.id)">
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
import { useUserStore } from "@/stores/userStore";
import { useFriendsStore } from "@/stores/friendsStore";
import { useRequestStore } from "@/stores/requestStore";

const friendsStore = useFriendsStore();
const userStore = useUserStore();
const requestStore = useRequestStore();

const user = computed(() => userStore.user);

const friendsList = computed(() => friendsStore.friendsList);
const friendsUsersList = computed ( () => userStore.friendsUsersList);
const friendRequestList = computed ( () => requestStore.friendRequestList);


// friendsList를 보내서
// 해당 friend들의 이름과 사진을 받아와야 함

// 친구 요청 보내기 위해서 친구 검색 화면으로 이동
const moveToFriendsRequest = function () {
  router.push({ name: "FriendsRequest" });
};

const toMain = function () {
  alert("본인 다이어리로 돌아가겠습니다!");
  router.push({name : "MainPage"});
}


// 새로고침시마다 아래 사항들 리뉴얼
onMounted(async() => {
  await friendsStore.getFriendsList(localStorage.getItem("userid")); // 친구 리스트
  const friendIds = friendsStore.friendsList;
  userStore.getFriendUsers(friendIds); // 친구들의 유저정보들
  console.log(friendsUsersList);
  await requestStore.getFriendRequestList(localStorage.getItem("userid")); // 친구 요청 리스트
  console.log(friendRequestList.value.length);
});

// 모달 열기
function openModal() { document.getElementById('modal').style.display = 'block'; }
// 모달 달기
function closeModal() { document.getElementById('modal').style.display = 'none'; }

// 수락 누르면
// 해당 요청 삭제 및 해당 친구와 친구되기

function beFriends(request) {
  friendsStore.addFriends(request);
  requestStore.deleteRequest(request);
  location.reload();
}

// 거부 누르면 요청만 삭제하고 친구는 안됨!
function denyFriends(request)
{
  requestStore.deleteRequest(request);
  location.reload();
}

// eachFriend 클릭 시 해당 친구의 다이어리로 이동하기

// 친구 삭제 버튼 클릭 시 친구 삭제하기 + MainPage로 이동하기
// request 필요 없음!
const requestDelete = function (friendId)
{
  const friends = {myId: localStorage.getItem("userid"), friendId: friendId};
  friendsStore.deleteFriends(friends);
  location.reload();
}
</script>



<style scoped>
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
.close-button {
    position: absolute;
    bottom: 10px;
    right: 10px;
    padding: 10px; /* Adjust padding based on your requirement */
    background-color: #007bff; /* Adjust background color based on your requirement */
    color: #fff; /* Adjust text color based on your requirement */
    border: none;
    cursor: pointer;
}

.eachRequest {
  border: 2px solid olivedrab;
  cursor: pointer;
}
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

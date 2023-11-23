<template>
  <div class="entireBackground">
    <div class="divMargin">
      <!-- 전체 -->

      <!-- 검색 창-->
      <div class="searchZone">
        <div style="width: 100%; display: inline-flex">
          <div class="test">
            <input
              type="text"
              v-model="searchInput"
              @keyup.enter="searchWithInput()"
              placeholder="친구 아이디를 검색하세요!"
              style="
                width: 100%;
                height: 100%;
                border-radius: 80px 0px 0px 80px;
                border: none;
                border-right: 0px;
                border-top: 0px;
                boder-left: 0px;
                boder-bottom: 0px;
                outline: none;
                background-color: #effbff;
              "
            />
          </div>
          <div class="test2">
            <img
              src="../../assets/돋보기배경제거.png"
              style="width: 100%"
              @click="searchWithInput()"
            />
          </div>
        </div>
      </div>

      <!-- 검색 결과 창-->
      <div class="searchResultZone">
        <div v-if="!searchedUser" class="foundNoUser">
          <p>검색된 유저가 없습니다! 다시 한 번 정확히 검색해주세요!</p>
        </div>

        <div v-else-if="searchedUser.nickname !== null" class="FoundAUser">
          <img src="../../assets/께란이배경삭제.png" alt="" style="flex: 3; width: 5px" />
          <!-- <img :src="searchedUser.profile_picture" /> -->
          <div style="flex: 1">사진 {{ profilePicture }}</div>

          <!-- 파란 색 둥근 배경 시작-->
          <div
            style="
              display: inline-flex;
              flex: 7;
              /* background-color: #9fdaef; */
              border-radius: 80px;
              padding: 3vw;
            "
          >
            <div style="flex: 6; background-color: #fffbf7; border-radius: 40px 0px 0px 40px">
              <!-- 정보들-->
              <div style="display: flex">
                <div
                  style="
                    flex: 2;

                    display: flex;
                    align-items: center;
                    justify-content: center;
                    margin: 10px;
                  "
                >
                  <p style="margin: 0; font-weight: bold">id</p>
                </div>
                <div style="display: flex; flex: 6; align-items: center; padding-top: 20px">
                  <p>
                    {{ searchedUser.id }}
                  </p>
                </div>
              </div>

              <div style="display: flex">
                <div
                  style="
                    flex: 2;

                    display: flex;
                    align-items: center;
                    justify-content: center;
                    margin: 5px;
                  "
                >
                  <p style="font-weight: bold">닉네임</p>
                </div>
                <div style="display: flex; flex: 6; align-items: center; margin: 20px">
                  <p>
                    {{ searchedUser.nickname }}
                  </p>
                </div>
              </div>

              <div style="display: flex" class="last">
                <div
                  style="
                    flex: 2;

                    display: flex;
                    align-items: center;
                    justify-content: center;
                    margin: 10px;
                  "
                >
                  <p style="font-weight: bold">상태 메시지</p>
                </div>
                <div style="display: flex; flex: 6; align-items: center; margin: 20px">
                  <p>
                    {{ searchedUser.statusMessage }}
                  </p>
                </div>
              </div>
            </div>
            <!-- 정보들 만 담음-->

            <div
           
              class="friendAdd"
            >
              <p @click="requestAdd(searchedUser.id)">친구 추가</p>
            </div>
          </div>
          <!-- 바로 위가 둥근 배경 끝-->
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
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@700&family=Noto+Sans+KR:wght@400;500;700&display=swap");

@keyframes move {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }

  /* 추가된 부분: 0-50%까지 배경색 변경 없음, 50-100%까지 색상 변경 */
  50% {
    background-color: #9fdaef;
  }
  100% {
    background-color: #f3f3f3;
  }
}

.friendAdd{
  flex: 2;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #ffe5d5;
                border-radius: 0px 40px 40px 0px;
                cursor: pointer;
                transition: background-color 0.3s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
}


.friendAdd:hover{
  background-color: #ec9e0d; /* 마우스를 올렸을 때의 배경색 */
}


.test {
  width: 85%;
  background-color: #effbff;
  padding: 0.4rem;
  border-radius: 80px 0px 0px 80px;
  animation: move 1s ease-in-out;
}

@keyframes move {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

.test2 {
  width: 15%;
  background-color: #9fdaef;
  border-radius: 0px 80px 80px 0px;
  cursor: pointer;
  animation: move 1s ease-in-out;
  transition: background-color 0.3s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
}

.test2:hover {
  background-color: #f3f3f3; /* 마우스를 올렸을 때의 배경색 */
}
.divMargin {
  padding-top: 3.5vw;
  margin-left: 15vw;
  margin-right: 15vw;
  height: 92vh;
}
.searchZone {
  /* background-color: bisque; */
  height: 5%;
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: center;
  padding: 20%;
  background-color: #c1e9f1;
}

.addButton {
  width: 3vw;
  height: 3vh;
  cursor: pointer;
}

.FoundAUser {
  display: inline-flex;
  background-color: beige;
  width: 100%;
  height: 20vw;
  border-radius: 80px;
  align-items: center;
  padding-right: 3vw;

  animation: move 1s ease-in-out;
}

input {
  height: 5vh;
  text-align: center;
  align-items: center;
}

.entireBackground {
  background-color: #c1e9f1;
  height: 100vh;
}
.searchResultZone {
  background-color: #c1e9f1;
  height: 60%;
}

p,
div {
  font-family: "Lato", sans-serif;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
}
</style>

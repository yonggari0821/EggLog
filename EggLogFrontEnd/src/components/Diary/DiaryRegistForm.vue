<template>
  <div class="divPadding">
    <div class="form-container">
      <h1 style="font-weight: bold">다이어리 등록</h1>
      <!-- 23 morning 날짜 추가-->
      <form @submit.prevent="submitForm">
        <div style="display: flex">
          <!-- 전체 담음-->
          <div style="flex: 1; margin-right: 1vw; margin-left: 1vw" id="one">
            <!-- 나눌 블록-->
            <p style="font-size: 1.2vw; font-weight: bold">날짜</p>
            <p style="font-size: 1vw">{{ route.params.diary_date }}</p>
            <div class="form-group">
              <p style="font-size: 1.2vw; font-weight: bold">제목</p>
              <input type="text" id="title" v-model="postDiary.title" required />
            </div>

            <div class="form-group">
              <p style="font-size: 1.2vw; font-weight: bold">내용</p>
              <textarea
                id="content"
                v-model="postDiary.content"
                required
                style="width: 100%; height: 30vh; background-color: transparent"
              ></textarea>
            </div>
          </div>
          <!--창헌이형파트-->
          <div style="flex: 1; margin-left: 1vw; margin-right: 1vw" id="two">
            <!--또 한블럭-->
            <!--여기서 부터 묶음-->
            <div class="form-group">
              <p style="font-size: 3vh; font-weight: bold">해시태그 선택</p>
              <div>
                <label for="upper">
                  <div style="display: flex">
                    <div style="font-size: 2vh">상체</div>
                    <div>
                      <input id="upper" type="checkbox" v-model="postDiary.hashtag" value="상체" />
                    </div>
                  </div>
                </label>
              </div>
              <div>
                <label for="lower">
                  <div style="display: flex">
                    <div style="font-size: 2vh">하체</div>
                    <div>
                      <input id="lower" type="checkbox" v-model="postDiary.hashtag" value="하체" />
                    </div>
                  </div>
                </label>
              </div>
              <div>
                <label for="abs">
                  <div style="display: flex">
                    <div style="font-size: 2vh">복근</div>
                    <div>
                      <input id="abs" type="checkbox" v-model="postDiary.hashtag" value="복근" />
                    </div>
                  </div>
                </label>
              </div>
              <div>
                <label for="body">
                  <div style="display: flex">
                    <div style="font-size: 2vh">전신</div>
                    <div>
                      <input id="body" type="checkbox" v-model="postDiary.hashtag" value="전신" />
                    </div>
                  </div>
                </label>
              </div>
            </div>

            <!--창헌이형파트-->

            <div>
              <div style="font-size: 3vh; font-weight: bold">지도</div>
              <!-- Include the Kakao Maps HTML code here -->
              <div id="clickLatlng">작성하는 현재 위치에요!</div>
              <div id="map" style="width: 30vw; height: 20vh"></div>
            </div>
          </div>
        </div>
      </form>
      <div style="display: flex; justify-content: right">
        <button
          type="button"
          class="btn btn-primary"
          style="width: 4vw; margin-right: 1vw"
          @click="registDiary"
        >
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useDiaryStore } from "@/stores/diaryStore";
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

const route = useRoute();
const router = useRouter();
const diaryStore = useDiaryStore();

const postDiary = ref({
  userId: null,
  title: null,
  content: null,
  diaryDate: null,
  // diaryPicture: null,
  hashtag: [],
  location: null,
});

let map = null;
const initMap = function () {
  let myCenter = new kakao.maps.LatLng(36.35512, 127.2983);
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      myCenter = new kakao.maps.LatLng(lat, lon);
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    });
  }
  const container = document.getElementById("map");
  const options = {
    center: myCenter,
    level: 5,
  }; // 지도 객체를 등록합니다.
  map = new kakao.maps.Map(container, options);
  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
};

function initKakaoMap() {
  function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : "0" + month;
    day = day >= 10 ? day : "0" + day;
    hour = hour >= 10 ? hour : "0" + hour;
    minute = minute >= 10 ? minute : "0" + minute;
    second = second >= 10 ? second : "0" + second;

    return date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
  }

  function successCallback({ coords, timestamp }) {
    latitude = coords.latitude;
    longitude = coords.longitude;
    alert(
      `위도: ${latitude},\n경도: ${longitude},\n\n현재 시간: ${dateFormat(new Date(timestamp))}`
    );
  }

  const errorCallback = (error) => {
    console.log(error);
  };

  const position = navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
  let latitude;
  let longitude;

  var container = document.getElementById("map");
  var options = {
    // center: new kakao.maps.LatLng(36.35512, 127.2983),
    level: 5,
  };
  // var map = new kakao.maps.Map(container, options);

  var markerImageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  // var markerImageSize = new kakao.maps.Size(22, 35);
  var markerImage = new kakao.maps.MarkerImage(markerImageSrc, markerImageSize);

  var marker = new kakao.maps.Marker({
    position: map.getCenter(),
    image: markerImage,
  });
  marker.setMap(map);

  kakao.maps.event.addListener(map, "click", function (mouseEvent) {
    var latlng = mouseEvent.latLng;
    marker.setPosition(latlng);
    var message = "클릭한 위치의 위도는 " + latlng.getLat() + " 이고, ";
    message += "경도는 " + latlng.getLng() + " 입니다";
    var resultDiv = document.getElementById("clickLatlng");
    resultDiv.innerHTML = message;
  });

  var iwContent = '<div style="padding:15px;">제목</div>';
  var iwContentOnlyDate = '<div style="padding:15px;">다이어리 날짜</div>';
  var iwPosition = map.getCenter();
  var iwRemoveable = true;

  var infowindow = new kakao.maps.InfoWindow({
    map: map,
    position: iwPosition,
    content: iwContent,
    removable: iwRemoveable,
  });

  var infowindowOnlyDate = new kakao.maps.InfoWindow({
    map: map,
    position: iwPosition,
    content: iwContentOnlyDate,
    removable: iwRemoveable,
  });

  infowindow.open(map, marker);

  kakao.maps.event.addListener(marker, "click", function () {
    infowindow.open(map, marker);
  });

  kakao.maps.event.addListener(marker, "mouseover", function () {
    infowindowOnlyDate.open(map, marker);
  });

  kakao.maps.event.addListener(marker, "mouseout", function () {
    infowindowOnlyDate.close();
  });

  var ps = new kakao.maps.services.Places();
  var markers = [];

  function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
  }

  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
      var bounds = new kakao.maps.LatLngBounds();
      removeMarker();

      for (var i = 0; i < data.length; i++) {
        displayMarker(data[i]);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
      }

      map.setBounds(bounds);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      alert("검색 결과가 존재하지 않습니다.");
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert("검색 결과 중 오류가 발생했습니다.");
      return;
    }
  }

  function displayMarker(place) {
    var marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(place.y, place.x),
    });

    kakao.maps.event.addListener(marker, "click", function () {
      infowindow.setContent(
        '<div style="padding:5px;font-size:12px;">' + place.place_name + "</div>"
      );
      infowindow.open(map, marker);
    });

    markers.push(marker);
  }

  function searchByKeyword() {
    const keyword = document.getElementById("keywordInput").value;
    console.log(keyword + "로 검색한 결과");
    ps.keywordSearch(keyword, placesSearchCB);
  }
}

function handleScriptError(event) {
  console.error("Error loading Kakao Maps script:", event);
}

const registDiary = function () {
  postDiary.value.userId = route.params.user_id;
  postDiary.value.diaryDate = route.params.diary_date;
  console.log(postDiary.value.userId);
  console.log(postDiary.value.diaryDate);
  diaryStore.registDiary(postDiary.value);
  alert("일기가 작성되었습니다");
  console.log("등록되었습니다.");
  router.push({
    name: "Diary",
    params: { diary: JSON.stringify(postDiary.value) },
  });
};
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script"); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=df1121badf1ccb1a5144d8b82705b21a&libraries=services,clusterer,drawing&autoload=false";
    script.addEventListener("load", () => {
      kakao.maps.load(initMap);
    }); //헤드태그에 추가
    document.head.appendChild(script);
  }
});
</script>

<style scoped>
.divPadding {
  padding-top: 3.3vw;
  background-image: url("../../assets/다이어리배경제거.png");
  background-size: 60% 80%;
  background-position: center;
  background-repeat: no-repeat;
}

.form-container {
  display: flex;
  flex-direction: column;
  max-width: 70vw;
  margin-left: 22vw;
  margin-right: 25vw;
  padding-bottom: 8.2vh;
  padding-top: 10vh;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}

.btn {
  border: solid 1px gray;
  width: 8vw;
  text-align: center;
  margin-right: 1vw;
  background-color: #dad8ff;
  border-radius: 0px 0px 10px 10px;
  cursor: pointer;
  transition: background-color 0.3s ease; /* 배경색이 변하는데 걸리는 시간과 전환 효과 지정 */
  box-shadow: 0 4px 4px -4px black;
}

.btn:hover {
  background-color: #ffe67c;
  color: #295f2e;
}
</style>

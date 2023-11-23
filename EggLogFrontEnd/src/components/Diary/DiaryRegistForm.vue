<template>
  <div class="divPadding">
    <div class="form-container">
      <h2>다이어리 등록</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="title">제목</label>
          <input type="text" id="title" v-model="postDiary.title" required />
        </div>

        <div class="form-group">
          <label for="content">내용</label>
          <input type="text" id="content" v-model="postDiary.content" required style="width: 100%;" />
        </div>

        <div>
          <!-- Include the Kakao Maps HTML code here -->
          <div id="map" style="width: 40vw; height: 40vh;"></div>
          <div id="clickLatlng">지도에서 위치를 클릭해서 오늘의 장소를 등록해보세요!</div>
          <input id="keywordInput" type="text" placeholder="검색 할 위치">
          <button @click="searchByKeyword">검색</button>
        </div>

        <button type="button" class="btn btn-primary" @click="registDiary">
          등록
        </button>
      </form>
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
  diaryPicture: null,
  location: null,
});

onMounted(() => {
  const script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = '//dapi.kakao.com/v2/maps/sdk.js?appkey=df1121badf1ccb1a5144d8b82705b21a&libraries=services,clusterer,drawing&autoload=false';

  script.addEventListener('load', initKakaoMap);
  script.addEventListener('error', handleScriptError);

  document.head.appendChild(script);
});

function initKakaoMap() {
  function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
  }

  function successCallback({ coords, timestamp }) {
    latitude = coords.latitude;
    longitude = coords.longitude;
    alert(`위도: ${latitude},\n경도: ${longitude},\n\n현재 시간: ${dateFormat(new Date(timestamp))}`);
  }

  const errorCallback = (error) => {
    console.log(error);
  }

  const position = navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
  let latitude;
  let longitude;

  var container = document.getElementById('map');
  var options = {
    center: new kakao.maps.LatLng(36.35512, 127.2983),
    level: 5
  };
  var map = new kakao.maps.Map(container, options);

  var markerImageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  var markerImageSize = new kakao.maps.Size(22, 35);
  var markerImage = new kakao.maps.MarkerImage(markerImageSrc, markerImageSize);

  var marker = new kakao.maps.Marker({
    position: map.getCenter(),
    image: markerImage
  });
  marker.setMap(map);

  kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
    var latlng = mouseEvent.latLng;
    marker.setPosition(latlng);
    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';
    var resultDiv = document.getElementById('clickLatlng');
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
    removable: iwRemoveable
  });

  var infowindowOnlyDate = new kakao.maps.InfoWindow({
    map: map,
    position: iwPosition,
    content: iwContentOnlyDate,
    removable: iwRemoveable,
  });

  infowindow.open(map, marker);

  kakao.maps.event.addListener(marker, 'click', function () {
    infowindow.open(map, marker);
  });

  kakao.maps.event.addListener(marker, 'mouseover', function () {
    infowindowOnlyDate.open(map, marker);
  });

  kakao.maps.event.addListener(marker, 'mouseout', function () {
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
      alert('검색 결과가 존재하지 않습니다.');
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert('검색 결과 중 오류가 발생했습니다.');
      return;
    }
  }

  function displayMarker(place) {
    var marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(place.y, place.x)
    });

    kakao.maps.event.addListener(marker, 'click', function () {
      infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
      infowindow.open(map, marker);
    });

    markers.push(marker);
  }

  function searchByKeyword() {
    const keyword = document.getElementById('keywordInput').value;
    console.log(keyword + '로 검색한 결과');
    ps.keywordSearch(keyword, placesSearchCB);
  }
}

function handleScriptError(event) {
  console.error('Error loading Kakao Maps script:', event);
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
</script>

<style scoped>
.divPadding {
  padding-top: 3.3vw;
}

.form-container {
  display: flex;
  flex-direction: column;
  max-width: 400px;
  margin: 0 auto;
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
  padding: 0.75rem 1rem;
  background-color: #aad1f1;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background-color: #fbff0c;
}
</style>

package com.egglog.service;

import java.util.List;

import com.egglog.dto.Friends;
import com.egglog.dto.Request;

public interface FriendsService {
	
	// Goal : 내 친구 목록 가져오기
	// Parameter : myId
	// Return : List<Friends>
	public List<String> selectAll(String myId);
	
	// Goal : 친구 추가
	// Parameter : 요청 인스턴스
	// Return : 'Dao에서의 int값 from DB'을 받아와서 // 0이면 친구 되기 실패 => (false) // 1이면 친구 됨! => (true)
	public boolean insert (Request request);
	
	// Goal : 친구 삭제
	// Parameter : 친구 인스턴스
	// Return : 'Dao에서의 int값 from DB'을 받아와서 // 0이면 친구 되기 실패 => (false) // 1이면 친구 됨! => (true)
	public boolean delete (Friends friends);
	
}

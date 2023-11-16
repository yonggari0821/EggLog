package com.egglog.dao;

import java.util.List;

import org.springframework.context.annotation.Bean;

import com.egglog.dto.Friends;
import com.egglog.dto.Request;
import com.egglog.dto.User;

public interface FriendsDao {
	// Goal : myId로 DB에서 친구들 아이디 가져오기
	// Parameter : myId
	// Return : 친구들 목록
	List<String> getFriends(String myId);
	
	// Goal : Mappers와 연계해서 친구 만들어주기
	// Parameter : 요청 인스턴스
	// Return : int 값 (1 이상 => 친구 됨 / 0 => 실패) 
	int addFriends(Request request);
	
	// Goal : Mappers와 연계해서 친구 삭제
	// Parameter : 친구 인스턴스
	// Return : int 값 (1 이상 => 친구 삭제 / 0 => 친구 유지)
	int removeFriends(Friends friends);
}

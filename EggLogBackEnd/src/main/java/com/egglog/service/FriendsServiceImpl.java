package com.egglog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egglog.dto.Friends;
import com.egglog.dto.Request;
import com.egglog.dao.FriendsDao;

@Service
public class FriendsServiceImpl implements FriendsService{
	
	@Autowired
	private FriendsDao friendsDao;
	
	// Goal : 내 친구 목록 가져오기
	// Parameter : myId
	// Return : List<Friends>
	@Override
	public List<String> selectAll(String myId) {
		return friendsDao.getFriends(myId);
	}
	
	
	// Goal : 친구 추가
	// Parameter : 요청 인스턴스
	// Return : 'Dao에서의 int값 from DB'을 받아와서 // 0이면 친구 되기 실패 => (false) // 1이면 친구 됨! => (true)
	@Override
	public boolean insert(Request request) {
		return friendsDao.addFriends(request) > 0;
	}
	
	// Goal : 친구 삭제
	// Parameter : 친구 인스턴스
	// Return : 'Dao에서의 int값 from DB'을 받아와서 // 0이면 친구 삭제 실패 => (false) // 1이면 친구 삭제 성공 => (true)
	@Override
	public boolean delete(Friends friends) {
		return friendsDao.removeFriends(friends) > 0;
	}
}

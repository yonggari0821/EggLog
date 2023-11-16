package com.egglog.service;

import org.springframework.stereotype.Service;

import com.egglog.dto.User;


public interface UserService {
	// 유저 정보 불러오기
	User getUser(String id);
	
	// 회원 가입
	int insert(User user);
	
	// 회원 탈퇴
	boolean deleteUser(String id);
	
	// 회원정보수정
	boolean modifyUser(User user);
}
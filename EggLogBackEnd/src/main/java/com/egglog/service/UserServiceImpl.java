package com.egglog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egglog.dao.UserDao;
import com.egglog.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	// 유저 정보 불러오기
	@Transactional
	@Override
	public User getUser(String id) {
		return userDao.getUser(id);
	}
	
	// 회원 가입
	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}
 
	// 회원 탈퇴
	@Transactional
	@Override
	public boolean deleteUser(String id) {
		return userDao.deleteUser(id) > 0;
	}
	
	// 회원정보수정
	@Transactional
	@Override
	public boolean modifyUser(User user) {
		return userDao.modifyUser(user) > 0;
	}
}
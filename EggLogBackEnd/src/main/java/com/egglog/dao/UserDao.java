package com.egglog.dao;

import java.util.List;

import com.egglog.dto.User;


public interface UserDao {
    
    // 유저 정보 불러오기
    public User getUser(String id);
    
    // 회원 가입
    public int insert(User user);
    
    // 회원 탈퇴
    public int deleteUser(String id);
    
    // 회원정보수정
    public int modifyUser(User user);

    // 이 id를 가진 User가 있는지 확인, 반환값 user
    public User searchById(String id);
    
    public List<User> searchFriendsUsers (List<String> friendIds);

	public String getRegDate(String id);
    
}
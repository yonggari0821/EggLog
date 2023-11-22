package com.egglog.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.egglog.dto.User;


public interface UserService {
    // 유저 정보 불러오기
    User getUser(String id);
    
    //
    public List<User> getUsersByIds(List<String> friendIds);
    
    // 회원 가입
    int insert(User user);
    
    // 회원 탈퇴
    boolean deleteUser(String id);
    
    // 회원정보수정
    boolean modifyUser(User user);

    // id로 User 찾기
    User searchById(String id);
    
    User login(String id , String pw);
}
package com.egglog.service;

import java.util.Collections;
import java.util.List;

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
    
    public List<User> getUsersByIds(List<String> friendIds) {
        return userDao.searchFriendsUsers(friendIds);
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

  @Override
  public User searchById(String id) {
    return userDao.searchById(id);
  }

@Override
public User login(String id, String pw) {
    // TODO Auto-generated method stub
    User check = userDao.getUser(id);
    if(check!=null && check.getPassword().equals(pw))
        return check;
    
    return null;
}
}
package com.egglog.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egglog.dto.User;
import com.egglog.service.UserService;
import com.egglog.util.JwtUtil;

import io.swagger.annotations.Api;


// 목적, 매개변수, 반환값
@RestController
@RequestMapping("/api")
@Api(tags="유저 컨트롤러")
public class UserRestController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    
  @Autowired
  private JwtUtil jwtUtil;
  
    @Autowired
    private UserService userService;
    
    
    // 목적: 회원정보확인
    // 매개변수: id
    // 반환값: user
    @GetMapping("/user/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        User user = userService.getUser(id);
        if (user != null)
            return new ResponseEntity<User>(user, HttpStatus.OK);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    // 목적: 친구 리스트에 해당하는 유저 정보 가져오기
    // 매개변수: 친구 아이디 리스트
    // 반환값: List<User>
    @GetMapping("/user/getFriendUsers")
    public ResponseEntity<List<User>> getFriendUsers(@RequestParam List<String> friendIds) {
        System.out.println(friendIds.toString());
        try {
            List<User> userList = userService.getUsersByIds(friendIds);
            System.out.println(userList.toString());
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // 추가 부분 11 23 2시반
    @GetMapping("/user/reg_date/{id}")
    public ResponseEntity<?> getRegDate(@PathVariable String id) {
        System.out.println("id :" + id);
        try {
            String regDate = userService.getRegDate(id);
            System.out.println(regDate);
            if(regDate != null)
                return new ResponseEntity<String> (regDate, HttpStatus.OK);
            return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    //
    
    
    
    
    // 목적: 회원가입
    // 매개변수: user
    // 반환값: user JSON 정보로 받기
    @PostMapping("/user")
    
    // throw 한 것 나중에 모아서 한꺼번에 try catch로 해결할 것
    public ResponseEntity<String> doRegist(@RequestBody User user) {
        try {
            // 비밀번호 토큰 생성
            String token = jwtUtil.createToken("password", user.getPassword());
            
            // 비밀번호를 database password 자리에 넣기
            user.setPassword(token);
            
            // DB에 user 정보 등록
            userService.insert(user);
            return new ResponseEntity<>("true", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // 목적: 회원 탈퇴
    // 매개변수: id
    // 반환값: boolean 성공, 실패
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        if (userService.deleteUser(id))
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }
    
    
    // 목적: 회원정보수정
    // 매개변수: user
    // 반환값: boolean
    // 프론트에서 토큰 관련 처리
    @PutMapping("/user")
    public ResponseEntity<String> update(@RequestBody User user) throws UnsupportedEncodingException {
        if (userService.modifyUser(user))
            return new ResponseEntity<String>("true", HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
       
   @PostMapping("/user/login")// 로그인
   public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println(user);
        String password = null;
        try {
            password = jwtUtil.createToken("password",user.getPassword());
            System.out.println(password);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        User check = userService.login(user.getId(),password); // 아이디 비밀번호 맞는지 홧인
        // 로그인 실패함!
        if (check == null) {
            //실패 상태 보내주고
            System.out.println("실패");
            return new ResponseEntity<Boolean>(false,HttpStatus.UNAUTHORIZED);
        } else {
            //loginUser에 넣어준다
//                session.setAttribute("loginUser", check.getName());
            // 이름을 받아오는게 맞을까.
            // 그냥 트루 펄스 받아온다음에 localstorage에 user정보를 저장하는게 맞다.
            System.out.println("체크" + check);
            System.out.println("성공");
            return new ResponseEntity<User>(check, HttpStatus.OK);
        }

    }
   
   // 목적: 회원정보 수정에서 현재 비밀번호 토큰화
   // 매개변수: password
   // 반환값: String token, null
   @PostMapping("/user/token")
   public ResponseEntity<?> token(@RequestParam String password) throws UnsupportedEncodingException {
       System.out.println("token start");
       if(password != null && password.length() > 0) {
           System.out.println("token success");
           String token = jwtUtil.createToken("password", password);
           return new ResponseEntity<String>(token, HttpStatus.OK);
       }
       System.out.println("token fail");
       return new ResponseEntity<Void>(HttpStatus.OK);
   }
            
   @GetMapping("/user/checkID")
   public ResponseEntity<Boolean> checkID(@RequestParam String id) {
       User isExist = userService.searchById(id);
       return new ResponseEntity<>(isExist != null, HttpStatus.OK);
   }

    // 목적: 로그아웃
    // 세션 종료를 통해 로그 아웃
    @GetMapping("/user/logout")
    public ResponseEntity<Void> doLogout(HttpServletRequest request) {
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
package com.egglog.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.egglog.dto.User;
import com.egglog.service.UserService;
import com.egglog.util.JwtUtil;

import springfox.documentation.annotations.ApiIgnore;


// 목적, 매개변수, 반환값
@RestController
@RequestMapping("/api")
public class UserRestController {

  @Autowired
  private JwtUtil jwtUtil;
  
	@Autowired
	private UserService userService;
	
	
	// 목적: 회원정보확인
	// 매개변수: id
	// 반환값: user
	@GetMapping("/user/{id}")
	public ResponseEntity<User> detail(@PathVariable String id) {
		User user = userService.getUser(id);
		if (user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	
	// 목적: 회원가입
	// 매개변수: user
	// 반환값: user JSON 정보로 받기
	@PostMapping("/user")
	
	// throw 한 것 나중에 모아서 한꺼번에 try catch로 해결할 것
	public ResponseEntity<Boolean> doRegist(@RequestBody User user) throws UnsupportedEncodingException {
	  
	    // 비밀번호 토큰 생성
	    String token = jwtUtil.createToken("password", user.getPassword());
	    
	    // 비밀번호를 database password자리에 넣기
	    user.setPassword(token);
	    
	    if(userService.searchById(user.getId()) != null)
	    	// BAD_REQUEST : 아이디가 이미 있을 경우
	    	return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	    
		// DB에 user 정보 등록
		userService.insert(user);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
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
	@PutMapping("/user")
	public ResponseEntity<Boolean> update(@RequestBody User user) {
		if (userService.modifyUser(user))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
	
	
	// 목적: 로그인
    // 매개변수: user
    // 반환값: boolean
	   @PostMapping("/login")
	   
	// throw 한 것 나중에 모아서 한꺼번에 try catch로 해결할 것
	    public ResponseEntity<?> doLogin(String id, String password, @ApiIgnore HttpSession session) throws UnsupportedEncodingException {
	     // password 토큰 생성
	       String pw = jwtUtil.createToken("password", password);
	        User loginUser = userService.searchById(id);
	        
	        // 해당 id를 가진 User의 password토큰과 입력한 password토큰 비교
	        if (loginUser != null && pw.equals(loginUser.getPassword())) {
	            session.setAttribute("loginUser", loginUser);  // 로그인
	            return new ResponseEntity<Boolean>(true,HttpStatus.ACCEPTED);
	        } else {
	          return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	        }
	    }

	    @GetMapping("/logout")
	    public ResponseEntity<Void> doLogout(@ApiIgnore HttpSession session) {
	        session.invalidate();
	        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	    }
}
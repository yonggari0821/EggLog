package com.egglog.controller;

import java.io.UnsupportedEncodingException;
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
	
	@GetMapping("/user/getFriendUsers")
	public ResponseEntity<List<User>> getFriendUsers(@RequestParam List<String> friendIds) {
	    try {
	        List<User> userList = userService.getUsersByIds(friendIds);
	        return new ResponseEntity<>(userList, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
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
	@PutMapping("/user")
	public ResponseEntity<Boolean> update(@RequestBody User user) throws UnsupportedEncodingException {
		String token = jwtUtil.createToken("password", user.getPassword());
		user.setPassword(token);
		
		if (userService.modifyUser(user))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
	

	// 목적: 로그인
    // 매개변수: id, password
    // 반환값: Map<String, Object>
//	   @PostMapping("/user/login")
//	// throw 한 것 나중에 모아서 한꺼번에 try catch로 해결할 것
//	    public ResponseEntity<Map<String, Object>> doLogin(User user) throws UnsupportedEncodingException {
//		   Map<String, Object> result = new HashMap<String, Object>();
//		   
//		   // password 토큰 생성
//		   String pw = jwtUtil.createToken("password", user.getPassword());
//		   User loginUser = userService.searchById(user.getId());
//		
//			HttpStatus status = null;
//			if(user.getId() != null && user.getPassword() != null && loginUser.getPassword().equals(pw)){
//				result.put("access-token", pw);
//				result.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				result.put("message", FAIL);
//				status = HttpStatus.NO_CONTENT;
//			}
//			return new ResponseEntity<Map<String,Object>>(result, status);
//	   }
	   
	   // 토큰화되어서 들어간다.
	   
	   
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
//	            session.setAttribute("loginUser", check.getName());
	        	// 이름을 받아오는게 맞을까.
	        	// 그냥 트루 펄스 받아온다음에 localstorage에 user정보를 저장하는게 맞다.
	        	System.out.println("체크" + check);
	        	System.out.println("성공");
	            return new ResponseEntity<User>(check, HttpStatus.OK);
	        }

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
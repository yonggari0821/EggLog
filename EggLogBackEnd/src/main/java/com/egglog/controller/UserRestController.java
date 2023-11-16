package com.egglog.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.egglog.dto.User;
import com.egglog.service.UserService;


// 목적, 매개변수, 반환값
@RestController
@RequestMapping("/api")
public class UserRestController {

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
	public ResponseEntity<Boolean> doRegist(@RequestBody User user) {

		// DB에 user 정보 등록
		if(userService.insert(user)>0)
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
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
}
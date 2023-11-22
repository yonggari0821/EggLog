package com.egglog.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.egglog.dto.Diary;
import com.egglog.dto.Friends;
import com.egglog.dto.Request;
import com.egglog.service.DiaryService;
import com.egglog.service.FriendsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "다이어리 컨트롤러")
public class DiaryRestController {
	@Autowired
	DiaryService diaryService;

	// Goal : 해당 유저의 다이어리 1개를 조회한다
	// Parameter : myId, diaryDate를 쿼리 스트링으로 받아와야 함
	// Return : 다이어리 인스턴스 1개
	// 창헌이형꺼에서 param으로 하기로함
	@GetMapping("/diary")
	@ApiOperation(value = "다이어리 1개를 조회한다.", response = Diary.class)
//    public ResponseEntity<?> getDiary(@RequestParam String userId, @RequestParam String diaryDate) {
	public ResponseEntity<?> getDiary(@RequestBody Diary diary) {
		try {
			Diary newDiary = diaryService.getDiary(diary.getUserId(), diary.getDiaryDate());
			if (diary.getContent() != null)
				return new ResponseEntity<Diary>(newDiary, HttpStatus.OK);
			else
				return new ResponseEntity<Diary>(diary, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// Goal : 해당 유저의 다이어리 리스트 가져오기
	// Parameter : userId
	// Return : 다이어리 리스트
	@GetMapping("/diary/{userId}/")
	public ResponseEntity<List<Diary>> getDiaryList(@PathVariable String userId) {
		List<Diary> list = diaryService.getDiaryList(userId);
		return new ResponseEntity<List<Diary>>(list, HttpStatus.OK);
	}

	// Goal : 해당 날짜 다이어리 정보 삭제
	// Parameter : userId, diaryDate
	// Return : 다이어리 정보 삭제 여부
	@DeleteMapping("/diary")
	public ResponseEntity<String> deleteDiary(String userId, String diaryDate) {
		if (diaryService.deleteDiary(userId, diaryDate))
			return new ResponseEntity<String>("true", HttpStatus.OK);
		return new ResponseEntity<String>("false", HttpStatus.OK);
	}

	// Goal : 다이어리 작성
	// Parameter : 다이어리 인스턴스
	// Return : 다이어리 작성 여부
	@PostMapping("/diary")
	@ApiOperation(value = "새로운 다이어리를 작성한다.", response = Integer.class)
	public ResponseEntity<?> writeDiary(@RequestBody Diary diary) {
		try {
			boolean result = diaryService.writeDiary(diary);
			if (result)
				return new ResponseEntity<String>("true", HttpStatus.OK);
			else
				return new ResponseEntity<String>("false", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	// Goal : 다이어리 수정
	// Parameter : 다이어리 인스턴스
	// Return : 다이어리 수정 여부
	@PutMapping("/diary")
	@ApiOperation(value = "썼던 다이어리를 수정한다.", response = Integer.class)
	public ResponseEntity<?> modifyDiary(@RequestBody Diary diary) {
		try {
			boolean result = diaryService.modifyDiary(diary);
			if (result)
				return new ResponseEntity<String>("true", HttpStatus.OK);
			else
				return new ResponseEntity<String>("false", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
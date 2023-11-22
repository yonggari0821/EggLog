package com.egglog.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egglog.dto.Diary;

public interface DiaryService {
	// 다이어리 1개 조회 // 아이디랑 날짜 필요!
	public Diary getDiary(String userId, String diaryDate);
    // 다이어리 리스트 조회
    List<Diary> getDiaryList(String userId);
    // 다이어리 삭제
    boolean deleteDiary(String userId, String diary_date);
	// 다이어리 1개 쓰기 // 다이어리 인스턴스 필요!
	public boolean writeDiary(Diary diary);
	// 다이어리 수정 // 다이어리 인스턴스 필요!
	public boolean modifyDiary(Diary diary);
}

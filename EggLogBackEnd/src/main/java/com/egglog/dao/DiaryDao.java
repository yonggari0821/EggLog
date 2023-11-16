package com.egglog.dao;


import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egglog.dto.Diary;
import com.egglog.dto.Friends;
import com.egglog.dto.Request;

public interface DiaryDao {
	// Goal : Mappers와 연계해서 다이어리 조회
	// Parameter : myId, diaryDate
	// Return : 다이어리 인스턴스
	Diary getDiary(@Param("userId")String userId, @Param("diaryDate")String diaryDate);
	

    // user의 모든 diary 정보 불러오기 (달력 형식으로 불러오기)
    public List<Diary> getDiaryList(String userId);
    
    // user의 해당 날짜 diary 삭제
    public int delete(@Param("userId")String userId, @Param("diaryDate")String diaryDate);
	
	// Goal : Mappers와 연계해서 다이어리 만들기
	// Parameter : 다이어리 인스턴스
	// Return : int 값 (1 이상 => 다이어리 만들어짐 / 0 => 다이어리 못 만듦) 
	int addDiary(Diary diary);
	
	// Goal : Mappers와 연계해서 다이어리 수정
	// Parameter : 다이어리 인스턴스
	// Return : int 값 (1 이상 => 다이어리 수정됨 / 0 => 다이어리 수정 안됨)
	int modifyDiary(Diary diary);
}

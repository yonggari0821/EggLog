package com.egglog.service;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egglog.dao.DiaryDao;
import com.egglog.dto.Diary;

@Service
public class DiaryServiceImpl implements DiaryService{
	
	@Autowired
	DiaryDao diaryDao;
	
	@Override
	public Diary getDiary(String userId, String diaryDate) {
		return diaryDao.getDiary(userId, diaryDate);
	}
	
    @Override
    public List<Diary> getDiaryList(String userId) {
        return diaryDao.getDiaryList(userId);
    }
    
    @Override
    public boolean deleteDiary(String userId, String diaryDate) {
        return diaryDao.delete(userId, diaryDate) > 0;
    }

	@Override
	public boolean writeDiary(Diary diary) {
		return diaryDao.addDiary(diary) > 0;
	}

	@Override
	public boolean modifyDiary(Diary diary) {
		return diaryDao.modifyDiary(diary) > 0;
	}
	
}

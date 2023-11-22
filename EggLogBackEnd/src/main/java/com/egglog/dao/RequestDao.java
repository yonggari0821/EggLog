package com.egglog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egglog.dto.Request;

public interface RequestDao {
	// 전체 요청 리스트 불러오기
	public List<Request> getRequestList();
	
	// 해당 사람에게 친구 요청한 요청들 리스트
	public List<Request> getRequest(String to);
	
	// 요청 보내기(등록)
	public int sendRequest(Request request);
	
	// 요청 삭제(요청자가 삭제하거나, 요청이 거절되었을 때)
	public int deleteRequest(Request request);
	
}

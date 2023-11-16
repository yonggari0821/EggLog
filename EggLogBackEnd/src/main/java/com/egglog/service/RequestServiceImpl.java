package com.egglog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egglog.dao.RequestDao;
import com.egglog.dto.Request;

@Service
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	private RequestDao requestDao;
	
	@Override
	public List<Request> getRequestList() {
		return requestDao.getRequestList();
	}

	@Override
	public List<Request> getRequest(String to) {
		return requestDao.getRequest(to);
	}

	@Transactional
	@Override
	public boolean sendRequest(Request request) {
		return requestDao.sendRequest(request) > 0;
	}

	@Transactional
	@Override
	public boolean deleteRequest(Request request) {
		return requestDao.deleteRequest(request) > 0;
	}
	
}

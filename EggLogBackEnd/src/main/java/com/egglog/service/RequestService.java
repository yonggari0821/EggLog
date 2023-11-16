package com.egglog.service;

import java.util.Date;
import java.util.List;

import com.egglog.dto.Request;

public interface RequestService {
	
	List<Request> getRequestList();
	
	List<Request> getRequest(String to);
	
	boolean sendRequest(Request request);
	
	boolean deleteRequest(Request request);
	
}

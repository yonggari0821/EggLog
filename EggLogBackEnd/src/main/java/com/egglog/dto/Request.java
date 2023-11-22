package com.egglog.dto;

public class Request {
	private String from;
	private String to;
	
	public Request() {
	}


	public Request(String from, String to) {
		this.from = from;
		this.to = to;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	@Override
	public String toString() {
		return "Request [from=" + from + ", to=" + to + "]";
	}
	

}

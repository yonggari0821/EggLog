package com.egglog.dto;

public class request {
	private int request_id;
	private String from;
	private String to;
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
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
	public request(int request_id, String from, String to) {
		super();
		this.request_id = request_id;
		this.from = from;
		this.to = to;
	}
}

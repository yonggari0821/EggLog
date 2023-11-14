package com.egglog.dto;

import java.time.LocalDate;

public class Diary {
	private String user_id;
	private LocalDate diary_date;
	private String title;
	private String content;
	private String diary_picture;
	private String location;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public LocalDate getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(LocalDate diary_date) {
		this.diary_date = diary_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDiary_picture() {
		return diary_picture;
	}
	public void setDiary_picture(String diary_picture) {
		this.diary_picture = diary_picture;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Diary(String user_id, LocalDate diary_date, String title, String content, String diary_picture,
			String location) {
		super();
		this.user_id = user_id;
		this.diary_date = diary_date;
		this.title = title;
		this.content = content;
		this.diary_picture = diary_picture;
		this.location = location;
	}
	
}

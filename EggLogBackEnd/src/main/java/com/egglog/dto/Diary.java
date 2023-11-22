package com.egglog.dto;

import java.sql.Date;

public class Diary {
	private String userId;
	private String diaryDate;
	private String title;
	private String content;
	private String diaryPicture;
	private String location;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDiaryDate() {
		return diaryDate;
	}
	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
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
	public String getDiaryPicture() {
		return diaryPicture;
	}
	public void setDiaryPicture(String diaryPicture) {
		this.diaryPicture = diaryPicture;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Diary() {}
	
	public Diary(String userId, String diaryDate, String title, String content, String diaryPicture,
			String location) {
		this.userId = userId;
		this.diaryDate = diaryDate;
		this.title = title;
		this.content = content;
		this.diaryPicture = diaryPicture;
		this.location = location;
	}
	@Override
	public String toString() {
		return "Diary [userId=" + userId + ", diaryDate=" + diaryDate + ", title=" + title + ", content=" + content
				+ ", diaryPicture=" + diaryPicture + ", location=" + location + "]";
	}
	
	
}

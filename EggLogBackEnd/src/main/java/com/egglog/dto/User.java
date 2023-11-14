package com.egglog.dto;

import java.time.LocalDate;

public class User {
	private String id; // 아이디 // PK
	private String password; // 비밀번호
	private String gender; // 성별
	private LocalDate birth;
	private String nickname; // 이름
	private String status_message;
	private String profile;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public User(String id, String password, String gender, LocalDate birth, String nickname, String status_message,
			String profile) {
		super();
		this.id = id;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
		this.nickname = nickname;
		this.status_message = status_message;
		this.profile = profile;
	}
}

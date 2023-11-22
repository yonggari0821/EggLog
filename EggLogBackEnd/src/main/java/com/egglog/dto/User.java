package com.egglog.dto;

import java.time.LocalDate;
import java.util.Date;

public class User {
	private String id; // 아이디 // PK
	private String password; // 비밀번호
	private String gender; // 성별
	private Date birth;	// 생년월일
	private Date regDate; // 등록일자 = 경험치와 관련
	private String nickname; // 이름
	private String statusMessage;	// 상태 메세지
	private String profilePicture;	// 프로필 사진
	
	
	public User() {}
	
	public User(String id, String password, String gender, Date birth, Date regDate, String nickname,
			String statusMessage, String profilePicture) {
		super();
		this.id = id;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
		this.regDate = regDate;
		this.nickname = nickname;
		this.statusMessage = statusMessage;
		this.profilePicture = profilePicture;
	}


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


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	public String getProfilePicture() {
		return profilePicture;
	}


	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", gender=" + gender + ", birth=" + birth + ", regDate="
				+ regDate + ", nickname=" + nickname + ", statusMessage=" + statusMessage + ", profilePicture="
				+ profilePicture + "]";
	}

}
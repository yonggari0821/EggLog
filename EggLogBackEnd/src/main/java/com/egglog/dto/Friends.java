package com.egglog.dto;

public class Friends {
	private String myId; // 복합키
	private String friendId; // 복합키
	public String getMyId() {
		return myId;
	}
	public void setMyId(String myId) {
		this.myId = myId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
	public Friends() {
	}
	
	public Friends(String myId, String friendId) {
		super();
		this.myId = myId;
		this.friendId = friendId;
	}

	
	@Override
	public String toString() {
		return "Friends [myId=" + myId + ", friendId=" + friendId + "]";
	}
	
}

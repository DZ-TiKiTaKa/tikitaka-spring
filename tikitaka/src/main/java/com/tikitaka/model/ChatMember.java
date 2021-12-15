package com.tikitaka.model;

public class ChatMember {
	
	private Long userNo;
	private Long chatNo;
	private String inTime;
	private String outTime;
	private String role;
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public Long getChatNo() {
		return chatNo;
	}
	public void setChatNo(Long chatNo) {
		this.chatNo = chatNo;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ChatMember [userNo=" + userNo + ", chatNo=" + chatNo + ", inTime=" + inTime + ", outTime=" + outTime
				+ ", role=" + role + "]";
	}
	
	
	
	

}

package com.tikitaka.model;

public class Calendar {
	private Long userNo;
	private String title;
	private String contents;
	private String startDate;
	private String endDate;
	private Long chatNo;
	
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	public Long getChatNo() {
		return chatNo;
	}
	public void setChatNo(Long chatNo) {
		this.chatNo = chatNo;
	}
	@Override
	public String toString() {
		return "Calendar [userNo=" + userNo + ", title=" + title + ", contents=" + contents + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", chatNo=" + chatNo + "]";
	}
	
	
}

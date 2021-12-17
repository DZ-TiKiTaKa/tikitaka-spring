package com.tikitaka.model;

import java.sql.Date;

public class Notice {
	private Long no;
	private Long userNo;
	private Long chatNo;
	private String title;
	private String contents;
	private int important;
	private Date regDate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
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
	public int getImportant() {
		return important;
	}
	public void setImportant(int important) {
		this.important = important;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Notice [no=" + no + ", userNo=" + userNo + ", chatNo=" + chatNo + ", title=" + title + ", contents="
				+ contents + ", important=" + important + ", regDate=" + regDate + "]";
	}
	
}

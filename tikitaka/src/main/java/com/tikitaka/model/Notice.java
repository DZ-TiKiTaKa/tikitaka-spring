package com.tikitaka.model;

import java.sql.Date;

public class Notice {
	private Long no;
	private Long user_no;
	private Long chat_no;
	private String title;
	private String contents;
	private int important;
	private Date reg_date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public Long getChat_no() {
		return chat_no;
	}
	public void setChat_no(Long chat_no) {
		this.chat_no = chat_no;
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
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "Notice [no=" + no + ", user_no=" + user_no + ", chat_no=" + chat_no + ", title=" + title + ", contents="
				+ contents + ", important=" + important + ", reg_date=" + reg_date + "]";
	}
	
	
}

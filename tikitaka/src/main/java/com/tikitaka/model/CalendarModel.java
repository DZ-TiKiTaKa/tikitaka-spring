package com.tikitaka.model;

import java.io.Serializable;

public class CalendarModel implements Serializable{

	private static final long serialVersionUID = 2082503192322391879L;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public CalendarModel() {
	}
	public CalendarModel(Long userNo, String title, String contents, String startDate, String endDate, Long chatNo) {
		super();
		this.userNo = userNo;
		this.title = title;
		this.contents = contents;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chatNo = chatNo;
	}
	
	CalendarModel(Builder builder){
		
	}
	
	public static class Builder{
		private Long userNo;
		private String title;
		private String contents;
		private String startDate;
		private String endDate;
		private Long chatNo;
		
		public Builder userNo(Long userNo) {
			this.userNo = userNo;
			return this;
		}
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		
		public Builder contents(String contents) {
			this.contents = contents;
			return this;
		}
		
		public Builder startDate(String startDate) {
			this.startDate = startDate;
			return this;
		}
		
		public Builder endDate(String endDate) {
			this.endDate = endDate;
			return this;
		}
		
		public Builder chatNo(Long chatNo) {
			this.chatNo = chatNo;
			return this;
		}
		
		public Builder(Long userNo, String title, String contents, String startDate, String endDate, Long chatNo) {
			this.userNo = userNo;
			this.title = title;
			this.contents = contents;
			this.startDate = startDate;
			this.endDate = endDate;
			this.chatNo = chatNo;
		}
		
	}
}

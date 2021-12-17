package com.tikitaka.model;

import java.io.Serializable;


public class Messagemodel implements Serializable{

	private static final long serialVersionUID = 2082503192322391880L;
	private String userNo;
	private String name;
	private String chatNo;
	private String contents;
	private String type;
	private String readCount;
	private String regTime;
	
	
	public String getUserNo() {
		return userNo;
	}


	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getChatNo() {
		return chatNo;
	}


	public void setChatNo(String chatNo) {
		this.chatNo = chatNo;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getReadCount() {
		return readCount;
	}


	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	public String getRegTime() {
		return regTime;
	}


	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}


	public Messagemodel() {}
	
	
	public Messagemodel(String userNo,String chatNo, String name, String contents, String type,String readCount,String regTime) {
		super();
		this.userNo = userNo;
		this.chatNo = chatNo;
		this.name = name;
		this.contents = contents;
		this.type = type;
		this.readCount = readCount;
		this.regTime = regTime;
	}
	
	
	Messagemodel(Builder builder){
		
	}
	
	public static class Builder{
		private String userNo;
		private String chatNo;
		private String name;
		private String contents;
		private String type;
		private String readCount;
		
		public Builder chatNo(String chatNo) {
			this.chatNo = chatNo;
			return this;
		}
		
		public Builder(String userNo,String chatNo, String name, String contents, String type,String readCount) {
			super();
			this.userNo = userNo;
			this.chatNo = chatNo;
			this.name = name;
			this.contents = contents;
			this.type = type;
			this.readCount = readCount;
			
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder contents(String contents) {
			this.contents = contents;
			return this;
		}
		
		public Builder type(String type) {
			this.type = type;
			return this;
		}
		
		public Messagemodel build() {
			return new Messagemodel(this);
		}
	}

}
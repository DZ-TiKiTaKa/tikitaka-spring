package com.tikitaka.model;

import java.io.Serializable;


public class MessageModel implements Serializable{

	private static final long serialVersionUID = 2082503192322391880L;
	private String chatNo;
	private String name;
	private String contents;
	public String getChatNo() {
		return chatNo;
	}
	public void setChatNo(String chatNo) {
		this.chatNo = chatNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "MessageModel [chatNo=" + chatNo + ", name=" + name + ", contents=" + contents + "]";
	}
	public MessageModel() {}
	
	
	public MessageModel(String chatNo, String name, String contents) {
		super();
		this.chatNo = chatNo;
		this.name = name;
		this.contents = contents;
	}
	
	
	MessageModel(Builder builder){
		
	}
	
	public static class Builder{
		private String chatNo;
		private String name;
		private String contents;
		
		public Builder chatNo(String chatNo) {
			this.chatNo = chatNo;
			return this;
		}
		
		public Builder(String chatNo, String name, String contents) {
			super();
			this.chatNo = chatNo;
			this.name = name;
			this.contents = contents;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder contents(String contents) {
			this.contents = contents;
			return this;
		}
		
		public MessageModel build() {
			return new MessageModel(this);
		}
	}

}

package com.tikitaka.model;

import java.io.Serializable;


public class MessageModel implements Serializable{

	private static final long serialVersionUID = 2082503192322391880L;
	private String chatNo;
	private String name;
	private String contents;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
		return "MessageModel [chatNo=" + chatNo + ", name=" + name + ", contents=" + contents + ", type=" + type + "]";
	}
	public MessageModel() {}
	
	
	public MessageModel(String chatNo, String name, String contents, String type) {
		super();
		this.chatNo = chatNo;
		this.name = name;
		this.contents = contents;
		this.type = type;
	}
	
	
	MessageModel(Builder builder){
		
	}
	
	public static class Builder{
		private String chatNo;
		private String name;
		private String contents;
		private String type;
		
		public Builder chatNo(String chatNo) {
			this.chatNo = chatNo;
			return this;
		}
		
		public Builder(String chatNo, String name, String contents, String type) {
			super();
			this.chatNo = chatNo;
			this.name = name;
			this.contents = contents;
			this.type = type;
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
		
		public MessageModel build() {
			return new MessageModel(this);
		}
	}

}

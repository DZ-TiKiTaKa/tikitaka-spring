package com.tikitaka.model;

import java.io.Serializable;


public class PubSubModel {

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
	
}

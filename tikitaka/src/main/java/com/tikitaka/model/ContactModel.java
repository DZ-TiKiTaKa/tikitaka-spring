package com.tikitaka.model;

import java.io.Serializable;

public class ContactModel implements Serializable{

	private static final long serialVersionUID = 2082503192322391879L;
	
	private Long userNo;
	private String chatNo;
	private String name;
	private String contents;
	private String type;

   
	public ContactModel(Long userNo, String chatNo, String name, String contents, String type) {
		super();
		this.userNo = userNo;
		this.chatNo = chatNo;
		this.name = name;
		this.contents = contents;
		this.type = type;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ContactModel [userNo=" + userNo + ", chatNo=" + chatNo + ", name=" + name + ", contents="
				+ contents + ", type=" + type + "]";
	}

	
}

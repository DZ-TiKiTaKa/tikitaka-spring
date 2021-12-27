package com.tikitaka.model;

import org.springframework.data.annotation.Id;

public class ChatMessage {

	private Long userNo;
	private Long chatNo;
	@Id
	private Long no;
	private String type;
	private String contents;
	private int readCount;
	private String regTime;
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
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	
	@Override
	public String toString() {
		return "ChatMessage [userNo=" + userNo + ", chatNo=" + chatNo + ", no=" + no + ", type=" + type + ", contents="
				+ contents + ", readCount=" + readCount + ", regTime=" + regTime + "]";
	}
	public ChatMessage(Long userNo,Long chatNo,String type,String contents,int readCount) {
		super();
		this.userNo = userNo;
		this.chatNo = chatNo;
		this.contents = contents;
		this.type = type;
		this.readCount = readCount;
	}
	
	public ChatMessage() {}
	
	
}
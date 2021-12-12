package com.tikitaka.model;

public class Manage {
	private Long userNo;
	private Long codeNo;
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public Long getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(Long codeNo) {
		this.codeNo = codeNo;
	}
	@Override
	public String toString() {
		return "Manage [userNo=" + userNo + ", codeNo=" + codeNo + "]";
	}
	
	
}

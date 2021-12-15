package com.tikitaka.dto;

public class Profile {
	private Long userNo;
	private Long codeNo;
	private String value;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Profile [userNo=" + userNo + ", codeNo=" + codeNo + ", value=" + value + "]";
	}
	
	
}

package com.tikitaka.model;

public class ChatMember {
	
	private Long userno;
	private Long chatno;
	private String in_time;
	private String out_time;
	private String role;
	public Long getUserno() {
		return userno;
	}
	public void setUserno(Long userno) {
		this.userno = userno;
	}
	public Long getChatno() {
		return chatno;
	}
	public void setChatno(Long chatno) {
		this.chatno = chatno;
	}
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getOut_time() {
		return out_time;
	}
	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ChatMember [userno=" + userno + ", chatno=" + chatno + ", in_time=" + in_time + ", out_time=" + out_time
				+ ", role=" + role + "]";
	}
	
	

}

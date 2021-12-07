package com.tikitaka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="user")
public class User {
	
	@Id
	private Long No;
	private String role;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String profile;
	private String loginTime;
	private String logoutTime;
	private boolean status;
	private Long careNo;
	private String proName;

	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public String getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}


	public String getLogoutTime() {
		return logoutTime;
	}


	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public Long getCareNo() {
		return careNo;
	}


	public void setCareNo(Long careNo) {
		this.careNo = careNo;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	public User() {
		
	};


	public Long getNo() {
		return No;
	}


	public void setNo(Long no) {
		No = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "User [No=" + No + ", role=" + role + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", profile=" + profile + ", loginTime=" + loginTime + ", logoutTime="
				+ logoutTime + ", status=" + status + ", careNo=" + careNo + ", proName=" + proName + "]";
	};
	

	
	
	
	
}

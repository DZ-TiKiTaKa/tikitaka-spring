package com.tikitaka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="user")
public class User {
	
	@Id
	private Long No;
	private String name;
	private String password;
	private String email;
	private String phone;

	
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
		return "User [No=" + No + ", name=" + name + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ "]";
	};
	
//	public User(Long id, String name, int age, String message) {
//		this.id = id;
//		this.name = name;
//		this.age = age;
//		this.message = message;
//	}
	
	
	
	
}

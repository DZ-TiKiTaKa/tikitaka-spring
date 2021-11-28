package com.tikitaka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="user")
public class User {
	
	@Id
	private Long id;
	private String name;
	private int age;
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User(Long id, String name, int age, String message) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.message = message;
	}
	public User() {};
	
}

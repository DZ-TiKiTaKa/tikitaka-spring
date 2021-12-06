package com.tikitaka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.model.User;

@RestController
public class UserController {
	
	@GetMapping("/api/hello")
	public String joinUser(){
		return "hello ysw";
	}
	
	@RequestMapping("/joinsuccess")
	public void joinsuccess(@RequestBody User user) {
		System.out.println(user.toString());
	}
}

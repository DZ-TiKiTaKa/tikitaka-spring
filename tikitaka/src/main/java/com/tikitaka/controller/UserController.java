package com.tikitaka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.model.User;
import com.tikitaka.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/login")
	public void userlogin(@RequestBody User user) {
		System.out.println(user.toString());
		System.out.println("userlogin 메서드 실행");
		
		User userr = userService.getUser(user.getEmail(), user.getPassword());
		
		
		System.out.println("name: " + userr.getName());
		
		
	}
}

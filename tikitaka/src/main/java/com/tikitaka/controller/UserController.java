package com.tikitaka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.dto.JsonResult;
import com.tikitaka.model.User;
import com.tikitaka.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public JsonResult userlogin(@RequestBody User user) {
		//System.out.println(user.toString());
		System.out.println("userlogin 메서드 실행");
		
		User uservo = userService.getUser(user.getEmail(), user.getPassword());
		System.out.println(uservo);
		if(uservo == null) {
			return JsonResult.fail("loginfail");
		}
		return JsonResult.success(uservo);
	}


	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		System.out.println("join controller called...");
		System.out.println("user:" + user);
		
		userService.joinUser(user);
		
		return "success";
	}

}
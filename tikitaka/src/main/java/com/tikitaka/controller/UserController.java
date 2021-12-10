package com.tikitaka.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tikitaka.dto.JsonResult;
import com.tikitaka.model.User;
import com.tikitaka.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public JsonResult userlogin(@RequestBody User user) {
		System.out.println(user.toString());
		System.out.println("userlogin 메서드 실행");
		
		User uservo = userService.getUser(user.getEmail(), user.getPassword());
		if(uservo == null) {

			return JsonResult.fail("loginfail");
		}
		
		return JsonResult.success(uservo != null);
		
	}

	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		
		userService.joinUser(user);
		
		return "success";
	}

	@PostMapping("/updateProfile")
	public void updateProfile(@RequestBody HashMap<String, Object> result) {
		System.out.println(result);
	}
	
	@PostMapping("/updateImage")
	public String updateImage(@RequestParam(value="file", required=false) MultipartFile image) throws Exception {
		String url = userService.restore(image);
		return url;
	}
}
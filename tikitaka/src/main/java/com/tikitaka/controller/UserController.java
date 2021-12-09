package com.tikitaka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
		
		userService.joinUser(user);
		
		return "success";
	}
	
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(@AuthUser UserVo authUser, Model model) {
//		UserVo userVo = userService.getUser(authUser.getNo());
//		model.addAttribute("userVo", userVo);
//		
//		return "user/update";
//	}	
	
	@RequestMapping
	public String logStatus(@RequestBody User user, Model model) {
		
		User userVO = userService.getLogStatus(user.getNo());
		model.addAttribute("userVO", userVO);
		
		return "success_logStatus";
		
	}

}
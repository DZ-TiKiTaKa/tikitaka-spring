package com.tikitaka.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public JsonResult userlogin(@RequestBody User user,HttpSession session) {
		System.out.println(user.toString());
		System.out.println("userlogin 메서드 실행");
		
		User uservo = userService.getUser(user.getEmail(), user.getPassword());
		if(uservo == null) {
			return JsonResult.fail("loginfail");
		}
		
		return JsonResult.success(uservo != null);
		
		//react에서 아이디 비번 받아와서 db의 값과 동일하면
		//DB의 status를 로그인으로 바꿔주고 react 화면에서는 메인으로
		//이동 하고 프로필의 네임변경 실패시 팝업창 띄우기
		// 채팅 view 제작하기
		//프로필 창에서 vaule값 dB에서 받아오기
		//창현이 형이 말해준 password 암호화 설정해야함
	}

	@GetMapping("/logout")
	public JsonResult userlogout(HttpSession session) {
		
		User user1 = new User();
		user1.setNo(2L);
		
		/*
		if(session.getAttribute("authUser") == null) {
			return JsonResult.fail("fail");
		}
		*/
		
		
		//로그인 
		int status = 1;
		System.out.println("업데이트 가보자곡");
		if(userService.UpdateUserState(user1.getNo(),status)) {
			
			System.out.println("업데이트 가보자곡");
			//session.removeAttribute("authUser");
			//session.invalidate();
		}
		
		return JsonResult.success(null);
	}
	
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		System.out.println("join controller called...");
		System.out.println("user:" + user);
		
		userService.joinUser(user);
		
		return "success";
	}

}
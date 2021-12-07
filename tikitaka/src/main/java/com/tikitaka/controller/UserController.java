package com.tikitaka.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
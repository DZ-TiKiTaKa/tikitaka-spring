package com.tikitaka.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		//System.out.println(user.toString());
		System.out.println("userlogin 메서드 실행");
		User uservo = userService.getUser(user.getEmail(), user.getPassword());
		
		System.out.println(uservo);
		
		if(uservo == null) {
			return JsonResult.fail("loginfail");
		}else {
			//status 상태 값 변경 및 now()를 통한 타임기록
			if(uservo.getStatus() == 0) {
				userService.UpdateUserState(uservo.getNo(), uservo.getStatus());
			}		
			return JsonResult.userSuccess(uservo);
		}
		
	}
	
	
	@RequestMapping("/logout")
	public JsonResult userlogout(@RequestBody HashMap<String, String> map) {
		System.out.println("C: userlogout ");
		int status = 1;
		
		System.out.println(	map.get("token"));
		
		String no = map.get("token");
		
		userService.UpdateUserState(no, status);
		
		return JsonResult.success("ok");
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

	// 친구 목록 (no, role, name, status, profile 가져오기)	
	@RequestMapping("/user")
	public JsonResult logStatus(@RequestBody HashMap<String, String> data) {

		String no = data.get("userNo");
		List<User> list = userService.getLogStatus(no);

		return JsonResult.success(list);

	}

	@PostMapping("/updateProfile")
	public void updateProfile(@RequestBody HashMap<String, Object> result) {
		
		userService.updateProfile(result);
		System.out.println(result);
	}
	
	@PostMapping("/updateImage/{token}")
	public String updateImage(@RequestParam(value="file", required=false) MultipartFile image,
								@PathVariable("token") Long no) throws Exception {
		System.out.println(no);
		String url = userService.restore(image, no);
		return url;
	}
	
	@GetMapping("/getImage/{no}")
	public String getImage(@PathVariable("no") Long no) {
		String result = userService.getIamge(no);
		return result;
	}
	
	@GetMapping("/getInfo/{no}")
	public User getInfo(@PathVariable("no") Long no){
		User info = new User();
		info = userService.getInfo(no);
		return info;
	}
	
	
}
package com.tikitaka.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		Long no = Long.parseLong((String)map.get("token"));
		
		userService.UpdateUserState(no, status);
		
		return JsonResult.success("ok");
	}
	


	@PostMapping("/join")
	public void join(@RequestBody User user) {
		String[] userNo = user.getUserNo().split("-"); // 사용자가 입력한 코드
		boolean emailCheck = userService.findByEmail(user.getEmail());
		System.out.println("email 중복체크" + emailCheck);
		if(emailCheck == false) {
			return;
		}
		
		if(userNo.length == 4) {
			user.setRole("CP");
		} else {
			user.setRole("CS");
		}
		
		userService.joinUser(user);
		userService.setCode(userNo);
		
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
	public JsonResult logStatus(@RequestBody String authNo) {
		//System.out.println("authno확인"+authNo);
		//authno을 받아서 친구 관계일때 목록에 뜨게 해야함(아직 미구현)
		List<User> list = userService.getFriendlistbyNo(authNo);

		return JsonResult.success(list);

	}

	@PostMapping("/updateProfile")
	public User updateProfile(@RequestBody HashMap<String, Object> result) {
		
		Long userNo = Long.parseLong(result.get("no").toString()) ;
		userService.updateProfile(result);
		User user = userService.findUser(userNo);
		System.out.println("바뀐 유저: " + user);
		return user;
	}
	
	@PostMapping("/updateImage/{token}")
	public String updateImage(@RequestParam(value="file", required=false) MultipartFile image,
								@PathVariable("token") Long no) throws Exception {
		if(no == null || image == null) {
			return null;
		}
		String url = userService.restore(image, no);
		return url;
	}
	
	@GetMapping("/getImage/{no}")
	public String getImage(@PathVariable("no") Long no) {
		String result = userService.getIamge(no);
		return result;
	}
	
	@GetMapping("/getInfo/{no}")
	public List<String> getInfo(@PathVariable("no") Long no){
		List<String> list = new ArrayList<String>();
		list = userService.getInfo(no);
		System.out.println(list);
		return list;
	}
	
	
    @PostMapping("/searchinfo/{userNo}")
    public List<User> searchInfo(@PathVariable Long userNo){
    	System.out.println("SEARCH INFO");
    	List<User> list = userService.searchInfoByNo(userNo);
    	System.out.println(list);
    	return list;
   }
    
    @PostMapping("/searchlotinfo/{userNo}")
   public List<User> searchlotinfo(@PathVariable Long userNo){
    	System.out.println("SEARCH LOT INFO");
    	List<User> list = userService.searchlotinfo(userNo);
    	
    	System.out.println(list);
    	return list;
    }
    
	
	
}
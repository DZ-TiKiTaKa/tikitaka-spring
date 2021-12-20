package com.tikitaka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.dto.JsonResult;
import com.tikitaka.model.Chat;
import com.tikitaka.model.Notice;
import com.tikitaka.service.NoticeService;

@RestController
public class NoticeController {

	@Autowired
	private NoticeService alertService;

	@RequestMapping("/main")
	public JsonResult getNoticeInfo(@RequestBody HashMap<String, String> data) {
		//notice 정보 가져오기 메소드
		System.out.println("C: getnoticeInfo");
		
		String no = data.get("token");
		List<Notice> list = alertService.getNotice(no);
		System.out.println(list);
		return JsonResult.success(list);
	}

	@RequestMapping("/getData")
	public JsonResult getData(@RequestBody HashMap<String, String> map) {

		System.out.println("C : getData");

		Long no = Long.parseLong((String) map.get("token"));
		
		//공지 알림 가져오기 메소드
		List<Notice> nlist = alertService.getAlert(no);
		System.out.println("getData : " + nlist);
		
		//새로은 채팅 알림 가져오기 메소드
		List<Chat> clist = alertService.getNewchat(no);
		System.out.println(clist);
		Map<String, Object> alist = new HashMap<String, Object>();
		
		alist.put("Nlist", nlist);
		alist.put("Clist", clist);
		
		return JsonResult.success(alist);
	}
	
	// chatNo에 해당하는 채팅방의 공지 작성
	@PostMapping("/talk/topic/noticeWrite")
    public String noticeWrite(@RequestBody HashMap<String, Object> map) {	
    	
    	System.out.println("noticeWrite 값 들어옴" + map);
    	
    	Long userNo = Long.parseLong(String.valueOf(map.get("userNo")));
    	Long chatNo = Long.parseLong(String.valueOf(map.get("chatNo")));
    	String title = map.get("title").toString();
    	int important = Integer.parseInt(String.valueOf(map.get("important")));

    	String contents = map.get("contents").toString();
    	
    	Notice notice = new Notice();
    	
    	notice.setUserNo(userNo);
    	notice.setChatNo(chatNo);
    	notice.setTitle(title);
    	notice.setContents(contents);
    	notice.setImportant(important);
    	
    	alertService.noticeWrite(notice);
    	
    	return "success";
    }

}

package com.tikitaka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.dto.JsonResult;
import com.tikitaka.model.Chat;
import com.tikitaka.model.Notice;
import com.tikitaka.model.User;
import com.tikitaka.service.NoticeService;

@RestController
public class NoticeController {

	@Autowired
	private NoticeService alertService;

	@RequestMapping("/main")
	public JsonResult getNoticeInfo(@RequestBody HashMap<String, String> data) {

		System.out.println("notice 가져오기 메소드");
		System.out.println(data.get("token"));
		String no = data.get("token");

		System.out.println(no);

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

}

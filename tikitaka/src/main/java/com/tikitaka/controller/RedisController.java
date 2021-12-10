package com.tikitaka.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.model.ChatMessage;
import com.tikitaka.service.ChatService;



@RestController
public class RedisController {
	
	@Autowired
	private ChatService chatService;
	
	@PostMapping("/send")
	public void send(@RequestBody HashMap<String, Object> result) {
		//System.out.println("result:" + result);
		
		ChatMessage data = new ChatMessage();
		data.setUserNo(Long.parseLong((String) result.get("userNo")));
		data.setChatNo(Long.parseLong((String) result.get("chatNo")));
		data.setType((String)result.get("type"));
		data.setContents((String)result.get("contents"));
		data.setReadCount((Integer)result.get("readCount"));
		
		chatService.insertMessage(data);
	
	}
	
	
}

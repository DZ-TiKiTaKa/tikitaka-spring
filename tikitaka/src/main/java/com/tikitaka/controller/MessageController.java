package com.tikitaka.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public void greeting() throws Exception {
		System.out.println("d연결....");
		
	}
}
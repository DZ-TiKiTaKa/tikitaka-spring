package com.tikitaka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.model.MessageModel;

@RestController
public class MessageController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	// @MessageMapping: 클라이언트에서 보내는 메시지를 매핑
	@MessageMapping("/send")
	public void SendToMessage(MessageModel msg) {
		// convertAndSend(): 첫번째 param에 해당하는 토픽을 구독중인 클라이언트에게 메시지를 전송
		simpMessagingTemplate.convertAndSend("/topic/" + msg.getChatNo(), msg);
	}
}

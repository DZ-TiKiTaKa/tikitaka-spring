package com.tikitaka.service;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private SimpMessageSendingOperations meOperations;
	
	public void getMsg() {
		meOperations.convertAndSend("/sub");
	}
	
	
}

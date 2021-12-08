package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.ChatMessage;
import com.tikitaka.repository.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;

	public void insertMessage(ChatMessage data) {
		chatRepository.insert(data);
	}
	
	
}

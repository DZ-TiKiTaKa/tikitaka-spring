package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tikitaka.model.ChatMessage;
import com.tikitaka.model.Messagemodel;
import com.tikitaka.model.Messagemodel;
import com.tikitaka.repository.ChatRepository;
import com.tikitaka.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
	
//	@Autowired
//	private RedisTemplate<String,String> redisTemplate;

	@Autowired
	private ChatMessageRepository chatmessageRepository;

	public boolean insertMessage(ChatMessage chatMessage) {
		return chatmessageRepository.insert(chatMessage);
	}

	
	
	
}

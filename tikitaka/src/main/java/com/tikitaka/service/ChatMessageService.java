package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tikitaka.model.ChatMessage;
import com.tikitaka.repository.ChatRepository;
import com.tikitaka.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Autowired
	private ChatMessageRepository chatmessageRepository;

	public void insertMessage(ChatMessage data) {
		
//		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//		String key = chat.getNo().toString();
//		// when
//		valueOperations.set(key, chat.getContents());
//		
//		// then
//		String value = valueOperations.get(key);
//		Boolean expire = redisTemplate.expire(key, 5, TimeUnit.SECONDS);
		
		//chatmessageRepository.insert(data);
	}
	
	
}

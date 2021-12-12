package com.tikitaka.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.tikitaka.model.Chat;

@Component
public class __redisTempalate {

//	@Autowired
//	private RedisTemplate<String,String> redisTemplate;
//	
//	@Bean
//	public void redisString() {
//
//		Chat chat = new Chat();
//		chat.setContents("yswcheck22");
//		chat.setNo(3114L);
//		// 데이터 주기
//		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//		String key = chat.getNo().toString();
//		// when
//		valueOperations.set(key, chat.getContents());
//		
//		// then
//		String value = valueOperations.get(key);
//		//Boolean expire = redisTemplate.expire(key, 5, TimeUnit.SECONDS);
//		
//	}
	
}

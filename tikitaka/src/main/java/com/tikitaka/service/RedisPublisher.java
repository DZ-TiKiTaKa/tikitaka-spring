package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Messagemodel;

@Service
public class RedisPublisher {

	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public void publish(ChannelTopic topic, Messagemodel message) {
		System.out.println("Redis Pub 호출");
		System.out.println(topic.getTopic());
		
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}
}

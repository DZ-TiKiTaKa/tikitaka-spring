package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.tikitaka.model.CalendarModel;
import com.tikitaka.model.ContactModel;
import com.tikitaka.model.Messagemodel;

@Service
public class RedisPublisher {

	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public void publish(ChannelTopic topic, Messagemodel message) {
		System.out.println("CalPub 호출");
		System.out.println(topic.getTopic());
		
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}
	
	public void publishCal(ChannelTopic topic, CalendarModel calModel) {
		System.out.println("publishCal called... !!!!!!!!!");
		redisTemplate.convertAndSend(topic.getTopic(), calModel);
	}
	
	// 연락처 보내기
	public void publishCon(ChannelTopic topic, ContactModel conModel) {
		System.out.println("publishCon 온다 !");
		
		// redis pub 호출 - publish 보내줌 (topic에 model(채팅에서는 message)을 담아서)
		redisTemplate.convertAndSend(topic.getTopic(), conModel);
	}
}

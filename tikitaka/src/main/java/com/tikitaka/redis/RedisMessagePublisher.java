package com.tikitaka.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import com.tikitaka.model.MessageModel;


public class RedisMessagePublisher implements MessagePublisher{
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic; 

	//기본 생성자
    public RedisMessagePublisher() { }
 
    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
      this.redisTemplate = redisTemplate;
      this.topic = topic;
    }

	public void publish(MessageModel model) {
		redisTemplate.convertAndSend(topic.getTopic(), model.getContents());
		//convertAndSend()는 목적지채널에 메시지를 전달하는 역할
		
	}

	@Override
	public void publish(String message) {
		// TODO Auto-generated method stub
		
	}
}
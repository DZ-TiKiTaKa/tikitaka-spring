package com.tikitaka.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.tikitaka.config.RedisConfig;
import com.tikitaka.model.MessageModel;
import com.tikitaka.redis.RedisMessagePublisher;

@Service
public class SpringRedisService {
	public void pub(ChannelTopic topic, MessageModel model) {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
		
		try {
			RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
			//ChannelTopic topic = (ChannelTopic)ctx.getBean("topic");
			//RedisMessagePublisher redisMessagePublisher= (RedisMessagePublisher)ctx.getBean("redisPublisher", redisTemplate, topic);
			

			RedisMessagePublisher redisMessagePublisher= new RedisMessagePublisher(redisTemplate, topic);
								
	        redisMessagePublisher.publish(model);//메시지를 게시함,목적지 채널에 메시지전달(지금은 채널임의로 messageQueue로 설정)
	        
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
	}
}

package com.tikitaka;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.tikitaka.model.Chat;
import com.tikitaka.redis.redisTempalate;
import com.tikitaka.service.ChatService;

@Configuration
@EnableRedisRepositories
@ComponentScan("com.tikitaka")
public class RedisConfiguration {
	
	
	@Autowired
	private ChatService chatService;
	
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host,port);
	}
	
	@Bean //이 메소드는 주석해도 안해도 redis 들어오는게 같은데 무슨 역할인지
	public RedisTemplate<  ?, ?> redisTemplate(){
		RedisTemplate<?, ?> redisTemplatePub = new RedisTemplate<>();
		redisTemplatePub.setConnectionFactory(redisConnectionFactory());
		redisTemplatePub.setKeySerializer(new StringRedisSerializer());
        redisTemplatePub.setValueSerializer(new StringRedisSerializer());
		return redisTemplatePub;
	}
	
	@Bean //
	public void insert() {
		Chat chat = new Chat();
		chat.setContents("hoohohohohoh");
		chatService.insert(chat);
		System.out.println("인설트 완료");
		
	}
	
}

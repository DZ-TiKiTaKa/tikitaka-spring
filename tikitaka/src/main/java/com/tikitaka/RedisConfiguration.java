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
import com.tikitaka.service.ChatService;

@Configuration
@EnableRedisRepositories
@ComponentScan("com.tikitaka.service")
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
	
	@Bean
	public RedisTemplate<?, ?> redisTemplate(){
		RedisTemplate<?, ?> redisTemplatePub = new RedisTemplate<>();
		redisTemplatePub.setConnectionFactory(redisConnectionFactory());
		redisTemplatePub.setKeySerializer(new StringRedisSerializer());
        redisTemplatePub.setValueSerializer(new StringRedisSerializer());
		return redisTemplatePub;
	}
	
	@Bean
	public void insert() {
		Chat chat = new Chat();
		chat.setContents("yayayayaya");
		chatService.insert(chat);
		
	}
	
	
	
}

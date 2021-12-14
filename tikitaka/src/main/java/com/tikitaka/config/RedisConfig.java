package com.tikitaka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.tikitaka.redis.MessagePublisher;
import com.tikitaka.redis.RedisMessagePublisher;
import com.tikitaka.redis.RedisMessageSubscriber;

@Configuration
@PropertySource("application.properties")
@EnableRedisRepositories //RedisRepository 를 사용한다고 지정
public class RedisConfig {
	@Value("${spring.redis.port}")
    private int port;
		
    @Value("${spring.redis.host}")
    private String host;

    //Redis server와 연결
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
    	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    	redisTemplate.setConnectionFactory(redisConnectionFactory());
    	redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
    	return redisTemplate;
    }
    
    //긴 문자열을 다룰때 사용
    @Bean
    public StringRedisTemplate strRedisTemplate() {
    	StringRedisTemplate redisTemplate = new StringRedisTemplate();
    	redisTemplate.setConnectionFactory(redisConnectionFactory());
    	return redisTemplate;
    }
    
    //메시지 큐를 위한 Bean 등록
    //subscriber역할을 수행
    @Bean
    MessageListenerAdapter messageListener() { 
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }
    
    //채널의 메시지를 받는데 사용되는 컨테이너
    //컨테이너는 Redis채널로부터 메시지를 받는데 사용, 구독자들에게 메시지를 dispatch함
    //// redis를 경청하고 있다가 메시지 발행(publish)이 오면 Listener가 처리한다.  
    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer(); 
        container.setConnectionFactory(redisConnectionFactory()); 
        //container.addMessageListener(messageListener(), topic());  //-> 이 소스는 Controller에서 실행(topic값을 동적으로 받기위해)
        return container; 
    }
    
    //메시지를 게시하는 bean (Publish)
    @Bean
    MessagePublisher redisPublisher() { 
        return new RedisMessagePublisher(redisTemplate(), topic());
    }
    
    //메시지를 주고받는 채널 Bean 작성
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("initTopic"); //채널토픽에 새로운 값이 들어오지 않을때 초기설정해둔 initTopic이라는 이름으로 채널 개설
    }
    
    
}
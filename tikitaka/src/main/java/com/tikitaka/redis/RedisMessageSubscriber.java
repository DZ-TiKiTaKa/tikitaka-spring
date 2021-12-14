package com.tikitaka.redis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

//RedisMessageListenerContainer는 redis를 경청하고 있다가 메시지 발행(publish)이 오면 Listener가 처리한다.  
public class RedisMessageSubscriber implements MessageListener {
	// MessageListener 인터페이스는 메시지와 채널에 대한 접근을 제공(MessageListener는 Spring Data
	// Redis에서 제공하는거임)
	public static List<String> messageList = new ArrayList<String>();
	
	@Autowired
	private final SimpMessagingTemplate simpMessagingTemplate;
		
	
//	public RedisMessageSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
//		super();
//		this.simpMessagingTemplate = simpMessagingTemplate;
//	}

	
	public RedisMessageSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
		super();
		this.simpMessagingTemplate = simpMessagingTemplate;
		System.out.println(simpMessagingTemplate);
	}

	


	// pattern을 통해 채널 매칭을 위한 패턴을 정의, 패턴을 통해 여러 채널로부터 구독을 할수 있다.
	// 메세지를 받을때 동작하는 로직 작성
//	@SendTo("/topic")
	public void onMessage(Message message, byte[] pattern) {
		String chatNo = new String(message.getChannel());
		
		System.out.println("chatno 가져오기" + chatNo);
		messageList.add(message.toString());
		
		System.out.println("Message received: " + message.toString());
		//String messages = message.toString();
		
		simpMessagingTemplate.convertAndSend("/topic" + message.getChannel() , message);
		
		
	}



}
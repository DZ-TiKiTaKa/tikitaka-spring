package com.tikitaka.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tikitaka.model.Messagemodel;



@Service
public class RedisSubscriber implements MessageListener {
	

    private final ObjectMapper objectMapper;
    
    //redis에 보내는 template
    private final RedisTemplate redisTemplate;
    
    //react로 전달할 template
    private SimpMessagingTemplate messagingTemplate;
    
//    public RedisSubscriber() {
//		this.objectMapper = new ObjectMapper();
//		this.redisTemplate = new RedisTemplate();
//		this.messagingTemplate = messagingTemplate;
//		};
    
    public RedisSubscriber(ObjectMapper objectMapper, RedisTemplate redisTemplate, SimpMessagingTemplate messagingTemplate) {
		super();
		this.objectMapper = objectMapper;
		this.redisTemplate = redisTemplate;
		this.messagingTemplate = messagingTemplate;
	} 



	@Override
    public void onMessage(Message message, byte[] pattern) {
        try {
        	System.out.println("Redis Sub 호출");

        	String pubMsg = (String)redisTemplate.getStringSerializer().deserialize(message.getBody());
        	 Messagemodel messageModel = objectMapper.readValue(pubMsg, Messagemodel.class);

        	 //channel 방번호
     		String chatNo = new String(message.getChannel());
        	 
     		
     		//sub한 채널에 데이터 전송
     		System.out.println("전송 데이터 : " + messageModel);
     		messagingTemplate.convertAndSend("/topic/"+ chatNo, messageModel);
     		
        } catch (Exception e) {
            System.out.println("error : " + e);
        }
    }

}

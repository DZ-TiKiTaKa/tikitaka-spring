package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tikitaka.dto.JsonResult;
import com.tikitaka.model.MessageModel;
import com.tikitaka.model.PubSubModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
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
//	}

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
        
        	System.out.println("그대로 나오는겨??" + message.toString());
        	 MessageModel messageModel = objectMapper.readValue(pubMsg, MessageModel.class);
        	 
        	  String json = objectMapper.writeValueAsString(pubMsg);
        	  System.out.println(json);
        	 
        	 System.out.println("모오오델" + messageModel);
        	 System.out.println(messageModel.getChatNo());
        	 
        	 PubSubModel model = new PubSubModel();
        	 model.setChatNo(messageModel.getChatNo());
        	 model.setContents(messageModel.getContents());
        	 model.setName(messageModel.getName());
        	 System.out.println(model);
        	 
        	 //channel 방번호
     		String chatNo = new String(message.getChannel());
        	 
     		//messagingTemplate.convertAndSend("/topic/"+ chatNo, messageModel);
     		messagingTemplate.convertAndSend("/topic/"+ chatNo, model);
     
        	 
           
        	 
        } catch (Exception e) {
            System.out.println("error : " + e);
        }
    }

}

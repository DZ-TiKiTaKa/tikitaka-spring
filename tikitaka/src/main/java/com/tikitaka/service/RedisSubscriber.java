package com.tikitaka.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tikitaka.model.CalendarModel;
import com.tikitaka.model.ContactModel;
import com.tikitaka.model.Messagemodel;

@Service
public class RedisSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;

	// redis에 보내는 template
	private final RedisTemplate redisTemplate;

	// react로 전달할 template
	private SimpMessagingTemplate messagingTemplate;

//    public RedisSubscriber() {
//		this.objectMapper = new ObjectMapper();
//		this.redisTemplate = new RedisTemplate();
//		this.messagingTemplate = messagingTemplate;
//		};

	public RedisSubscriber(ObjectMapper objectMapper, RedisTemplate redisTemplate,
			SimpMessagingTemplate messagingTemplate) {
		super();
		this.objectMapper = objectMapper;
		this.redisTemplate = redisTemplate;
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			System.out.println("Redis Sub 호출");

			String pubMsg = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
			Messagemodel messageModel = objectMapper.readValue(pubMsg, Messagemodel.class);
			
			String pubCal = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
			CalendarModel calModel = objectMapper.readValue(pubCal, CalendarModel.class);
			
			// 연락처 전송 
			String pubCon = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
			ContactModel conModel = objectMapper.readValue(pubCon, ContactModel.class);
			
			System.out.println(messageModel.getType() + "df?????????");
			System.out.println(conModel + "-----------------------------------");
			
			// channel 방번호
			String chatNo = new String(message.getChannel());
						
			if(messageModel.getType() == null && 
					calModel.getTitle() != null &&
					calModel.getStartDate() != null &&
					calModel.getEndDate() != null) {
				System.out.println("Redis Sub calModel send!!");
				messagingTemplate.convertAndSend("/topic/" + chatNo, calModel);;
		    
			} else if(messageModel.getType() != null){
				// sub한 채널에 데이터 전송
				messagingTemplate.convertAndSend("/topic/" + chatNo, messageModel);
				System.out.println("전송 데이터 : " + chatNo);
			} else if (messageModel.getType() != null && 
					conModel.getName() != null) {
				messagingTemplate.convertAndSend("/topic/" + chatNo, conModel);
				System.out.println("RedisSubcriber-------------------------" + conModel);
			}
 			
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}

}
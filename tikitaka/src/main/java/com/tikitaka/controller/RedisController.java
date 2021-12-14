package com.tikitaka.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.model.MessageModel;
import com.tikitaka.service.ChatService;
import com.tikitaka.service.SpringRedisService;



@RestController
@RequestMapping("/talk")
public class RedisController {
	// topic 메시지 응답 대기하는 listener
	@Autowired
	private RedisMessageListenerContainer redisMessageListenerContainer;
	//subscriber
	@Autowired
	private MessageListenerAdapter messageListener;
	//DB에 값들을 저장하기 위한 service
	@Autowired
	private ChatService chatService;
	// publisher를 해주는 service
	@Autowired
	private SpringRedisService springredisService;
	
	// topic 으로 메시지 전송할 수 있게 map으로 생성
	private Map<String, ChannelTopic> channel;
	
	@PostConstruct
	public void init() {
		// topic에 담을 map 초기화
		channel = new HashMap<>();
	}
	
	//토픽 리스트
	//다만 스프링 부트서버를 재실행할때마다 init()에 의해 hashMap은 초기화되기 때문에 DB애서 채널(topic)리스트를 받아와야된다.
	@GetMapping("/topic")
	public Set<String> findAllRoom(){
		System.out.println("채팅리스트 출력해라!");
		return channel.keySet();
	}
	
	//토픽 생성
	@PutMapping("/topic/{chatNo}")
	public void createChat(@PathVariable String chatNo) {
		// 신규 topic 생성
		ChannelTopic topic = new ChannelTopic(chatNo);
		// Listener에 등록
		redisMessageListenerContainer.addMessageListener(messageListener, topic);
		// topic map에 저장
		channel.put(chatNo, topic); // channel<String,ChannelTopuc> 으로 Map값이 삽입
		System.out.println("channel:"+channel);
	}
	
	//메시지 Send (토픽을 생성(createChat())해야만 메시지 send가능)
	@PostMapping("/topic")
	public void publishMessage(@RequestBody HashMap<String, Object> result) throws Exception {
		String chatNo = (String) result.get("chatNo").toString();  
		String name = (String) result.get("name").toString();
		String contents = (String) result.get("message").toString();
		
		ChannelTopic topic = channel.get(chatNo); //챗넘버로 원하는 채널 탐색
		System.out.println("토픽확인"+topic);
		MessageModel model = new MessageModel(chatNo, name, contents); 		
//		System.out.println(model);
//		System.out.println(topic);
		springredisService.pub(topic, model);
		
	}
	
	//채팅방 나가기
    @DeleteMapping("/topic/{chatNo}")
    public void deleteTopic(@PathVariable String chatNo) {
        ChannelTopic topic = channel.get(chatNo);
        redisMessageListenerContainer.removeMessageListener(messageListener, topic);
        channel.remove(chatNo);
    }
	
	
	
//	@PostMapping("/send")
//	public void send(@RequestBody HashMap<String, Object> result) {
//		System.out.println("result:" + result);
//		
//		ChatMessage data = new ChatMessage();
//		data.setUserNo(Long.parseLong((String) result.get("userNo")));
//		data.setChatNo(Long.parseLong((String) result.get("chatNo")));
//		data.setType((String)result.get("type"));
//		data.setContents((String)result.get("contents"));
//		data.setReadCount((Integer)result.get("readCount"));
//		
//		chatService.insertMessage(data);
//	
//	}
	
	
}

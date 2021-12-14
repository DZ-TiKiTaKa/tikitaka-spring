package com.tikitaka.controller;


import java.net.Socket;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import com.tikitaka.model.ChatMessage;
import com.tikitaka.model.Chat;
import com.tikitaka.model.MessageModel;
import com.tikitaka.redis.RedisMessageSubscriber;
import com.tikitaka.service.ChatService;
import com.tikitaka.service.ChatMemberService;
import com.tikitaka.service.ChatMessageService;
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
	//DB에 chatroom 관련 데이터 관리하는 service
	@Autowired
	private ChatService chatService;
	//DB의 chatmember테이블의 데이터 관리
	@Autowired
	private ChatMemberService chatmemberService;
	//DB에 message값들을 저장하는 service
	@Autowired
	private ChatMessageService chatmessageService;
	// publisher를 해주는 service
	@Autowired
	private SpringRedisService springredisService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
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
		System.out.println("채팅리스트 출력!"+channel.keySet());
		return channel.keySet();
	}
	
	
	//User.js 에서 쓰는 메소드
	@PutMapping("/topic/{chatNo}")
	public void createChat(@PathVariable String chatNo) {
		System.out.println("들어와랏");
		// 신규 topic 생성
		ChannelTopic topic = new ChannelTopic(chatNo);
		// Listener에 등록 onmessage 출력
		redisMessageListenerContainer.addMessageListener(messageListener, topic);
		// topic map에 저장
		channel.put(chatNo, topic); // channel<String,ChannelTopuc> 으로 Map값이 삽입
		System.out.println(channel);
		
	}
	
	
	@PutMapping("/topic/{userNo}")
	public void createChat(@PathVariable String userNo, @RequestBody String authNo) {
		System.out.println("대화를 신청하는 유저의 authNo = "+ authNo);
		System.out.println("대화하고싶은 유저의 userNo = "+ userNo);
//		//채팅방 개설
//		Chat chat = new Chat();
//		chat.setTitle("그룹채팅일경우 방장 마음대로, 1대1일경우 서로상대의 이름 표시");
//		chat.setContents("컨텐츠-> 불필요한 column이라고 생각해서 나중에 제거하기");
//		chat.setJoin_count(1);//현재 임의로 2설정, 그룹채팅시 인원수 체크해서 값 설정
//		chatService.insertChatRoom(chat);
//		
//		//mybatis useGeneratedKeys옵션을 통해 insert할때 auto_increment된 no값이 chat객체에 들어가게된다.
//		System.out.println("방금 생긴 채팅방의 no: " + chat.getNo());
//
//		//chat_member테이블의 insert 작업
//		//대화를 하기 위해 방을 생성한 authno과 초대받은 userno 이 chat_member에서 관리된다.
//		//지금은 in_time과 out_time을 now()로 설정함, 추후 수정
//		chatmemberService.insertMember(Long.parseLong(authNo), chat.getNo(), "CREATER");
//		chatmemberService.insertMember(Long.parseLong(userNo), chat.getNo(), "MEMBER");
//		
//		System.out.println("방만든 사람-> authNo: "+ authNo + " 과 chatno: " + chat.getNo() + "을 가진 chat_member데이터 생성완료!");
//		System.out.println("참가자-> userno: "+ userNo + " 과 chatno: " + chat.getNo() + "을 가진 chat_member데이터 생성완료!");
		
		//Redis
//		// 신규 topic 생성
//		ChannelTopic topic = new ChannelTopic(chatNo);
//		// Listener에 등록
//		redisMessageListenerContainer.addMessageListener(messageListener, topic);
//		// topic map에 저장
//		channel.put(chatNo, topic); // channel<String,ChannelTopuc> 으로 Map값이 삽입
//		System.out.println(channel);
	}
	
	//ChatRoom.js에서 사용
	//메시지 Send (토픽을 생성(createChat())해야만 메시지 send가능)
	@PostMapping("/topic")
	public void publishMessage(@RequestBody HashMap<String, Object> result) throws Exception {
		String chatNo = (String) result.get("chatNo").toString();  
		String name = (String) result.get("name").toString();
		String contents = (String) result.get("message").toString();
		
		ChannelTopic topic = channel.get(chatNo); //챗넘버로 원하는 채널 탐색
		System.out.println("토픽확인 "+topic);
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
        System.out.println("리스너컨테이너에서 채널삭제-> " + topic);
        channel.remove(chatNo);
        System.out.println("채널Map에서 remove성공");
        
        
        
        //
        
        
        
        
    }

    
//    @PostMapping("/getmsg")
//	public void onMessage(@RequestBody Map<String, Object> map) {
//    	System.out.println("오나용??");
//    	System.out.println(map);
//    	String chatNo = (String)map.get("chatNo");
////    	ChannelTopic topic = channel.get(chatNo);
////    	System.out.println(topic.getTopic());
//    	
//    }
//    
//	
	
	@PostMapping("/send")
	public void send(@RequestBody HashMap<String, Object> result) {
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
		simpMessagingTemplate.convertAndSend("/sub/greetings", "hi hi");
	
	}

	

    
}
package com.tikitaka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.model.Chat;
import com.tikitaka.model.Messagemodel;
import com.tikitaka.service.AlertRedisSubscriber;
import com.tikitaka.service.ChatService;
import com.tikitaka.service.RedisPublisher;
import com.tikitaka.service.RedisSubscriber;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/ring")
@RestController
public class AlertController {
   
////////////redis//////////////////////
// topic에 메시지 발행을 기다리는 Listner
@Autowired
private RedisMessageListenerContainer redisMessageListenerContainer;
// 발행자
@Autowired
private RedisPublisher redisPublisher;
// 구독자
@Autowired
private AlertRedisSubscriber alertRedisSubscriber;
// topic 이름으로 topic정보를 가져와 메시지를 발송할 수 있도록 Map에 저장
private Map<String, ChannelTopic> channel;

////////////DB Service//////////////////////
@Autowired
private ChatService chatService;

@PostConstruct
public void init() {
    // topic 정보를 담을 Map을 초기화
    channel = new HashMap<>();
}

// 유효한 Topic 리스트 반환(사용자의 채팅방 리스트 출력)
@RequestMapping("/alert/{userNo}")
public List<Chat> findAllRoom(@PathVariable Long userNo) {
   
	System.out.println("C : findAllRoom");
	List list = chatService.findChatNumber(userNo);
//   connectsocket(list,userNo);
   return chatService.findChatRoom(userNo);
}

public void connectsocket(List chatnoList, Long userNo) {
	System.out.println("C : connectsocket");
   //방수 만큼 토픽 생성후 , redis 호출
   for(int i=0;i<chatnoList.size();i++) {
	   System.out.println("redis channel 다시 생성하기 init 데이터 전송");
      String chatNo = chatnoList.get(i).toString();
      ChannelTopic topic = new ChannelTopic(chatNo);
//      Messagemodel model = new Messagemodel(userNo,chatNo, "", "연결 되었습니다!", "","","");       
      redisMessageListenerContainer.addMessageListener(alertRedisSubscriber, topic);
//      redisPublisher.publish(topic,model);
   }


}


}
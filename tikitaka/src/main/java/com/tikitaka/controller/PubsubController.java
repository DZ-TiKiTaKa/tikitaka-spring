package com.tikitaka.controller;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tikitaka.dto.JsonResult;
import com.tikitaka.model.Chat;
import com.tikitaka.model.ChatMember;
import com.tikitaka.model.Messagemodel;
import com.tikitaka.model.Notice;
import com.tikitaka.model.User;
import com.tikitaka.model.ChatMessage;

import com.tikitaka.service.ChatMemberService;
import com.tikitaka.service.ChatMessageService;
import com.tikitaka.service.ChatService;
import com.tikitaka.service.RedisPublisher;
import com.tikitaka.service.RedisSubscriber;
import com.tikitaka.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/talk")
@RestController
public class PubsubController {
	
		////////////redis//////////////////////
	    // topic에 메시지 발행을 기다리는 Listner
		@Autowired
	    private RedisMessageListenerContainer redisMessageListenerContainer;
	    // 발행자
		@Autowired
	    private RedisPublisher redisPublisher;
	    // 구독자
		@Autowired
	    private RedisSubscriber redisSubscriber;
	    // topic 이름으로 topic정보를 가져와 메시지를 발송할 수 있도록 Map에 저장
	    private Map<String, ChannelTopic> channel;
	    

	    ////////////DB Service//////////////////////
	    @Autowired
	    private ChatService chatService;
	    @Autowired
	    private ChatMemberService chatmemberService;
	    @Autowired
	    private ChatMessageService chatMessageService;
	    @Autowired
	    private UserService userService;

	    @PostConstruct
	    public void init() {
	        // topic 정보를 담을 Map을 초기화
	        channel = new HashMap<>();
	    }

	    // 유효한 Topic 리스트 반환
	    @GetMapping("/topic")
	    public Set<String> findAllRoom() {
	        System.out.println("채팅 리스트 출력 : " + channel.keySet());
	    	return channel.keySet();
	    }
	    //대화를 신청할 유저와 본인의 채팅방 조회 > 없으면 topic 생성
	    @PutMapping("/searchchat/{userNo}")
	    public String searchchat(@PathVariable String userNo, @RequestBody HashMap<String, Object> auth) {
	    	
	    	System.out.println("C : enterChat");
	    	String authNo = auth.get("token").toString();
	    	System.out.println(authNo);
	    	System.out.println("대화 신청 user : " + authNo);
	    	System.out.println("대화 받는 user : " + userNo);
	    	
	    	//true일경우 채팅방 이미 존재,false는 없음
	    	String chatNo =  chatService.SearchByChatNo(authNo,userNo);
	    	System.out.println("type은 무엇인가요." + chatNo );
	    	
	    	ChannelTopic topic = new ChannelTopic(chatNo);
	    	System.out.println("redis topic 다시 생성하기 init 데이터 전송");
	    	Messagemodel model = new Messagemodel(userNo,chatNo, "name", "contents", "type","readCount","regTime");       
	        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
	        redisPublisher.publish(topic,model);
	    	
	        
	        if(chatNo == "0") {
	        // 신규 topic 생성
	       System.out.println("토픽생성 해볼께요..");
	        // Listener에 등록
	        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
	        // topic map에 저장
	        channel.put(chatNo, topic); // channel<String,ChannelTopuc> 으로 Map값이 삽입
	        System.out.println(channel);
	        }
	        
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("chatNo", chatNo.toString());
		        
	    	return chatNo;
	    }
	    
	    
	    
	    
	    // 신규 Topic을 생성하고 Listener등록 및 Topic Map에 저장
	    @PutMapping("/topic/{userNo}")
	    public Map<String, String> createChat(@PathVariable String userNo, @RequestBody HashMap<String, Object> auth) {
	    	System.out.println("okkkkkkkkkkkkkkkkkkkk");
	    	
	        String authNo = (String)auth.get("token").toString();
	        
	        System.out.println("대화를 신청하는 유저의 authNo = "+ authNo);
	        System.out.println("대화하고싶은 유저의 userNo = "+ userNo);
	        
	        //채팅방 개설
	        Chat chat = new Chat();
	        chat.setTitle("그룹채팅일경우 방장 마음대로, 1대1일경우 서로상대의 이름 표시");
	        chat.setContents("컨텐츠-> 불필요한 column이라고 생각해서 나중에 제거하기");
	        chat.setJoinCount(1);//현재 임의로 1설정, 그룹채팅시 인원수 체크해서 값 설정
	        chatService.insertChatRoom(chat);
	        
	        Long chatNo = chat.getNo();
	        //mybatis useGeneratedKeys옵션을 통해 insert할때 auto_increment된 no값이 chat객체에 들어가게된다.
	        System.out.println("방금 생긴 채팅방의 no: " + chatNo);

	        //chat_member테이블의 insert 작업
	        //대화를 하기 위해 방을 생성한 authno과 초대받은 userno 이 chat_member에서 관리된다.
	        //지금은 in_time과 out_time을 now()로 설정함, 추후 수정
	        chatmemberService.insertMember(Long.parseLong(authNo), chatNo, "CREATER");
	        chatmemberService.insertMember(Long.parseLong(userNo), chatNo, "MEMBER");
	        
	        System.out.println("방만든 사람-> authNo: "+ authNo + " 과 chatno: " + chatNo + "을 가진 chat_member데이터 생성완료!");
	        System.out.println("참가자-> userno: "+ userNo + " 과 chatno: " + chatNo + "을 가진 chat_member데이터 생성완료!");
	        
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("chatNo", chatNo.toString());
	        
	        // Redis
	        // 신규 topic 생성
	        ChannelTopic topic = new ChannelTopic(chatNo.toString());
	        // Listener에 등록
	        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
	        // topic map에 저장
	        channel.put(chatNo.toString(), topic); // channel<String,ChannelTopuc> 으로 Map값이 삽입
	        System.out.println(channel);

	        return map;
	     }
	    
	    
	    @PostMapping("/topic")
	    public void publishMessage(@RequestBody HashMap<String, Object> result) throws Exception {
	    	System.out.println("C : pub message");

	    	String userNo = result.get("userNo").toString();
	    	String name = result.get("name").toString();
	        String chatNo = result.get("chatNo").toString();  
	        String contents = result.get("message").toString();
	        String type =  result.get("type").toString();
	        String readCount = result.get("readCount").toString();
	        String regTime = result.get("regTime").toString();
	        
	        String chatNoo =  result.get("chatNo").toString().replaceAll("\\\"", "");
	        ChannelTopic topic = new ChannelTopic(chatNoo);
	        System.out.println(chatNoo);
	        System.out.println("topic은?" + topic);
	        
	        
	        Messagemodel model = new Messagemodel(userNo,chatNo, name, contents, type,readCount,regTime);       
	        
	        
	        //chat 메시지 DB 저장 메소드
	        ChatMessage chatmessage = new ChatMessage(
	        		Long.parseLong(userNo),
	        		Long.parseLong(chatNoo),
	        		type,
	        		contents,
	        		Integer.parseInt(readCount));
	        System.out.println("넣을 데이터!" + chatmessage);
	        
	        System.out.println("type =" + type);
	        switch(type) {
	        case "TEXT" : 
	        	chatMessageService.insertMessage(chatmessage);
	        	break;
	        }
	        System.out.println("Cast test 완료");
	        
	        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
	        redisPublisher.publish(topic,model);
	     }
	    

	    // Topic 삭제 후 Listener 해제, Topic Map에서 삭제
	    @DeleteMapping("/room/{roomId}")
	    public void deleteRoom(@PathVariable String roomId) {
	        ///ChannelTopic channel = channel.get(roomId);
	      //  redisMessageListenerContainer.removeMessageListener(redisSubscriber, channel);
	       // channel.remove(roomId);
	    }
	    
	    @GetMapping("/chatList/{chatNo}")
	    public Map<String, List<ChatMember>> chatList(@PathVariable String chatNo) {
	    	ChatMember member = new ChatMember();

	    	member.setChatNo(Long.parseLong(chatNo));
	    	List<ChatMember> list = chatMessageService.findByChatNo(member);
	    	Map<String, List<ChatMember>> map = new HashMap<String, List<ChatMember>>();
	    	map.put("list", list);
	    	System.out.println(map);
	    	return map;
	    }

	    @PostMapping("/topic/sendimage")
	    public String sendImage(@RequestParam("file") MultipartFile image) throws Exception {
	        String imgurl = chatMessageService.sendImage(image);
	        System.out.println(imgurl);
	        return imgurl;
	    
	    // chatNo에 해당하는 채팅방의 공지 리스트 
	    @RequestMapping("/topic/890") // 임시로 지정 ...
	    public JsonResult chatNoticeList(@RequestBody HashMap<String, String> data) {
	    	// @PathVariable String ChatNo => useContext에 있는 auth.chat.No 들고 와서 체크
	    	
	    	String chatNo = data.get("chatNo"); 

	    	List<Notice> list = chatService.getNotice(chatNo);
	    	System.out.println(list);


			return JsonResult.success(list);

		}
	 
	    
	    
	}

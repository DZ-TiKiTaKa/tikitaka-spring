package com.tikitaka.controller;

import java.sql.Date;
import java.util.ArrayList;
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

	    // 유효한 Topic 리스트 반환(사용자의 채팅방 리스트 출력)
	    @RequestMapping("/topiclist/{userNo}")
	    public List<Chat> findAllRoom(@PathVariable Long userNo) {
	    	List<Chat> chatnoList = chatService.findChatRoom(userNo);
	    	
	    		    	//System.out.println(chatnoList);
	    	return chatnoList;
	    }

	    
	    
	    //대화를 신청할 유저와 본인의 채팅방 조회 > 없으면 topic 생성
	    @PutMapping("/searchchat/{userNo}/{type}")
	    public String searchchat(@PathVariable Long userNo, @PathVariable String type,@RequestBody HashMap<String, Object> auth) {
	    	
	    	System.out.println("C : SearchChat");
	    	String authNo = auth.get("token").toString();
	    	System.out.println("대화 신청 user : " + authNo);
	    	System.out.println("대화 받는 user : " + userNo);
	    	System.out.println("대화 신청" + authNo + "와 대화 제의" + userNo + "의 채팅방 검색");
	    	System.out.println("TYPE :" + type);
	    	String chatNo = null;
	    	//if(type.equals("PERSONAL")){ 
	    	//개인 톡방일경우
	    	//true일경우 채팅방 이미 존재,false는 없음
	    	
	    	System.out.println("개인 톡방 search");
	    	chatNo =  chatService.SearchByChatNo(authNo,userNo,type);
	    	System.out.println("DB에서 찾아온 chatno은 무엇인가요." + chatNo );
	    	
	    	//}
	    	
	    	
	        if(!chatNo.equals("0")) {
	        	//chatNo로 redis에 다시 channel 생성하기
	        	System.out.println("redis channel 다시 생성하기 init 데이터 전송");
	        	ChannelTopic topic = new ChannelTopic(chatNo.toString());
		    	Messagemodel model = new Messagemodel(userNo,chatNo, "", "연결 되었습니다!", "","","");       
		        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
		        redisPublisher.publish(topic,model);
	        }
	    	
	
	    	return chatNo;
	    }
	    

	    // 신규 Topic을 생성하고 Listener등록 및 Topic Map에 저장
	    @PutMapping("/topic/{userNo}/{type}/{title}")
	    public Long createChat(@PathVariable String userNo, @PathVariable String type ,@PathVariable String title, @RequestBody HashMap<String, Object> auth) {
	    	System.out.println("C : createChat");
	    	System.out.println("title:"+title);
	        String authNo = (String)auth.get("token").toString();
	        String[] arrUserNo = userNo.split(",");
	        
	        System.out.println("대화를 신청하는 유저의 authNo = "+ authNo);
	        System.out.println("대화하고싶은 유저의 userNo = "+ userNo);
	        
	        //채팅방 개설
	        Chat chat = new Chat();
	        chat.setTitle(title);
	        chat.setContents("컨텐츠-> 불필요한 column이라고 생각해서 나중에 제거하기");
	        chat.setJoinCount(arrUserNo.length + 1);
	        chat.setType(type);
	        chatService.insertChatRoom(chat);
	        
	        
	        Long chatNo = chat.getNo();
	        //mybatis useGeneratedKeys옵션을 통해 insert할때 auto_increment된 no값이 chat객체에 들어가게된다.
	        System.out.println("방금 생긴 채팅방의 no: " + chatNo);

	        //chat_member테이블의 insert 작업
	        //대화를 하기 위해 방을 생성한 authno과 초대받은 userno 이 chat_member에서 관리된다.
	        //지금은 in_time과 out_time을 now()로 설정함, 추후 수정
	        chatmemberService.insertMember(Long.parseLong(authNo), chatNo, "CREATER");
	        
	        
	        if(arrUserNo.length == 1) {
	        	chatmemberService.insertMember(Long.parseLong(userNo), chatNo, "MEMBER");
	        }
	        else if(arrUserNo.length > 1) {
	        	for(int i=0; i<arrUserNo.length; i++) {
		        	chatmemberService.insertMember(Long.parseLong(arrUserNo[i]), chatNo, "MEMBER");
		        }
	        }
	        
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

	        return chatNo;
	     }
	    
//	    @PostMapping("/topic")
//	    public void welcomeMessage() {
//	    	
//	    }
	    
	    
	    @PostMapping("/topic")
	    public void publishMessage(@RequestBody HashMap<String, Object> result) throws Exception {
	    	System.out.println("C : pub message");

	    	Long userNo = Long.parseLong(result.get("userNo").toString().replaceAll("\\\"", ""));
	    	String name = result.get("name").toString();
	        String chatNo = result.get("chatNo").toString();  
	        String contents = result.get("message").toString();
	        String type =  result.get("type").toString();
	        String readCount = result.get("readCount").toString();
	        String regTime = result.get("regTime").toString();
	        
	        String chatNoo =  result.get("chatNo").toString().replaceAll("\\\"", "");
	        ChannelTopic topic = new ChannelTopic(chatNoo);
	        System.out.println("topic은?" + topic);
	        System.out.println("전달 컨텐츠" + contents);
	        
	        
	        Messagemodel model = new Messagemodel(userNo,chatNo, name, contents, type,readCount,regTime);       
	        
	        
	        //chat 메시지 DB 저장 메소드
	        ChatMessage chatmessage = new ChatMessage(
	        		userNo,
	        		Long.parseLong(chatNoo),
	        		type,
	        		contents,
	        		Integer.parseInt(readCount));
	        System.out.println("넣을 데이터!" + chatmessage);
	        chatMessageService.insertMessage(chatmessage);
	        System.out.println("Cast test 완료");
	        
	        System.out.println(model);
	        redisPublisher.publish(topic,model);
	        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
	     }
	    

	    // Topic 삭제 후 Listener 해제, Topic Map에서 삭제
	    @DeleteMapping("/topic/{chatNo}")
	    public void outRoom(@PathVariable Long chatNo) {
//	    	System.out.println("C : OutRoom");
//	    			 channel.get(chatNo.toString()); 
//	    			 System.out.println(channel);
//	        redisMessageListenerContainer.removeMessageListener(redisSubscriber, channel);
//	        channel.remove(chatNo);
	    }
	    
	    @GetMapping("/chatList/{chatNo}")
	    public List<ChatMember> chatList(@PathVariable String chatNo) {
	    	System.out.println("ChatList called ..........!!");
	    	System.out.println("chatNo" + chatNo);

	    	List<ChatMember> list = chatMessageService.findByChatNo(Long.parseLong(chatNo));
	    	System.out.println("list!!" + list);
	    	
	    	return list;
	    }

	    @PostMapping("/topic/sendimage")
	    public String sendImage(@RequestParam("file") MultipartFile image) throws Exception {
	        String imgurl = chatMessageService.sendImage(image);
	        System.out.println(imgurl);
	        return imgurl;
	    }
	    
	    // chatNo에 해당하는 채팅방의 공지 리스트 
	    @RequestMapping("/topic/noticeList/{chatNo}") 
	    public JsonResult chatNoticeList(@PathVariable String chatNo, @RequestBody HashMap<String, String> data) {
	    	// @PathVariable String ChatNo => useContext에 있는 auth.chat.No 들고 와서 체크
	    	

	    	List<Notice> list = chatService.getNotice(chatNo);
	    	System.out.println(list);


			return JsonResult.success(list);

		}
	 
	    
	    
	}
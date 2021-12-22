package com.tikitaka.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Chat;
import com.tikitaka.model.ChatMember;
import com.tikitaka.model.Notice;
import com.tikitaka.repository.ChatRepository;

@Service
public class ChatService {
	
	@Autowired
	private ChatRepository chatRepository;

	public void insertChatRoom(Chat chatroom) {
		chatRepository.insert(chatroom);
	}

	public Long findByChatNo(ChatMember chatMember) {
		return chatRepository.findByChatNo(chatMember);
	}

	// chatNo에 해당하는 채팅방의 공지 리스트 
	public List<Notice> getNotice(String chatNo) {
		return chatRepository.findByChatNo(chatNo);
	}

	public String  SearchByChatNo(String authNo, Long userNo,String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authNo", authNo);
		map.put("userNo", userNo);
		map.put("type", type);
		return chatRepository.SearchByChatNo(map);

	}
	
	public List<Chat> findChatRoom(Long userNo){
		return chatRepository.findChatRoom(userNo);
	}

	// chatNo에 해당하는 채팅방의 최근 공지 가져와서 채팅방 상단에 띄우기
	public List<Notice> getRecentNotice(String chatNo) {
		
		return chatRepository.findByRecentNotice(chatNo);
	}

	public List findChatNumber(Long userNo) {
      return chatRepository.findChatNumber(userNo);
   }
	
	
}
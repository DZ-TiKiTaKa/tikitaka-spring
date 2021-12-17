package com.tikitaka.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Chat;
import com.tikitaka.model.ChatMember;
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


	public String  SearchByChatNo(String authNo, String userNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("authNo", authNo);
		map.put("userNo", userNo);
		return chatRepository.SearchByChatNo(map);
	}
	
	
}
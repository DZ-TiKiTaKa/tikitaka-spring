package com.tikitaka.service;

import java.util.List;

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
	
	
}

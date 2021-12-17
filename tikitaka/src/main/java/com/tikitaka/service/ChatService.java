package com.tikitaka.service;

import java.util.List;

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
	
}

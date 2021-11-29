package com.tikitaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Chat;
import com.tikitaka.repository.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	public void insert(Chat chat) {
		chatRepository.insert(chat);

		System.out.println(chat);
		System.out.println(chat);
		System.out.println(chat);
		System.out.println(chat);
		System.out.println("마스터에는 안넣고 디벨로퍼에만 넣어볼게용");
		
		System.out.println("223");
		//test

	}
}

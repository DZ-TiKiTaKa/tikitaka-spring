package com.tikitaka.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Chat;
import com.tikitaka.model.ChatMember;
import com.tikitaka.model.Messagemodel;
import com.tikitaka.repository.ChatRepository;
import com.tikitaka.repository.ChatMemberRepository;
import com.tikitaka.repository.ChatMessageRepository;

@Service
public class ChatMemberService {

	@Autowired
	private ChatMemberRepository chatmemberRepository;

	public void insertMember(Long userno, Long chatno, String role) {
		chatmemberRepository.insert(userno, chatno,role);
	}
	
}

package com.tikitaka.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.ChatMember;
import com.tikitaka.model.ChatMessage;

@Repository
public class ChatMessageRepository {

	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(ChatMessage chatMessage) {
		return 1 == sqlSession.insert("chatmessage.insert", chatMessage);
	}
	
	public boolean sendImage(ChatMessage chatMessage) {
		System.out.println("sendImage DB!!!!!" + chatMessage);
		return 1 == sqlSession.insert("chatmessage.sendImage", chatMessage);
		
	}

	public List<ChatMember> finbyChatNo(Long chatNo) {
		return sqlSession.selectList("chatmessage.findByChatList", chatNo);
	}

}

package com.tikitaka.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.ChatMessage;

@Repository
public class ChatMessageRepository {

	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(ChatMessage data) {
		return 1 == sqlSession.insert("chatmessage.insert", data);
		

	}
	
	

}

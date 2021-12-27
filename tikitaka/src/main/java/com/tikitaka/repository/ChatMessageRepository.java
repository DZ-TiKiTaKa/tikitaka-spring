package com.tikitaka.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<ChatMember> finbyChatNo(Long chatNo) {
		return sqlSession.selectList("chatmessage.findByChatList", chatNo);
	}
	
	public List<ChatMessage> findChatroomlistMsg(Long userNo) {
		return sqlSession.selectList("chatmessage.findChatroomlistMsgByChatNo", userNo);
	}
	
	public String noReadmsgCount(Long userno, Long chatno) {
		Map<String, Long> map  = new HashMap<>();
		map.put("userNo", userno);
		map.put("chatNo", chatno);
		map.put("chatNo2", chatno);
		
		return sqlSession.selectOne("chatmessage.noReadmsgCount",map);
	}

}

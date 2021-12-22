package com.tikitaka.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.Chat;
import com.tikitaka.model.ChatMember;
import com.tikitaka.model.Notice;

@Repository
public class ChatRepository {

	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(Chat chatRoom) {
		return 1 == sqlSession.insert("chat.insert", chatRoom);
		
	}


	public Long findByChatNo(ChatMember chatMember) {
		return sqlSession.selectOne("chatmember.findByChatNo", chatMember);
	}

	// chatNo에 해당하는 채팅방의 공지 리스트 
	public List<Notice> findByChatNo(String chatNo) {
		return sqlSession.selectList("chat.findByChatNo", chatNo);
	}
	
	
	public String SearchByChatNo(Map map) {
		return sqlSession.selectOne("chat.SearchByChatNo", map);
	}	

	
	public List<Chat> findChatRoom(Long userNo) {
		return sqlSession.selectList("chat.findChatroom", userNo);
	}

	// chatNo에 해당하는 채팅방의 최근 공지 가져와서 채팅방 상단에 띄우기
	public List<Notice> findByRecentNotice(String chatNo) {
		return sqlSession.selectList("chat.findByRecentNotice", chatNo);
	}


	// 해당 유저가 속한 채팅번호만 반환
	   public List findChatNumber(Long userNo) {
	      return sqlSession.selectList("chat.findChatNumber", userNo);
	   }
}


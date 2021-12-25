package com.tikitaka.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.Chat;
import com.tikitaka.model.Notice;
import com.tikitaka.model.ChatMember;


@Repository
public class ChatMemberRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(Long userno, Long chatno, String role) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_no", userno);
		map.put("chat_no", chatno);
		map.put("role", role);
		
		return 1 == sqlSession.insert("chatmember.insert", map);
	}
		
	public boolean UpdateOuttime(Long userno, Long chatno) {
		Map<String, Long> map  = new HashMap<>();
		map.put("userNo", userno);
		map.put("chatNo", chatno);
		
		return 1 == sqlSession.update("chatmember.outtimeUpdate",map);
	}

	public List<ChatMember> findUserNoByChatNo(Long chatNo) {
		return sqlSession.selectList("chatmember.findUserNoByChatNo", chatNo);
	}

	


}

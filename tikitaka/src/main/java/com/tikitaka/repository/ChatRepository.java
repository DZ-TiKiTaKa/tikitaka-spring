package com.tikitaka.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.Chat;

@Repository
public class ChatRepository {

	@Autowired
	private DataSource dataSource;

	
	@Autowired
	private SqlSession sqlSession;
	

	
	
	public boolean insert(Chat chat) {
		int count = sqlSession.insert("chat.insert", chat);
		return count == 1;
	}
	
	

}

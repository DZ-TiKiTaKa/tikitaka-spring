package com.tikitaka.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.User;


@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	//로그인할때 id, password 비교
	public User findByIdAndPassword(String email, String password){
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("pwd", password);
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}
	
	public boolean insertUser(User user) {
		return 1 == sqlSession.insert("user.insertUser", user);
	}
}


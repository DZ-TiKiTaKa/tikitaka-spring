package com.tikitaka.repository;

import java.util.HashMap;
import java.util.List;
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

	// 친구 목록 (no, role, name, status, profile 가져오기)	
	public List<User> findLogStatus(String no) {
		return sqlSession.selectList("user.findLogStatus", no);
	}
  
	public boolean UpdateUserState(Long no, Integer status) {
		Map<String, Object> map  = new HashMap<>();
		map.put("no", no);
		map.put("status", status);
		
		return 1 == sqlSession.update("user.UpdateUserState",map);
	}

	public boolean updateProfile(User user) {
		return 1 == sqlSession.update("user.updateProfile", user);
		
	}

	public String findUrl(Long no) {
		return sqlSession.selectOne("user.findUrl", no);
	}

	public User getInfo(Long no) {
		return sqlSession.selectOne("user.info", no);
	}

	public boolean updateProfile(HashMap<String, Object> result) {
		return 1 == sqlSession.update("user.updateProfile", result);
		
	}

	public boolean updateImage(Long no, String url) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("profile", url);
		return 1 == sqlSession.update("user.updateImage", map);
		
	}



}

package com.tikitaka.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.Chat;
import com.tikitaka.model.Notice;

@Repository
public class NoticeRepository {

	@Autowired
	private SqlSession sqlSession;

	public List<Notice> getNotice(String no) {
 		
	return sqlSession.selectList("notice.getNotice", no);	
	}

	public List<Notice> getAlert(Long no) {
		
		List<Notice> list = sqlSession.selectList("notice.getAlert", no);
		System.out.println("repo: " + list);
		return list;
	}

	public List<Chat> getNewchat(Long no) {
		
		return sqlSession.selectList("notice.getNewchat", no);
	}

	
}

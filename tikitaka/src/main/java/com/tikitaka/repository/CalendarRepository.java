package com.tikitaka.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tikitaka.model.Calendar;

@Repository
public class CalendarRepository {

	@Autowired
	private SqlSession sqlSession;

	public boolean addCalendar(Calendar cal) {
		return 1 == sqlSession.insert("calendar.addCalendar", cal);
	}
	
	
}

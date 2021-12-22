package com.tikitaka.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Calendar;
import com.tikitaka.repository.CalendarRepository;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;

	public void addCalendar(Calendar cal) {
		calendarRepository.addCalendar(cal);
	}
}

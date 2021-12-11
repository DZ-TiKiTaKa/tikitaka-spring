package com.tikitaka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Notice;
import com.tikitaka.model.User;
import com.tikitaka.repository.NoticeRepository;
import com.tikitaka.repository.UserRepository;

@Service
public class NoticeService{
	
	//공지, 알림 service
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public List<Notice> getNotice(String no) {
		return noticeRepository.getNotice(no);
	}

	public List<Notice> getAlert(Long no) {
		return  noticeRepository.getAlert(no);
		
	}
	

	
}

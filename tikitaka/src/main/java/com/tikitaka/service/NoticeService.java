package com.tikitaka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.Chat;
import com.tikitaka.model.Notice;
import com.tikitaka.repository.NoticeRepository;

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

	public List<Chat> getNewchat(Long no) {
		return noticeRepository.getNewchat(no);
	}
	
	// chatNo에 해당하는 채팅방의 공지 작성
	public boolean noticeWrite(Notice notice) {
		return noticeRepository.insertNotice(notice);

	}
	
}

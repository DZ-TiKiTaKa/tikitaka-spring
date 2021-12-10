package com.tikitaka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikitaka.model.User;
import com.tikitaka.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(String email, String password) {
		return userRepository.findByIdAndPassword(email, password);
	}

	public void joinUser(User user) {
		userRepository.insertUser(user);
	}

	// 친구 목록 (no, role, name, status, profile 가져오기)	
	public List<User> getLogStatus(String no) {
		return userRepository.findLogStatus(no);
	}

	// 회원 로그인/로그아웃 상태 업데이트 메소드
	public boolean UpdateUserState(Long no, int status) {
		return userRepository.UpdateUserState(no, status);
	}
}

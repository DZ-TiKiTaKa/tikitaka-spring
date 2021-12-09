package com.tikitaka.service;

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
	
	public User getLogStatus(Long no) {
		return userRepository.findLogStatus(no);
	}
}

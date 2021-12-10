package com.tikitaka.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tikitaka.model.User;
import com.tikitaka.repository.UserRepository;

@Service
public class UserService {

	private static String SAVE_PATH = "/tikitaka-images"; // c:저장폴더
	private static String URL_BASE = "/images";
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String email, String password) {
		return userRepository.findByIdAndPassword(email, password);
	}
	
	public void joinUser(User user) {
		userRepository.insertUser(user);
	}

	//회원 로그인/로그아웃 상태 업데이트 메소드
	public boolean UpdateUserState(Long no,int status) {
		return userRepository.UpdateUserState(no,status);
	}

	public String restore(MultipartFile image) throws Exception{
		System.out.println("");
		try {
			if(image.isEmpty()) {
				return "";
			}

			UUID id = UUID.randomUUID();

			String origin = image.getOriginalFilename();
			String extName = origin.substring(origin.lastIndexOf('.') + 1);
			String saveName = id + "." + extName;

			byte[] data = image.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveName);
			os.write(data);
			os.close();
		
			String url = URL_BASE + "/" + saveName;
			//userRepository.updateProfile(url);
			return url;
			
		} catch (IOException e) {
			System.out.println("error:" + e);
		}
		return null;
	}
		
	}


package com.tikitaka.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	// 친구 목록
	public List<User> getFriendlistbyNo(String no) {
		return userRepository.findFriendlistbyNo(no);
	}

	// 회원 로그인/로그아웃 상태 업데이트 메소드
	public boolean UpdateUserState(Long no, int status) {
		return userRepository.UpdateUserState(no, status);
	}

	public String getIamge(Long no) {
		return userRepository.findUrl(no);
	}
	
	public String restore(MultipartFile image, Long no) throws Exception{
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
			
			userRepository.updateImage(no, url);
			return url;
			
		} catch (IOException e) {
			System.out.println("error:" + e);
		}
		return null;
	}

	public List<String> getInfo(Long no) {
		return userRepository.getInfo(no);
		
	}

	public void updateProfile(HashMap<String, Object> result) {
		userRepository.updateProfile(result);
	}

	public void setCode(String[] userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i=0; i<userNo.length-1; i++) {
			map.put("userNo",userNo[userNo.length-1]);
			map.put("codeNo", userNo[i]);
			userRepository.insertCode(map);
		}
		
	}

	public Long findNoByEmail(String email) {
		return userRepository.findNoByEmail(email);
	}

	public boolean findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String getNameByNo(Long userNo) {
		return userRepository.getNameByNo(userNo);
	}

	public List searchInfoByNo(Long userNo) {
		return userRepository.searchInfoByNo(userNo);
		
	}

	public List searchlotinfo(Long userNo) {
		return userRepository.searchlotinfo(userNo);
	}

	public User findUser(Long UserNo) {
		return userRepository.findUser(UserNo);
		
	}

	
		
}


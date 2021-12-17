package com.tikitaka.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tikitaka.model.ChatMember;
import com.tikitaka.model.ChatMessage;
import com.tikitaka.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
	private static String SAVE_PATH = "/chat-images"; // c:저장폴더
	private static String URL_BASE = "/sendimages";
//	@Autowired
//	private RedisTemplate<String,String> redisTemplate;

	@Autowired
	private ChatMessageRepository chatmessageRepository;

	public boolean insertMessage(ChatMessage chatMessage) {
		return chatmessageRepository.insert(chatMessage);
	}

	public List<ChatMember> findByChatNo(ChatMember chatMember) {
		return chatmessageRepository.findByChatNo(chatMember);
	}

	public String sendImage(MultipartFile image) {
		try {
			if(image.isEmpty()) {
				return null;
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
			return url;
			
		} catch (IOException e) {
			System.out.println("error:" + e);
		}
		return null;
		
	}
	
	
}

package com.tikitaka.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
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
	
	private static String FILE_SAVE_PATH = "/chat-files";
	private static String FILE_URL_BASE = "/sendfiles";
		
	@Autowired
	private ChatMessageRepository chatmessageRepository;

	public boolean insertMessage(ChatMessage chatMessage) {
		return chatmessageRepository.insert(chatMessage);
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
			System.out.println("sendImage error : " + e);
		}
		return null;
		
	}
	
    // 채팅방 파일 전송
	public String sendFile(MultipartFile file) {
		try {
			if(file.isEmpty()) {
				return null;
			}
			
			UUID fileId = UUID.randomUUID();
			// 파일명 
			String originalFile = file.getOriginalFilename();
			// 파일명 중 확장자만 추출                                   // lastIndexOf('.') - 뒤에 있는 .의 index 번호 
			String originalFileExtension = originalFile.substring(originalFile.lastIndexOf('.'));
			// UUID + 확장자명
			String saveName = fileId + originalFileExtension;
			
			// 매개값으로 주어진 바이트 배열의 모든 바이트를 출력 스트림으로 보냄
			byte[] fileData = file.getBytes();
			
			// FileOutputStream 생성
			OutputStream os = new FileOutputStream(FILE_SAVE_PATH + "/" + saveName);
			os.write(fileData);
			// OutputStream을 더 이상 사용하지 않을 경우 close()를 호출해서 사용했던 시스템 자원을 풀어줌
			os.close();

			String fileUrl = FILE_URL_BASE +  "/" + saveName;
			
			return fileUrl;	
			
		} catch (IOException e) {
			System.out.println("sendFile error : " + e);
		}
		return null;
	}


	public List<ChatMember> findByChatNo(Long chatNo) {
		return chatmessageRepository.finbyChatNo(chatNo);
	}



	
	
	
	public List<ChatMessage> findChatroomlistMsg(Long userNo) {
		return chatmessageRepository.findChatroomlistMsg(userNo);
	}
	
	
}

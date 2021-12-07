package com.tikitaka.test;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.tikitaka.model.Chat;

@Ignore
@SpringBootTest
public class RedisTempalateTest {
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	
	
	@Test
	public void testStrings() {
		Chat chat = new Chat();
		chat.setContents("hello");
		chat.setNo(1221L);
		// 데이터 주기
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String key = chat.getNo().toString();
		// when
		valueOperations.set(key, chat.getContents());
		
		// then
		String value = valueOperations.get(key);
		//Boolean expire = redisTemplate.expire(key, 5, TimeUnit.SECONDS);
		
		assertThat(value).isEqualTo(chat.getContents());
		//assertThat(expire).isTrue();
	}
	
//	@Test
//	public void testSet() {
//		// 데이터 주기
//		SetOperations<String, String> setOperations = redisTemplate.opsForSet();
//		String key = "userSet";
//		
//		// when
//		setOperations.add(key, "u", "s", "e", "r");
//		
//		// then
//		Set<String> members = setOperations.members(key);
//		Long size = setOperations.size(key);
//		System.out.println("members: " + members);
//		assertThat(members).containsOnly("u","s","e","r");
//		assertThat(size).isEqualTo(4);
//
//	}
	
//	@Test
//    void testHash() {
//        // given
//        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
//        String key = "hashKey";
//
//        // when
//        hashOperations.put(key, "user", "member");
//
//        // then
//        Object value = hashOperations.get(key, "user");
//        assertThat(value).isEqualTo("member");
//
//        Map<Object, Object> entries = hashOperations.entries(key);
//        assertThat(entries.keySet()).containsExactly("user");
//        assertThat(entries.values()).containsExactly("member");
//
//        Long size = hashOperations.size(key);
//        assertThat(size).isEqualTo(entries.size());
//    }
}

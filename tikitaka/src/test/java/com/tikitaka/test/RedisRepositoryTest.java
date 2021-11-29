package com.tikitaka.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tikitaka.RedisConfiguration;
import com.tikitaka.model.User;
import com.tikitaka.repository.UserRedisRepository;

@SpringBootTest(classes= {RedisConfiguration.class})
public class RedisRepositoryTest {
	
	@Autowired
	private UserRedisRepository urrp;
	
	@Test
	public void test() {
		System.out.println("RedisRepository called...");
		//User user = new User(null, "tiki", 30);
		
		// when
		//urrp.save(user);
		
		//urrp.findById(user.getId());
	}
}

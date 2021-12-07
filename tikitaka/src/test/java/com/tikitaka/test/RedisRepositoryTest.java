package com.tikitaka.test;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tikitaka.repository.UserRedisRepository;

@Ignore
//@SpringBootTest(classes= {RedisConfiguration.class})
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

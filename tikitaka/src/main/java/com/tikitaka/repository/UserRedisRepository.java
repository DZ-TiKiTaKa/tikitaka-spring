package com.tikitaka.repository;

import org.springframework.data.repository.CrudRepository;

import com.tikitaka.model.User;

public interface UserRedisRepository extends CrudRepository<User, String> {
	
}

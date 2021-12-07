package com.tikitaka.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RedisController {

	@PostMapping("/send")
	public String send() {
		
		return "success";
	}
	
}

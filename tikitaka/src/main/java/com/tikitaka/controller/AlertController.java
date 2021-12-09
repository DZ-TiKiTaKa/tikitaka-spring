package com.tikitaka.controller;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikitaka.service.AlertService;

@RestController
public class AlertController {
	
	@Autowired
	private AlertService alertService;

	@RequestMapping("/main")
	public void gettest() {
		String user_no = "1"; 
				System.out.println("notice 가져오기 메소드");
	}
}

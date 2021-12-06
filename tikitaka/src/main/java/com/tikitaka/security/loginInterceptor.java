package com.tikitaka.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class loginInterceptor extends HandlerInterceptorAdapter{


	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//기본 프레임만 만들어둠
		
		return true;
	}



}

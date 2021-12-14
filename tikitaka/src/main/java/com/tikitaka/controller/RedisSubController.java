package com.tikitaka.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RedisSubController implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

}

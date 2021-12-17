package com.tikitaka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket")
		.setAllowedOriginPatterns("*")
		.withSockJS();
	}
	
	
	@Override 
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//TT로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메세지는 MessageMapping이 달린 메서드로 라우팅 된다.
		//topic 주소가 있는 사람들에게 보낸다.
		registry.enableSimpleBroker("/topic"); // sub
		registry.setApplicationDestinationPrefixes("/pub"); // pub
		
		System.out.println("WebsocketConfig!");
	}
	
}
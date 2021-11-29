package com.tikitaka;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * @SpringBootApplication에는 
 * 
 * @SpringBootConfiguration
 * @ComponentScan
 * @EnableAutoConfiguration 3가지 역할을 수행함.
 * 
 * 1. @ComponentScan
 *  - @Component @Configuration @Repository @Service @Controller @RestController
 *  - 하위 패키지에서 위와 같은 어노테이션을 찾아 bean으로 등록한다.
 * 
 * 2. EnableAutoConfiguration
 *  -  사전에 정의한 라이브러리들을  Bean으로 등록해 주는 어노테이션
 *  - 사전 정의 파일 위치
 *  : Dependencies > spring-boot-autoconfigure > META-INF > spring.factories
 *  
 *  3. @SpringBootConfiguration
 *   - 스프링 부트의 설정을 나타내는 어노테이
 * */
@SpringBootApplication
public class RedisTemplateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RedisTemplateApplication.class);
	}
}

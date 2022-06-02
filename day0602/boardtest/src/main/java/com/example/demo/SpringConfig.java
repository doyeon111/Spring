package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//객체를 제공해주는 xml대신 클래스로 만들어 줌

//객체를 제공해주는 환경설정 파일
@Configuration
public class SpringConfig {
	
	//객체 제공자
	@Bean
	public CommonsMultipartResolver multipartResolver() { //메소드의 이름은 id의 역할을 한다.
		return new CommonsMultipartResolver();
	}
}

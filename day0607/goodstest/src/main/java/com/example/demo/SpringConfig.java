package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class SpringConfig {
	
	@Bean
	public CommonsMultipartResolver multipartResolver() { //파일 업로드를 하기 위함
		return new CommonsMultipartResolver();
	}
}

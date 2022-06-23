package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new MemberInterceptor())
		.addPathPatterns("/member/**")
		.excludePathPatterns("/member/images/**"); //이 이미지는 intercepter로 부터 분리되었다.
	}

	
}

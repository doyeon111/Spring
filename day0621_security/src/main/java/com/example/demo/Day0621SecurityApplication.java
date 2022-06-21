package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@SpringBootApplication
public class Day0621SecurityApplication {
	
	@Bean
	public PasswordEncoder passwordEncoder() { //스프링 시큐리티를 위한 암호 생성
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Day0621SecurityApplication.class, args);
		
		/*
		String pwd = "tiger"; //이것을 암호화해야함.
		String pwd2 = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(pwd); //암호화 과정을 거침(암호를 암호화 해주는 과정)
		System.out.println(pwd2); */
		
		//임의로 멤버생성
//		DBManager.insert(new MemberVO("tiger", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("tiger"), "홍길동", "user")); //암호화함
//		DBManager.insert(new MemberVO("lion", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("lion"), "이순신", "admin"));
//		
//		System.out.println("insert ok");
	} 

}

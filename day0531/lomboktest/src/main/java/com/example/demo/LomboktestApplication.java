package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LomboktestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LomboktestApplication.class, args);
		
		
		Person p = new Person();
		p.setName("홍길동");
		p.setAge(20);
		System.out.println(p.getName());
		System.out.println(p.getAge());

	}

}

package com.example.demo.util;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//기능을 동작하도록 함
@Component
//어노테이션 기반의 스케줄링 설정
@EnableScheduling
public class SistUtil {
	/*
	//10초마다 동작하도록 함
	@Scheduled(fixedRate = 10000)
	public void test() {
		//System.out.println("콜콜!!");
	}
	
	//크론으로 시간 설정   (초  분 시 일  월)
	@Scheduled(cron = "10 26 9 21 * ?") //매월 21일  9시 26분에 메일 발송이라는 글자 출력
	public void test2() {
		System.out.println("메일발송");
	} */
	
	
	
}

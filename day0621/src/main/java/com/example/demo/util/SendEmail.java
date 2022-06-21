package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@Component
@EnableScheduling
@Setter
public class SendEmail {

	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private MailSender mailSender;
	
	//매월 21일 11시에 동작하도록 설정
	@Scheduled(cron = "30 35 11 21 * ?")
	public void send() {
		List<EmpVO> list = dao.findAll();
		for(EmpVO e : list) {
			String ename = e.getEname();
			String email = e.getEmail();
			int total = e.getSalary() + e.getComm();
			if(email != null && !email.equals("")) {
				try {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom("lby9905@gmail.com"); //gmail 설정
					mailMessage.setTo(email);
					mailMessage.setSubject(ename + "님, 이번달 급여 명세서입니다. [담당자: 김도연]");
					mailMessage.setText(ename + "님, 이번달 실수령액은 " + total + "만원 입니다.");
					
					mailSender.send(mailMessage);
					
					System.out.println(ename + "에게 메일 전송하였습니다.");
				} catch(Exception ex) {
					System.out.println("예외발생: " + ex.getMessage());
				}
			}
		}
		
		System.out.println("메일전송을 모두 완료하였습니다.");
 	}
	
}

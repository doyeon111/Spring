package com.example.demo.controller;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;
import lombok.Setter;

@RestController
@Setter
public class MemberController {
	
	@Autowired
	private MailSender javaMailSender;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/sendMail2")
	public String sendMail2() {
		 mailSender.send(new MimeMessagePreparator() {
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setFrom("lby9905@gmail.com");
			     message.setTo("lby9905@naver.com");
			     message.setSubject("my subject");
			     message.setText("my text <img src='cid:myLogo'>", true);
			     message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
			     message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
			   }
			 });
		 return "OK";
	}
	
	
	
	
	//메일
	@RequestMapping("/sendMail")
	public String sendMail(String to) {
		String no = ""; 
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		//랜덤으로 4자리 숫자의 난수를 만든다.
				Random r = new Random();
				//4자리 이기 때문에 4번
				no += r.nextInt(10);
				no += r.nextInt(10);
				no += r.nextInt(10);
				no += r.nextInt(10);
		mailMessage.setSubject("회원가입안내");
		mailMessage.setFrom("lby9905@gmail.com"); //gmail설정
		mailMessage.setText(no);
		mailMessage.setTo(to); //naver or nate 이메일 설정
		
		
		
		try {
			javaMailSender.send(mailMessage);
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		
		return no;
	}
	

	@RequestMapping("/sendNUM")
	public String sendNUM(String to) { //받는 사람 전화번호
		String from = "01025598279"; //강사님 전화번호
		String no = ""; 
		
		//랜덤으로 4자리 숫자의 난수를 만든다.
		Random r = new Random();
		//4자리 이기 때문에 4번
		no += r.nextInt(10);
		no += r.nextInt(10);
		no += r.nextInt(10);
		no += r.nextInt(10);
		
		BitSms sms = new BitSms();
		sms.sendMsg(from, to, no); //from(강사님번호) 전화번호로부터 to(사용자가 입력한 전화번호) 에게 4자리 랜덤숫자를 보낸다.
		
		return no;
	}
	
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Service //자동으로 동작하기 위해서 service 어노테이션을 사용
@Setter
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberDAO dao;

	
	@Override
	//loadUserByUsername이라는 메소드를 오버라이딩 해야함./ username을 매개변수로 받아 id에 해당하는 회원정보를 가져옴.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //username은 userid라고 보면 된다.
		// TODO Auto-generated method stub
		
		System.out.println("사용자 로그인 처리");
		//db로부터 회원정보를 가지고와서 userDetail서비스를 만든다.
		
		MemberVO m = dao.findById(username);
		
		//예외발생
		//If문을 사용하여 회원이 있는지 없는지(vo에서 가져옴) 판별, 
		//회원이 있으면 그 아이디에 해당하는 유저를 userDetails 객체를 생성하여 가져옴.
		if(m == null) { //username이 없으면 예외발생
			throw new UsernameNotFoundException(username);
		}
		
		//예외가 발생되지 않으면 details에 객체 생성
		UserDetails user = User.builder()
							.username(username)
							.password(m.getPwd()) //암호화된 비밀번호를 가져옴
							.roles(m.getRole()).build(); 
		
		
		return user;
	}

}

package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//스프링 시큐리티 환경설정

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		//매개변수로 전달받은 http를 통해서 환경설정한다.
		http.authorizeRequests() //요청에 따라서 승인할 것이다.
		.mvcMatchers("/", "/hello", "/all/**").permitAll() //요청이름 : 이러한 요청은 아무나 다 사용할 수 있다. (로그인 안해도 사용할 수 있다.) / root, /hello, /all/은 아무나 사용가능
		.mvcMatchers("/admin/**").hasRole("admin")// admin이라는 모든 요청들은 admin이라는 role이 있어야 한다.
		.anyRequest().authenticated();//로그인만 하면 된다.
		
		//시큐리티에서 제공해주는 로그인 폼이 아닌 직접 만든 로그인 폼
		http.formLogin().loginPage("/login").permitAll() //로그인하는 폼은 /login 페이지에 요청, 인증절차 없이 아무나 사용할 수 있다.
		.defaultSuccessUrl("/service1"); //로그인을 성공하고 난 후 이동
		
		//로그아웃 
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃에 대한 요청은 /logout이라는 페이지 사용
		.invalidateHttpSession(true); //로그아웃을 하면 세션을 파기함  ==> (true는 파기한다는 것)
		
		//나머지는 http 기본에 따라서 사용
		http.httpBasic();
		
		
	}
	
}

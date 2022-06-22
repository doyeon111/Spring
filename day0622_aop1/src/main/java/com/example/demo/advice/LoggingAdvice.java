
package com.example.demo.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {	
	//joinPoint : 지점 하나하나
	
	//어드바이스가 동작할 타겟을 설정한다.
	//com.example.demo.controller 패키지의 하위의 모든 클래스들의 모든 메소드들을 타겟으로 설정
	@Pointcut("execution(public * com.example.demo.controller..*(..))") //핵심 관심 사항
	public void test() {	// <--- 그 타겟을 대표하는 id이다.(메소드 이름이 아이디가 됨)	
	}
	
	//정상적인 동작, 비정상적인 동작 모두 동작한다. (공통 관심 사항)
	@After("test()")  //test라는 아이디를 갖는 pointcut에 설정된 메소드들이 동작한 후에 다음의 메소드가 동작한다.
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		System.out.println(methodName + "메소드가 정상적으로 완료되었습니다.");
	}
	
	
//	@AfterThrowing("test()") //에러가 날 때만 동작
//	public void afterError(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().toShortString();
//		System.out.println(methodName + "메소드가 비정상적으로 완료되었습니다.");
//	}
//	
//	@AfterReturning("test()")
//	public void afterOK() {
//		System.out.println("타깃 메소드가 정상적으로 완료되었습니다.");
//	}
	
	
//	@Before("test()")
//	public void before(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().toShortString();
//		System.out.println("타깃 메소드 동작하기 전입니다.");
//	}
	
	/*
	@Around("test()")
	public Object pro(ProceedingJoinPoint joinPoint) {
		Object re = null;
		String methodName = joinPoint.getSignature().toShortString();
		long start  = System.currentTimeMillis();
		System.out.println(methodName + "이 동작하기 전입니다.");
		try {
			re = joinPoint.proceed(); //타깃 메소드를 동작
		}catch (Throwable e) {
		}
		long end = System.currentTimeMillis();
		System.out.println(methodName + "이 동작한 후입니다.");
		System.out.println("걸린시간:"  + (end-start));
		return re;
	} */
}

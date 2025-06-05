package com.sist.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ReactQueryAOP {
	@Around("execution(* com.sist.web.controller.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		Object obj = null;
		long start = System.currentTimeMillis();
		System.out.println("Client 요청: "+jp.getSignature().getName());
		obj = jp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("소요 시간:" +(end-start));
		
		return obj;
	}
	@AfterReturning(value = "execution(* com.sist.web.controller.*Controller.*(..))", returning = "obj")
	public void afterReturning(Object obj)
	{
		System.out.println(obj.toString()); // json
	}
	
}

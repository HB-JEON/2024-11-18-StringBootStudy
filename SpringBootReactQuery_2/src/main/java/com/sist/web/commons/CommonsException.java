package com.sist.web.commons;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.*;
// 공통 예외 처리
@RestControllerAdvice
public class CommonsException {
	@ExceptionHandler(exception = Exception.class)
	public void exception(Exception e)
	{
		System.out.println("=============== error ==============");
		e.printStackTrace();
		System.out.println("====================================");
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException e)
	{
		System.out.println("========= error(IOException) =========");
		e.printStackTrace();
		System.out.println("======================================");
	}
}

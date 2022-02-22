package com.hibernate.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
	
	@Before("execution(* com.hibernate.demo.controller.UserController.*(..))")
	public void logRestController (){
		System.out.println("Log Printout");
		LOGGER.info("RestController called");
	}

}

package com.isaac.javaweb.spring.finalexam.tool;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogTool {

	private Logger log = Logger.getLogger(getClass());
	
	@AfterThrowing(value = "execution(* com.isaac.javaweb.spring.finalexam.service.impl.*.*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinpoint, Exception ex) {
		log.error("调用函数："+joinpoint.toLongString()+" 出现异常！");
		log.error("异常信息："+ex.getMessage());
	}
	
	@Before("execution(* com.isaac.javaweb.spring.finalexam.service.impl.*.*(..))")
	public void logBefore(JoinPoint joinpoint){
		log.info("开始调用函数："+joinpoint.toLongString());
		log.info("参数为：" + Arrays.toString(joinpoint.getArgs()));
	}
	
	@After("execution(* com.isaac.javaweb.spring.finalexam.service.impl.*.*(..)) && args(product,..)")
	public void logAfter(JoinPoint joinpoint, String product){
		log.info("开始调用函数："+joinpoint.toLongString()+"  参数为："+ product);
		System.out.println("开始调用函数："+joinpoint.toLongString()+"  参数为："+ product);
	}
}

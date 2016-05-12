package com.isaac.javaweb.SpringAOPTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SpringAspectLoging {
	
	@Before("execution(* com.isaac.javaweb.SpringAOPTest.DBManager.DB*(..))")
	private void LogBeforeFunction(JoinPoint jp){
		System.out.println("调用函数: "+jp.toLongString());	
	}
	
	@Before("execution(* com.isaac.javaweb.SpringAOPTest.DBManager.getProductInventory(..)) && args(product,..) ")
	private void LogBeforeFunction(JoinPoint jp, String product){
		System.out.println("调用函数: "+jp.toLongString()+" 参数为: "+product);	
	}
	
	@AfterReturning("execution(* com.isaac.javaweb.SpringAOPTest.DBManager.DB*(..))")
	private void LogAfterFunction(JoinPoint jp){
		System.out.println("调用函数: "+jp.getSignature().getName()+" 结束，正常返回。");	
	}
	
	@AfterReturning(value="execution(* com.isaac.javaweb.SpringAOPTest.DBManager.getProductInventory(..))",
					argNames="retVal", returning="retVal")
	private void LogAfterFunction(JoinPoint jp, Object retVal){
		System.out.println("调用函数: "+jp.getSignature().getName()+" 结束，正常返回。");
		System.out.println("返回值："+retVal.toString());
	}
	
	@AfterThrowing("execution(* com.isaac.javaweb.SpringAOPTest.DBManager.*(..))")
	private void FinishFunction(JoinPoint jp){
		System.out.println("调用函数: "+jp.toLongString()+" 出现异常，异常返回！");	
	}
	

}

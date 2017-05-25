package com.bjsxt.annotation.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * aop提供动态代理：
 * 注意切点表达式，这里是作用在service下的包类方法上，因为UserService类没有实现
 * 任何接口，所以这里aop会使用cglib实现动态代理
 * 而如果切入表达式式在dao包类方法上，则此时daoImpl是实现了接口的，所以这里使用的就会是
 * jdk动态代理的方式实现
 * @author lsx
 *
 */
@Aspect
@Component
public class LogInterceptor {
	@Pointcut("execution(public * com.bjsxt.service..*.add(..))")
	public void myMethod(){};
	
	@Before("myMethod()")
	public void before() {
		System.out.println("method before");
	}
	
	@Around("myMethod()")
	public void aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method around start");
		pjp.proceed();
		System.out.println("method around end");
	}
	
}

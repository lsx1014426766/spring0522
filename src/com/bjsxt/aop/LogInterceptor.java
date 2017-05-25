package com.bjsxt.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
/**
 * aop实现动态代理的方式一：
 * jdk提供的动态代理  实现方式   实现invoke方法
 * @author lsx
 *
 */
public class LogInterceptor implements InvocationHandler {
	/**
	 * 目标作用类，增强逻辑就是基于这个目标类的方法的，
	 * 在create这个类（constructor）时就需要指定它作用的目标类，或者时set注入
	 */
	
	private Object target;
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void beforeMethod(Method m) {
		
		System.out.println(m.getName() + " start");
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		beforeMethod(m);
		m.invoke(target, args);
		return null;
	}
}

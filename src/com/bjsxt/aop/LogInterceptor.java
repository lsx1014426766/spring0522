package com.bjsxt.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

public class LogInterceptor implements InvocationHandler {
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

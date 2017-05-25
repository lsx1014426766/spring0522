package com.bjsxt.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib动态代理实现

Cglib是一个优秀的动态代理框架，
它的底层使用ASM在内存中动态的生成被代理类的子类，
使用CGLIB即使代理类没有实现任何接口也可以实现动态代理功能。
CGLIB具有简单易用，它的运行速度要远远快于JDK的Proxy动态代理
 * @author lsx
 *
 */
public class CglibProxy implements MethodInterceptor{

	/**o  被代理类
	 * method 作用的方法
	 * args 方法参数
	 * methodProxy 代理类
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		    System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
	        System.out.println(method.getName());
	        Object o1 = methodProxy.invokeSuper(o, args);
	        System.out.println("++++++after " + methodProxy.getSuperName() + "++++++");
	        return o1;
	}

}

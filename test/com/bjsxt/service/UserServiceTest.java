package com.bjsxt.service;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

import org.junit.Test;

import com.bjsxt.aop.CglibProxy;
import com.bjsxt.aop.LogInterceptor;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.dao.impl.UserDAOImpl;
import com.bjsxt.model.User;
import com.bjsxt.spring.BeanFactory;
import com.bjsxt.spring.ClassPathXmlApplicationContext;

/**
 * lesson1:自实现模拟ClassPathXmlApplicationContext
 * @author lsx
 *
 */
public class UserServiceTest {

	@Test
	public void testAdd() throws Exception {
		BeanFactory applicationContext = new ClassPathXmlApplicationContext();
		UserService service = (UserService)applicationContext.getBean("userService");
		User u = new User();
		u.setId(1);
		u.setName("zhangsan");
		service.add(u);
	}
	/**
	 * java 反射机制  通过代理类实现aop
	 */
	@Test
	public void testProxy() {
		UserDAO userDAO = new UserDAOImpl();
		LogInterceptor li = new LogInterceptor();
		li.setTarget(userDAO);
		UserDAO userDAOProxy = (UserDAO)Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), userDAO.getClass().getInterfaces(), li);
		//UserDAO userDAOProxy = (UserDAO)Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), Class[]{UserDAO.class}, li);
		System.out.println(userDAOProxy.getClass());
		userDAOProxy.delete();
		userDAOProxy.save(new User());
		
	}
	
	
	
	/*class $Proxy4 implements UserDAO 
	 * {
	 * 	save(User u) {
	 * 	Method m = UserDAO.getclass.getmethod 
	 * li.invoke(this, m, u)
	 * }
	 * }
	 */
	/**
	 * 测试通过cglib实现动态代理方式
	 */
	@Test
     public void testCglibProxy(){
    	 CglibProxy cglibProxy = new com.bjsxt.aop.CglibProxy();

         Enhancer enhancer = new Enhancer();
         enhancer.setSuperclass(UserService.class);
         enhancer.setCallback(cglibProxy);

         UserService o = (UserService)enhancer.create();
         o.delete();
    	 
     }

}

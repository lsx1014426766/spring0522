package com.bjsxt.service;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;


/**
 * lesson2:使用spring的ClassPathXmlApplicationContext
 * @author lsx
 *
 */
public class UserServiceTest2 {

	@Test
	public void testAdd() throws Exception {
	
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans2.xml");
		UserService service = (UserService)applicationContext.getBean("userService");
		//UserService service2 = (UserService)applicationContext.getBean("userService");
		//默认单例singleton,设为 scope="prototype" 多例
		//System.out.println(service==service2);
		UserDAO DAO = (UserDAO)applicationContext.getBean("userDAO");
		//注入提供无参的构造函数，如果不给通过构造参数实例化的话，如果有的属性没有初始化，这里就得手动调用相应的方法了
		//service.setUserDAO(DAO);
		//System.out.println(DAO);
		User u = new User();
		u.setId(1);
		u.setName("zhangsan");
		service.add(u);
		
		applicationContext.destroy();
	}

}

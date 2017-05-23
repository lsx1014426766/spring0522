package com.bjsxt.service;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;


/**
 * jdbc真实连接数据库
 * @author lsx
 *
 */
public class UserServiceTest4 {

	@Test
	public void testAdd() throws Exception {
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans_jdbc.xml");
		UserService service = (UserService)applicationContext.getBean("userService");
		
		User u = new User();
		u.setId(1);
		u.setName("lsx4444");
		service.add(u);
		
		applicationContext.destroy();
	}

}

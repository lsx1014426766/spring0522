package com.bjsxt.service;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;


/**
 * xml配置aop方式
 * @author lsx
 *
 */
public class UserServiceTest3 {

	@Test
	public void testAdd() throws Exception {
		/**error1:
		 * java.lang.IllegalStateException: BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext
	       没有指定xml文件的位置


error2:
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userService' defined in class path resource [beans.xml]: 1 constructor arguments specified but no matching constructor found in bean 'userService' (hint: specify index and/or type arguments for simple parameters to avoid type ambiguities)
添加对应的构造方法，区别于上面的set方法注入

		 */
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans_aop.xml");
		UserService service = (UserService)applicationContext.getBean("userService");
		
		User u = new User();
		u.setId(1);
		u.setName("zhangsan");
		service.add(u);
		
		applicationContext.destroy();
	}

}

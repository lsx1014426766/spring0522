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
public class UserServiceTest1 {

	@Test
	public void testAdd() throws Exception {
		/**error1:
		 * java.lang.IllegalStateException: BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext
	       没有指定xml文件的位置


error2:
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userService' defined in class path resource [beans.xml]: 1 constructor arguments specified but no matching constructor found in bean 'userService' (hint: specify index and/or type arguments for simple parameters to avoid type ambiguities)
添加对应的构造方法，区别于上面的set方法注入

		 */
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans1.xml");
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
		/**
		 * 对于这里非web方式启动的spring加载配置文件，要想停止应用上下文，需要手动调用它的destroy方法
		 * 如果bean指定scope=prototype,则不会调用destroy方法，没有监听  单例可以
		 * bean加载的时机是可以指定的：
		 *   默认是随着加载配置文件，容器初始化的时刻一起将其下的bean实例化
		 *   也可以通过属性lazy-init来指定当调用ctx.getBean("dd")的时刻即用的时候才去实例化
		 *   这样就不会在一开始等容器启动就花费好多时间在实例化众多的bean上
		 *   
		 *   多个bean都有init-method,destroy-method时：
		 *  	daoimpl init
				service init
				user saved!
				service destroy
				daoimpl destroy
		 */
		applicationContext.destroy();
	}

}

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
		u.setId(6);
		u.setName("lsx4444");
		/**
		 * 注意：这里在执行 db操作时，会考虑到实体和表之间的映射关系
		 * 在实体类上必须注解上@Entity 主键上@Id这样的标识
		 * 如果db中主键是需要人为指定的，就不能再插入操作时在表id列加上@generateValue这样的标识
		 * 因为这本身就是矛盾的，如果给了这个自生成的主键标识，数据库又没指定autoincrement，这样就会报错：
		 * not give a default value
		 */
		service.add(u);
		
		applicationContext.destroy();
	}

}

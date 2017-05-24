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
	
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans_xml.xml");
		UserService service = (UserService)applicationContext.getBean("userService");
		
		User u = new User();
		u.setId(7);
		u.setName("zhangsan");
		service.add(u);
		
		applicationContext.destroy();
	}
/**
 * org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException: Line 28 in XML document from class path resource [beans_xml.xml] is invalid; nested exception is org.xml.sax.SAXParseException; lineNumber: 28; columnNumber: 66; cvc-complex-type.2.4.a: 发现了以元素 'aop:pointcut' 开头的无效内容。应以 '{"http://www.springframework.org/schema/aop":aspect}' 之一开头。
 * 



 */
}

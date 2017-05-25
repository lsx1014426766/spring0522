package com.bjsxt.service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.dao.LogDAO;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;


@Service(value="userService")//默认首字母小写
public class UserService {
	private UserDAO userDAO;  
	private LogDAO logDAO;
	//@Transactional(readOnly=true)
	public void add(User user) {
		userDAO.save(user);
	}
	public void delete(){
		   System.out.println("service delete for test cglibproxy");
		   
	   }
	public User getUser(int id) {
		return null;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	/**
	 * 注意此注解最好是加载set方法上，更清楚它是要做什么 注入  调用set方法
	 * 默认是byType方式注入，如果不能清楚区分到底是注入那个，还需要指定名字
	 * @Qualifier需要给定它是哪个bean的名字name
	 * @param userDAO
	 */
	/*@Autowired
	public void setUserDAO(@Qualifier("userDAO")UserDAO userDAO) {
		this.userDAO = userDAO;
	}*/
	/**
	 * JSR标准  使用的common-annatation包
	 * 先查找匹配的类型那个，找到多个就报错，再按照名字找，找不到再报错
	 * @param userDAO
	 */
	@Resource(name="userDAO4")
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	//加上构造方法
	public UserService(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}
	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 和在xml中写init-method是一个意思   生命周期
	 */
	@PostConstruct
   public void init(){
	   System.out.println("service init");
	   
   }
	@PreDestroy
   public void destroy(){
	   System.out.println("service destroy");
	   
   }
}

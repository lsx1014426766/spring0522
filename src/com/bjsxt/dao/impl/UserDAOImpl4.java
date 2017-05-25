package com.bjsxt.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;
@Component("userDAO4")
public class UserDAOImpl4 implements UserDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(User user) {
		
		//Hibernate
		//JDBC
		//XML
		//NetWork
		System.out.println("session factory class:" + sessionFactory.getClass());
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			s.save(user);
			s.getTransaction().commit();
		System.out.println("user saved!");
		//throw new RuntimeException("exeption!");
	}

	@Override
	public void delete() {
		System.out.println("user delete............");
		
	}

}

package com.bjsxt.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;
@Component("userDAO5")
public class UserDAOImpl5 implements UserDAO {
private HibernateTemplate hibernateTemplate;

	

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}



	public void save(User user) {
		
			hibernateTemplate.save(user);
			
		//throw new RuntimeException("exeption!");
	}


	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}


}

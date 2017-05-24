package com.bjsxt.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.bjsxt.dao.LogDAO;
import com.bjsxt.model.Log;

@Component("logDAO") 
public class LogDAOImpl2 extends SuperDAO implements LogDAO {

	
	public void save(Log log) {
		
		//this.getHibernateTemplate().save(log);
		this.getHibernateTemplate().save(log);
		//throw new RuntimeException("error!");
	}

}

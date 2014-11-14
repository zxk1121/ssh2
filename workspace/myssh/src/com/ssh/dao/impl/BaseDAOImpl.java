package com.ssh.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.dao.BaseDao;

@Repository("baseDao")
@SuppressWarnings("all")
public class BaseDAOImpl<T> implements BaseDao<T>{
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		return this.getCurrentSession().save(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Class<T> c, Serializable user) {
		// TODO Auto-generated method stub
		return (T)this.getCurrentSession().get(c, user);
	}

}

package com.ssh.dao;

import java.io.Serializable;

public interface BaseDao<T> {
	
	public Serializable save(T o);
	
	public T find(Class<T> c,Serializable user);
	
}

package com.ssh.service.impl;

import org.springframework.stereotype.Service;

import com.ssh.dao.BaseDao;
import com.ssh.entity.User;
import com.ssh.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{

	private BaseDao<User> baseDao;

	@Override
	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		return baseDao.find(User.class, username);
	}
}

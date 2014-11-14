package com.ssh.service;

import com.ssh.entity.User;

public interface UserService {
	
	public User findUserByName(String username);
	
}

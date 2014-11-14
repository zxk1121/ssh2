package com.ssh.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.entity.User;
import com.ssh.service.UserService;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String username;
	private String password;
	
	public String login(){
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=userService.findUserByName(username);
		if(user!=null){
			if (user.getPassword()==password) {
				request.setAttribute("username", username);
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package com.putdata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putdata.User;
import com.putdata.util.dao.UserStunkDao;
import com.putdata.util.dao.impl.UserStunkDaoImpl;

public class UpdatePhone {
	public static void updatePhone(){
//		System.out.println("updatephone");
		UserStunkDao userStunkDao=new UserStunkDaoImpl();
//		List<User> list=userStunkDao.queryPhoneTest();
//		List<String> phone=new ArrayList<String>();
//		List<User> newlist=new ArrayList<User>();
//		for(int i=0;i<list.size();i++){
//			phone.add(list.get(i).getPhone());
//		}
//		for(int i=0;i<100;i++){
//			Collections.shuffle(phone);
//		}
//		for(int i=0;i<list.size();i++){
//			User user=list.get(i);
//			user.setPhone(phone.get(i));
//			newlist.add(user);
//		}
//		userStunkDao.updatePhoneTest(newlist);
		
		boolean success=userStunkDao.updatePhoneSetNull();
		if(success){
			userStunkDao.updatePhoneByPhoneTest();
		}
	}
	
	public static void main(String[] args) {
		UpdatePhone.updatePhone();
	}
}

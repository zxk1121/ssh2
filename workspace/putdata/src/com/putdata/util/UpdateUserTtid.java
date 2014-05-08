package com.putdata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putdata.dto.UserStunk;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class UpdateUserTtid {	
	public static void updateUserTtid(int count,String ttid){
		DaoFactory dao=new DaoFactoryImpl();
		List<UserStunk> list=dao.queryUserAll();
		List<UserStunk> newList=new ArrayList<UserStunk>();
		Collections.shuffle(list);
		for(int i=0;i<count;i++){
			UserStunk user=list.get(i);
			user.setTtid(ttid);
			newList.add(user);
		}
		dao.updatettid(newList);
	}
	
	public static void main(String[] args) {
		UpdateUserTtid.updateUserTtid(52, "qq");
	}
}

package com.putdata.util;

import java.util.ArrayList;
import java.util.List;

import com.putdata.Statisitcs;
import com.putdata.User;
import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class InsertPhone {
	private DaoFactory daofactory;

	public InsertPhone() {
		daofactory = new DaoFactoryImpl();
	}

	public void insertPhone() {
		List<User> userlist = daofactory.queryPhoneTest();
		List<UserStunk> list=daofactory.queryUserStunk();
		List<User> newuserlist=new ArrayList<User>();
		for(int i=0;i<userlist.size();i++){
			boolean success=true;
			for(int j=0;j<list.size();j++){
				if(userlist.get(i).getUserId()==list.get(j).getUserId()){
					success=false;
				}
			}
			if(success){
				newuserlist.add(userlist.get(i));
			}
		}
		System.out.println(newuserlist.size());
		// 插入UserStunk表
//		daofactory.insertUserStunk(userlist);
//		updateCertif(userlist);
	}

	public void updateCertif(List<User> userlist) {
		List<Certificate> certlist = daofactory.queryCarInfo();
		List<Statisitcs> statlist = daofactory.queryStatisitcs();
		List<Certificate> newcertlist = new ArrayList<Certificate>();
		int k = 0, a = 0;
		for (int i = 0; i < statlist.size()-4; i++) {
			for (int j = 0; j < Integer.valueOf(statlist.get(i)
					.getCertificatesuccess()); j++) {
				if (userlist.get(a).getCreated() < statlist.get(i).getCreated()) {
					
					Certificate certif = certlist.get(k);
					certif.setUserId(userlist.get(a).getUserId());
					daofactory.updateStatus(userlist.get(a).getUserId());
					newcertlist.add(certif);
					k++;
				}
				a++;
			}
		}
		daofactory.updateCarInfo(newcertlist);
	}

}

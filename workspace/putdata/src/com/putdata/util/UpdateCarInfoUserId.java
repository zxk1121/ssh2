package com.putdata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putdata.Statisitcs;
import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.UserStunkDao;
import com.putdata.util.dao.impl.DaoFactoryImpl;
import com.putdata.util.dao.impl.UserStunkDaoImpl;

public class UpdateCarInfoUserId {
	public static void updateCarInfoUserId() {
		UserStunkDao userStunkDao = new UserStunkDaoImpl();
		DaoFactory daoFactory = new DaoFactoryImpl();
		List<Statisitcs> statlist = daoFactory.queryStatisitcs();
		List<Certificate> carlist = userStunkDao.queryCarInfo();
		List<Certificate> newcarlist=new ArrayList<Certificate>();
		List<UserStunk> statuslist=new ArrayList<UserStunk>();
		long starttime = 0;
		int k = 0;
		for (int i = statlist.size()-3; i < statlist.size()-2; i++) {
			Statisitcs stat = statlist.get(i);
			if (i != 0) {
				starttime = statlist.get(i - 1).getCreated();
			}
			System.out.println(stat.getCertificatesuccess());
			System.out.println("starttime:"+starttime);
			System.out.println("endtime:"+stat.getCreated());
			int count=daoFactory.queryCertCount(DateToStringUtil.millDateFormatYMD((stat.getCreated()-86400)*1000));
			List<UserStunk> userlist = userStunkDao.queryUserStunk(starttime,
					stat.getCreated(),
					Integer.valueOf(stat.getCertificatesuccess())-13);
			for(int a=0;a<10;a++){
	    		Collections.shuffle(userlist);
			}
			System.out.println(userlist.size());
			List<UserStunk> ulist=new ArrayList<UserStunk>();
			for(int j=0;j<Integer.valueOf(stat.getCertificatesuccess())-13;j++){
				ulist.add(userlist.get(j));
			}
			System.out.println("u:"+ulist.size());
			for (int j = 0; j < ulist.size(); j++) {
				Certificate cert=carlist.get(k++);
				cert.setUserId(ulist.get(j).getUserId());
				cert.setCreated(ulist.get(j).getCreated());
				newcarlist.add(cert);
				statuslist.add(ulist.get(j));
			}
		}
		
		boolean success=userStunkDao.updateCarInfo(newcarlist);
		if(success){
			userStunkDao.updateUserStatus(statuslist);
		}
	}
	public static void main(String[] args) {
		UpdateCarInfoUserId.updateCarInfoUserId();
	}
}

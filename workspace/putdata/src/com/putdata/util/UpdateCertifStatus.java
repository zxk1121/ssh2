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

public class UpdateCertifStatus {
	public static void updateCertifStatus() {
		DaoFactory daoFactory = new DaoFactoryImpl();
		UserStunkDao userStunkDao = new UserStunkDaoImpl();
		List<Statisitcs> statlist = daoFactory.queryStatisitcs();
		List<UserStunk> userlist = new ArrayList<UserStunk>();
		List<UserStunk> newlist = new ArrayList<UserStunk>();
		long starttime = 0;
		for (int i = statlist.size()-33; i < statlist.size()-32; i++) {
			Statisitcs stat = statlist.get(i);
			if (i != 0) {
				starttime = statlist.get(i - 1).getCreated();
			}
			userlist = userStunkDao.findUserStunk(
					starttime,
					stat.getCreated());
			for (int k = 0; k < 10; k++) {
				Collections.shuffle(userlist);
			}
			int count = Integer.valueOf(stat.getUploadcertificate())
					- Integer.valueOf(stat.getCertificatesuccess())-5;
			System.out.println("count:"+count);
			for (int j = 0; j < count; j++) {
				newlist.add(userlist.get(j));
			}
			System.out.println(newlist.size());
			userStunkDao.updateStatusError(newlist);
		}
	}

	public static void main(String[] args) {
		UpdateCertifStatus.updateCertifStatus();
	}
}

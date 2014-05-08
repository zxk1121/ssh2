package com.putdata.util;

import java.util.List;

import com.putdata.Statisitcs;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.UserStunkDao;
import com.putdata.util.dao.impl.DaoFactoryImpl;
import com.putdata.util.dao.impl.UserStunkDaoImpl;

public class UpdateSystem {
	public static void updateSystem() {
		DaoFactory daoFactory = new DaoFactoryImpl();
		UserStunkDao userStunkDao = new UserStunkDaoImpl();
		List<Statisitcs> statis = daoFactory.queryStatisitcs();
		for (int i = statis.size()-1; i < statis.size(); i++) {
			Statisitcs stati = statis.get(i);
			String date = DateToStringUtil.millDateFormatYMD((stati
					.getCreated() - 86400) * 1000);
			userStunkDao.updateSystem(
					date,
					Integer.valueOf(stati.getValidateUser())-Integer.valueOf(stati.getUploadcertificate()));
		}
	}

	public static void main(String[] args) {
		updateSystem();
	}
}

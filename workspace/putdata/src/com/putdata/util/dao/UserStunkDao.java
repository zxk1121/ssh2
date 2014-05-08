package com.putdata.util.dao;

import java.util.List;

import com.putdata.User;
import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;

public interface UserStunkDao {
	public List<UserStunk> queryUserStunk(long starttime,long endtime,int limit);
	
	public List<UserStunk> findUserStunk(long starttime,long endtime);
	
	public List<Certificate> queryCarInfo();

	public boolean updateCarInfo(List<Certificate> list);

	public boolean updateUserStatus(List<UserStunk> list);
	
	public List<User> queryPhoneTest();
	
	public boolean updatePhoneTest(List<User> list);
	
	public boolean updateStatusError(List<UserStunk> list);
	
	public boolean updateSystem(String date,int count);
	
	public boolean updatePhoneSetNull();
	
	public boolean updatePhoneByPhoneTest();
	
	public void insertIntoUserStunkByPhoneTestTwo(int count);
	
	public void insertIntophoneTestByPhoneTestTwo(int count);
}

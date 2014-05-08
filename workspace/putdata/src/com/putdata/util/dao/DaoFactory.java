package com.putdata.util.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.putdata.CarInfo;
import com.putdata.Statisitcs;
import com.putdata.User;
import com.putdata.dto.Certificate;
import com.putdata.dto.RejectReason;
import com.putdata.dto.UserStunk;



public interface DaoFactory {
	
	public Connection getConnection()throws Exception;
	
	public void insertPhone(List<User> users) ;
	
	public void insertStatisitcs(List<Statisitcs> list);
	
	public List<Statisitcs> queryStatisitcs();
	
	public void insertCarInfo(List<CarInfo> list);
	
	public List<UserStunk> queryUserStunk();
	
	public void insertNewUserStunk(List<UserStunk> list);
	
	public List<Certificate> queryCertificate();
	
	public void insertNewCertificate(List<Certificate> list);
	
	public List<RejectReason> queryrejectReason();
	
	public void insertRejectReason(List<RejectReason> list);

	public void deleNewCertificate();
	public void deleNewUserStunk();
	public void deleReject();
	
	public void insertUserStunk(List<User> list);
	
	public List<User> queryPhoneTest();
	
	public List<Certificate> queryCarInfo();
	
	public void updateCarInfo(List<Certificate> list);
	
	public void updateStatus(long userid);
	
	public List<UserStunk> queryUserAll();
	
	public int queryUserByPhone(String phone);
	
	public int queryCertCount(String date);
	
	public void updatettid(List<UserStunk> list);
	
	public void insertFeaturedArticle(List<Map<String,String>> list);
	
	public void insertHistoryArticle(List<Map<String,String>> list);
}

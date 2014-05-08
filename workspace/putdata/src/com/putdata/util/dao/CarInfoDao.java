package com.putdata.util.dao;

import java.util.List;

import com.putdata.dto.Certificate;

public interface CarInfoDao {
	public List<Certificate> queryCarInfo();
	
	public boolean updateCarInfo(List<Certificate> list);
	
	public boolean updateAdd(List<Certificate> list);
	
	public List<Certificate> queryCertif(String code,int limit);
}

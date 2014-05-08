package com.putdata.util.dao;

import java.util.List;

import com.putdata.dto.Certificate;

public interface updateAddressDao {
	
	//修改深圳地址
	public List<Certificate> querySZAddress(String sql);
		
	public int updateSZAddress(List<Certificate> list);
}

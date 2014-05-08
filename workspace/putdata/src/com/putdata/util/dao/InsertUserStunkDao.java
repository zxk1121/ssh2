package com.putdata.util.dao;

import java.util.List;

import com.putdata.CarInfo;
import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;

public interface InsertUserStunkDao {
	public void insertUserStunk(List<UserStunk> list);

	public void insertCertificate(List<Certificate> list);
}

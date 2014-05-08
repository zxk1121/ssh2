package com.putdata.util.dao;

import java.util.List;

import com.putdata.Statisitcs;
import com.putdata.dto.UserStunk;

public interface QueryThreeDayDataDao {
	//查找App每日统计数据
	public List<Statisitcs> queryStatisitcs();
	
	//查找最近3天的用户
	public List<UserStunk> queryUserStunkThreeDay();
	
	//修改用户created
	public void updateCreatedByUserId(List<UserStunk> list);
	
	//增加用户
	public void insertUserStunk(List<String> list);
	
	//查找各种状态的用户
	public List<UserStunk> queryUserStunkByStatus(String status);
}

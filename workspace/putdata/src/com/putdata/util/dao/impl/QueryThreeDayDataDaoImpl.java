package com.putdata.util.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.putdata.Statisitcs;
import com.putdata.dto.UserStunk;
import com.putdata.util.GetConnection;
import com.putdata.util.dao.QueryThreeDayDataDao;

public class QueryThreeDayDataDaoImpl implements QueryThreeDayDataDao {

	// 查找App每日统计表
	@Override
	public List<Statisitcs> queryStatisitcs() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Statisitcs> list = new ArrayList<Statisitcs>();
		try {
			String sql = "select * from dongdao.AppStatisticsByDay";
			conn = GetConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Statisitcs statisitcs = new Statisitcs();
				statisitcs.setId(rs.getInt(1));
				statisitcs.setCreated(rs.getLong(2));
				statisitcs.setNewUser(rs.getString(3));
				statisitcs.setActiveUser(rs.getString(4));
				statisitcs.setValidateUser(rs.getString(5));
				statisitcs.setUploadcertificate(rs.getString(6));
				statisitcs.setCertificatesuccess(rs.getString(7));
				list.add(statisitcs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	// 查找最近三天的用户
	@Override
	public List<UserStunk> queryUserStunkThreeDay() {
		// TODO Auto-generated method stub
		List<UserStunk> list = new ArrayList<UserStunk>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select userId,created from dongdao.newUserStunk where DATEDIFF(NOW(),DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d'))<= 3";
		try {
			conn = GetConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserStunk userStunk = new UserStunk();
				userStunk.setUserId(rs.getLong(1));
				userStunk.setCreated(rs.getLong(2));
				list.add(userStunk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 修改用户created
	@Override
	public void updateCreatedByUserId(List<UserStunk> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update dongdao.newUserStunk set created=? where userId=?";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setLong(1, list.get(i).getCreated());
				pstmt.setLong(2, list.get(i).getUserId());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 增加用户
	@Override
	public void insertUserStunk(List<String> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.newUserStunk "
				+ "select * from dongdao.UserStunk where DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d')=?";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				stmt.setString(1, list.get(i));
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 查找各种状态的用户
	@Override
	public List<UserStunk> queryUserStunkByStatus(String status) {
		List<UserStunk> list = new ArrayList<UserStunk>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select userId,created from dongdao.newUserStunk where DATEDIFF(NOW(),DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d'))<= 3 and status="
				+ status;
		try {
			conn = GetConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserStunk user = new UserStunk();
				user.setUserId(rs.getLong(1));
				user.setCreated(rs.getLong(2));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}

package com.putdata.util.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.putdata.dto.Certificate;
import com.putdata.util.GetConnection;
import com.putdata.util.dao.CarInfoDao;

public class CarinfoDaoImpl implements CarInfoDao {

	@Override
	public List<Certificate> queryCarInfo() {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.CarInfo where address is null";
		List<Certificate> list = new ArrayList<Certificate>();
		try {
			connection = GetConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Certificate certif = new Certificate();
				certif.setCid(rs.getLong(1));
				certif.setAddress(rs.getString(7));
				certif.setCar_board_code(rs.getString(3));
				list.add(certif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
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

	@Override
	public boolean updateCarInfo(List<Certificate> list) {
		// TODO Auto-generated method stub]
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "update dongdao.CarInfo set address=? where cid=?";
		try {
			connection = GetConnection.getConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setString(1, list.get(i).getAddress());
				pstmt.setLong(2, list.get(i).getCid());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateAdd(List<Certificate> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update dongdao.certificate set address=?,car_board_code=? where cid=?";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Certificate certif = list.get(i);
				stmt.setString(1, certif.getAddress());
				stmt.setString(2, certif.getCar_board_code());
				stmt.setLong(3, certif.getCid());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		return true;
	}

	@Override
	public List<Certificate> queryCertif(String code, int limit) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Certificate> list = new ArrayList<Certificate>();
		String sql = "select cid,car_board_code,address from dongdao.certificate where cid >= "
				+ "(SELECT floor(RAND() * (SELECT MAX(cid) FROM dongdao.certificate))) and cid in(select cid from dongdao.CarInfo)"
				+ "and car_board_code like '%"+code+"%' ORDER BY cid LIMIT "+limit;
		try {
			conn = GetConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Certificate certif = new Certificate();
				certif.setCid(rs.getLong(1));
				certif.setCar_board_code(rs.getString(2));
				certif.setAddress(rs.getString(3));
				list.add(certif);
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

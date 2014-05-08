package com.putdata.util.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.putdata.CarInfo;
import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;
import com.putdata.util.GetConnection;
import com.putdata.util.dao.InsertUserStunkDao;

public class InsertUserStunkDaoImpl implements InsertUserStunkDao{

	@Override
	public void insertUserStunk(List<UserStunk> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prestmt = null;
		long start = System.currentTimeMillis();
		String sql = "insert into dongdao.userstunkbak(userId,phone,created,longitude,"
				+ "latitude,userStats,deviceCode,system,certificate_pic,"
				+ "certificate_status,registTime,appVersion,locationUpdateTime,"
				+ "device,newtime,ttid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			prestmt = conn
					.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			for (int i = 0; i < list.size(); i++) {
				UserStunk userstunk = list.get(i);
				prestmt.setLong(1, userstunk.getUserId());
				prestmt.setString(2, userstunk.getPhone());
				prestmt.setLong(3, userstunk.getCreated());
				prestmt.setString(4, userstunk.getLongitude());
				prestmt.setString(5, userstunk.getLatitude());
				prestmt.setLong(6, userstunk.getUserStats());
				prestmt.setString(7, userstunk.getDeviceCode());
				prestmt.setLong(8, userstunk.getSystem());
				prestmt.setString(9, userstunk.getCertificate_pic());
				prestmt.setString(10, userstunk.getCertificate_status());
				prestmt.setLong(11, userstunk.getRegistTime());
				prestmt.setString(12, userstunk.getAppVersion());
				prestmt.setLong(13, userstunk.getLocationUpdateTime());
				prestmt.setString(14, userstunk.getDevice());
				prestmt.setLong(15, userstunk.getNewtime());
				prestmt.setString(16, userstunk.getTtid());
				prestmt.addBatch();
			}

			System.out.println("before batch:"
					+ (System.currentTimeMillis() - start) / 1000);
			prestmt.executeBatch();
			System.out.println("affter batch:"
					+ (System.currentTimeMillis() - start) / 1000);
			conn.commit();
			long end = System.currentTimeMillis();
			System.out.println("jdbc:" + (end - start) / 1000);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prestmt != null) {
					prestmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertCertificate(List<Certificate> list) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.certificatebak(cid,userId,car_board_code,car_board_type,name,"
				+ "resgitTime,paperWorkTime,carType,car_id,engine_code,address,created,userProperty,createtime) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			long start = System.currentTimeMillis();
			for (int i = 0; i < list.size(); i++) {
				Certificate carInfo = list.get(i);
				stmt.setLong(1, carInfo.getCid());
				stmt.setLong(2, carInfo.getUserId());
				stmt.setString(3, carInfo.getCar_board_code());
				stmt.setString(4, carInfo.getCar_board_type());
				stmt.setString(5, carInfo.getName());
				stmt.setString(6, carInfo.getResgitTime());
				stmt.setString(7, carInfo.getPaperWorkTime());
				stmt.setString(8, carInfo.getCarType());
				stmt.setString(9, carInfo.getCar_id());
				stmt.setString(10, carInfo.getEngine_code());
				stmt.setString(11,carInfo.getAddress());
				stmt.setLong(12,carInfo.getCreated());
				stmt.setString(13, carInfo.getUserProperty());
				stmt.setString(14, carInfo.getCreatedTime());
				stmt.addBatch();
			}
			System.out.println("before batch:"
					+ (System.currentTimeMillis() - start) / 1000);
			stmt.executeBatch();
			System.out.println("affter batch:"
					+ (System.currentTimeMillis() - start) / 1000);
			conn.commit();
			long end = System.currentTimeMillis();
			System.out.println("jdbc:" + (end - start) / 1000);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}

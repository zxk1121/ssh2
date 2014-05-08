package com.putdata.util.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.putdata.User;
import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;
import com.putdata.util.GetConnection;
import com.putdata.util.dao.UserStunkDao;

public class UserStunkDaoImpl implements UserStunkDao {

	@Override
	public List<UserStunk> queryUserStunk(long starttime, long endtime,
			int limit) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT  userId ,created from dongdao.phoneTest  where  "
				+ "userId>=(SELECT floor(RAND() * (SELECT MAX(userId) FROM dongdao.phoneTest))) and created>="
				+ starttime
				+ " and created<="
				+ endtime+" and userId in (select userId from dongdao.UserStunk where certificate_status=0) limit "+limit;
		List<UserStunk> list = new ArrayList<UserStunk>();
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

	public List<Certificate> queryCarInfo() {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.CarInfo where userId is null";
		List<Certificate> list = new ArrayList<Certificate>();
		try {
			conn = GetConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Certificate certificate = new Certificate();
				certificate.setCid(rs.getLong(1));
				certificate.setUserId(rs.getLong(2));
				certificate.setCar_board_code(rs.getString(3));
				certificate.setCar_board_type(rs.getString(4));
				certificate.setEngine_code(rs.getString(5));
				certificate.setName(rs.getString(6));
				certificate.setAddress(rs.getString(7));
				certificate.setCreated(rs.getLong(8));
				certificate.setCar_id(rs.getString(9));
				certificate.setUserProperty(rs.getString(10));
				certificate.setCarType(rs.getString(11));
				certificate.setCreatedTime(rs.getString(12));
				certificate.setResgitTime(rs.getString(13));
				certificate.setPaperWorkTime(rs.getString(14));
				list.add(certificate);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean updateCarInfo(List<Certificate> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update dongdao.CarInfo set userId=?,created=? where cid=?";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setLong(1, list.get(i).getUserId());
				pstmt.setLong(2, list.get(i).getCreated());
				pstmt.setLong(3, list.get(i).getCid());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		return true;
	}

	@Override
	public boolean updateUserStatus(List<UserStunk> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update dongdao.UserStunk set certificate_status=1 where userId=?";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setLong(1, list.get(i).getUserId());
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
		return false;
	}

	@Override
	public List<User> queryPhoneTest() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select userId,Created,phone from dongdao.phoneTest";
		List<User> list = new ArrayList<User>();
		try {
			conn = GetConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong(1));
				user.setCreated(rs.getLong(2));
				user.setPhone(rs.getString(3));
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

	@Override
	public boolean updatePhoneTest(List<User> list) {
		// TODO Auto-generated method stub
		System.out.println("updatePhoneTest");
		long s=System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update dongdao.phoneTest set phone=? where userId=?";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				stmt.setString(1, list.get(i).getPhone());
				stmt.setLong(2, list.get(i).getUserId());
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
		long e=System.currentTimeMillis();
		System.out.println("time:"+(e-s)/1000);
		return true;
	}

	@Override
	public boolean updateStatusError(List<UserStunk> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update dongdao.UserStunk set certificate_status=2 where userId=? and certificate_status=0";
		try {
			conn = GetConnection.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				stmt.setLong(1, list.get(i).getUserId());
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
		return true;
	}

	@Override
	public List<UserStunk> findUserStunk(long starttime, long endtime) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT  userId ,created from dongdao.phoneTest  where userId in (select userId from dongdao.UserStunk where certificate_status=0) and created>="
				+ starttime
				+ " and created<="
				+ endtime;
		List<UserStunk> list = new ArrayList<UserStunk>();
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

	@Override
	public boolean updateSystem(String date ,int count) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement stmt=null;
		String sql="UPDATE dongdao.UserStunk SET system = 1 "
				+ " where userId IN (SELECT*FROM(SELECT userId FROM dongdao.UserStunk "
				+ " WHERE DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d') =?"
				+ " AND system=0 and certificate_status=0"
				+ " AND userId >= (SELECT floor(RAND() * ( SELECT MAX(userId) FROM dongdao.UserStunk))) LIMIT ?) t)";
		try{
			conn=GetConnection.getConnection();
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, date);
			stmt.setInt(2, count);
			stmt.executeUpdate();
		}catch (Exception e) {
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
		return true;
	}

	@Override
	public boolean updatePhoneSetNull() {
		// TODO Auto-generated method stub
		System.out.println("updatePhoneSetNull");
		Connection conn=null;
		Statement stmt=null;
		String sql="update dongdao.UserStunk set phone=null where phone in (select phone from dongdao.phoneTest)";
		try{
			conn=GetConnection.getConnection();
			stmt=conn.createStatement();
			int success=stmt.executeUpdate(sql);
			if(success>0){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try{
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updatePhoneByPhoneTest() {
		// TODO Auto-generated method stub
		System.out.println("updatePhoneByPhoneTest");
		Connection conn=null;
		Statement stmt=null;
		String sql="update dongdao.UserStunk u,dongdao.phoneTest p set u.phone=p.phone where p.userId=u.userId";
		try{
			conn=GetConnection.getConnection();
			stmt=conn.createStatement();
			int success=stmt.executeUpdate(sql);
			if(success>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void insertIntoUserStunkByPhoneTestTwo(int count) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		System.out.println("updatePhoneByPhoneTest");
		Connection conn=null;
		Statement stmt=null;
		String sql="insert into dongdao.UserStunk(userId,phone,created) select userId,phone,created from dongdao.phoneTesttwo limit "+count;
		try{
			conn=GetConnection.getConnection();
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertIntophoneTestByPhoneTestTwo(int count) {
		// TODO Auto-generated method stub
		System.out.println("updatePhoneByPhoneTest");
		Connection conn=null;
		Statement stmt=null;
		String sql="insert into dongdao.phoneTest(userId,phone,created) select userId,phone,created from dongdao.phoneTesttwo limit "+count;
		try{
			conn=GetConnection.getConnection();
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}

}

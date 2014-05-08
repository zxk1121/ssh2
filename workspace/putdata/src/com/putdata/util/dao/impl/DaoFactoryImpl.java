package com.putdata.util.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.putdata.CarInfo;
import com.putdata.Statisitcs;
import com.putdata.User;
import com.putdata.dto.Certificate;
import com.putdata.dto.RejectReason;
import com.putdata.dto.UserStunk;
import com.putdata.util.dao.DaoFactory;

public class DaoFactoryImpl implements DaoFactory {

	public Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		InputStream in = DaoFactoryImpl.class.getClassLoader()
				.getResourceAsStream("datasource.properties");
		props.load(in);
		String driverClass = props.getProperty("driverClass");
		String url = props.getProperty("jdbcUrl");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		Connection conn = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void insertPhone(List<User> users) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.phoneTesttwo(userId,phone,created) values(?,?,?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			long start = System.currentTimeMillis();
			for (int i = 0; i < users.size(); i++) {
				stmt.setLong(1, users.get(i).getUserId());
				stmt.setString(2, users.get(i).getPhone());
				stmt.setLong(3, users.get(i).getCreated());
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

	public void insertStatisitcs(List<Statisitcs> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		//AppStatisticsByDay,IOSStatistics,AndroidStatistics
		String sql = "insert into dongdao.AppStatisticsByDay(created,newUser,activeUser,validateUser,uploadcertificate,certificatesuccess) values(?,?,?,?,?,?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			long start = System.currentTimeMillis();
			for (int i = 0; i < list.size(); i++) {
				stmt.setLong(1, list.get(i).getCreated());
				stmt.setString(2, list.get(i).getNewUser());
				stmt.setString(3, list.get(i).getActiveUser());
				stmt.setString(4, list.get(i).getValidateUser());
				stmt.setString(5, list.get(i).getUploadcertificate());
				stmt.setString(6, list.get(i).getCertificatesuccess());
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

	@Override
	public List<Statisitcs> queryStatisitcs() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Statisitcs> list = new ArrayList<Statisitcs>();
		try {
			String sql = "select * from dongdao.AppStatisticsByDay";
			conn = getConnection();
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

	@Override
	public void insertCarInfo(List<CarInfo> list) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.CarInfo(car_board_code,name,"
				+ "resgitTime,paperWorkTime,carType,car_id,engine_code,address) "
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			long start = System.currentTimeMillis();
			for (int i = 0; i < list.size(); i++) {
				CarInfo carInfo = list.get(i);
				stmt.setString(1, carInfo.getCar_board_code());
				stmt.setString(2, carInfo.getName());
				stmt.setString(3, carInfo.getFirstTime());
				stmt.setString(4, carInfo.getExpireTime());
				stmt.setString(5, carInfo.getCar_board() + carInfo.getCarType());
				stmt.setString(6, carInfo.getCar_id());
				stmt.setString(7, carInfo.getEngine_code());
				stmt.setString(8, carInfo.getAddress());
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

	@Override
	public List<UserStunk> queryUserStunk() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UserStunk> list = new ArrayList<UserStunk>();
		String sql = "select * from dongdao.UserStunk where certificate_pic is not null";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserStunk userstunk = new UserStunk();
				userstunk.setUserId(rs.getLong(1));
				userstunk.setPhone(rs.getString(2));
				userstunk.setCreated(rs.getLong(3));
				userstunk.setLatitude(rs.getString(4));
				userstunk.setLongitude(rs.getString(5));
				userstunk.setUserStats(rs.getLong(6));
				userstunk.setDeviceCode(rs.getString(7));
				userstunk.setSystem(rs.getLong(8));
				userstunk.setCertificate_pic(rs.getString(9));
				userstunk.setCertificate_status(rs.getString(10));
				userstunk.setRegistTime(rs.getLong(11));
				userstunk.setAppVersion(rs.getString(12));
				userstunk.setLocationUpdateTime(rs.getLong(13));
				userstunk.setDevice(rs.getString(14));
				userstunk.setNewtime(rs.getLong(15));
				userstunk.setTtid(rs.getString(16));
				list.add(userstunk);
			}
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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void insertNewUserStunk(List<UserStunk> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prestmt = null;
		long start = System.currentTimeMillis();
		String sql = "insert into dongdao.newUserbak(userId,phone,created,longitude,"
				+ "latitude,userStats,deviceCode,system,certificate_pic,"
				+ "certificate_status,registTime,appVersion,locationUpdateTime,"
				+ "device,newtime,ttid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
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

	public List<Certificate> queryCertificate() {
		// TODO Auto-generated method stub
		List<Certificate> list = new ArrayList<Certificate>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.certificate";
		try {
			conn = getConnection();
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
			return list;
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void insertNewCertificate(List<Certificate> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(list.size());
		String sql = "insert into dongdao.CarInfo(car_board_code,car_board_type,engine_code,name,"
				+ "car_id,userProperty,carType,"
				+ "resgitTime,paperWorkTime,address) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Certificate certificate = list.get(i);
				stmt.setString(1, certificate.getCar_board_code());
				stmt.setString(2, certificate.getCar_board_type());
				stmt.setString(3, certificate.getEngine_code());
				stmt.setString(4, certificate.getName());
				stmt.setString(5, certificate.getCar_id());
				stmt.setString(6, certificate.getUserProperty());
				stmt.setString(7, certificate.getCarType());
				stmt.setString(8, certificate.getResgitTime());
				stmt.setString(9, certificate.getPaperWorkTime());
				stmt.setString(10, certificate.getAddress());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
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

	public List<RejectReason> queryrejectReason() {
		// TODO Auto-generated method stub
		List<RejectReason> list = new ArrayList<RejectReason>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.RejectReason";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RejectReason reason = new RejectReason();
				reason.setId(rs.getLong(1));
				reason.setUserId(rs.getLong(2));
				reason.setReason(rs.getString(3));
				reason.setCreated(rs.getLong(4));
				list.add(reason);
			}
			return list;
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void insertRejectReason(List<RejectReason> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.newrejectreason(id,userId,reason,created) values(?,?,?,?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				RejectReason reason = list.get(i);
				stmt.setLong(1, reason.getId());
				stmt.setLong(2, reason.getUserId());
				stmt.setString(3, reason.getReason());
				stmt.setLong(4, reason.getCreated());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
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

	@Override
	public void deleNewCertificate() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from dongdao.newcertificate";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
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

	@Override
	public void deleNewUserStunk() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from dongdao.newUserStunk";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
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

	@Override
	public void deleReject() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from dongdao.newrejectreason";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动不存在");
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
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

	@Override
	public void insertUserStunk(List<User> list) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql = "insert into dongdao.UserStunk(userId,phone,created) values(?,?,?)";
		long start = System.currentTimeMillis();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				pstmt.setLong(1, user.getUserId());
				pstmt.setString(2, user.getPhone());
				pstmt.setLong(3, user.getCreated());
				pstmt.addBatch();
			}
			System.out.println("before batch:"
					+ (System.currentTimeMillis() - start) / 1000);
			pstmt.executeBatch();
			System.out.println("affter batch:"
					+ (System.currentTimeMillis() - start) / 1000);
			conn.commit();

		} catch (SQLException e) {
			System.out.println("数据库异常");
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<User> queryPhoneTest() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.phoneTest";
		List<User> list = new ArrayList<User>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong(1));
				user.setPhone(rs.getString(2));
				user.setCreated(rs.getLong(3));
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
	public List<Certificate> queryCarInfo() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.CarInfo";
		List<Certificate> list = new ArrayList<Certificate>();
		try {
			conn = getConnection();
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
	public void updateCarInfo(List<Certificate> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update dongdao.carInfo set userId=? where cid=?";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Certificate certif = list.get(i);
				pstmt.setLong(1, certif.getUserId());
				pstmt.setLong(2, certif.getCid());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateStatus(long userid) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		String sql = "update dongdao.UserStunk set certificate_status=1 where userid="
				+ userid;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<UserStunk> queryUserAll() {

		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UserStunk> list=new ArrayList<UserStunk>();
		String sql = "select userId,ttid from dongdao.UserStunk where ttid is null and (userId in (select userId from dongdao.phoneTest)  or userId in (select userId from dongdao.certificatebak where created is not null) )and DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d')>='2014-02-01' and DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d')<'2014-03-01'";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserStunk user=new UserStunk();
				user.setUserId(rs.getLong(1));
				user.setTtid(rs.getString(2));
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
	public int queryUserByPhone(String phone) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dongdao.UserStunk where phone=" + phone;
		int i = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				i++;
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
		long end=System.currentTimeMillis();
		System.out.println("time:"+(end-start)/1000);
		return i;
	}

	@Override
	public int queryCertCount(String date) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		int i=0;
		String sql="select count(*) from dongdao.certificate where DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d')="+date;
		try{
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		return i;
	}

	@Override
	public void updatettid(List<UserStunk> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update dongdao.UserStunk set ttid=? where userid=?";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				stmt.setString(1, list.get(i).getTtid());
				stmt.setLong(2, list.get(i).getUserId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertFeaturedArticle(List<Map<String, String>> list) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.FeaturedArticle(title,url) values(?,?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
//				stmt.setString(1, list.get(i).get("articleId"));
				stmt.setString(1, list.get(i).get("title"));
				stmt.setString(2, list.get(i).get("url"));
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertHistoryArticle(List<Map<String, String>> list) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dongdao.historyArticle(articleId,title,url,imageurl,type) values(?,?,?,?,?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				stmt.setString(1, list.get(i).get("articleId"));
				stmt.setString(2, list.get(i).get("title"));
				stmt.setString(3, list.get(i).get("url"));
				stmt.setString(4, list.get(i).get("imageUrl"));
				stmt.setInt(5, 2);
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

}

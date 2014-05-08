package com.putdata.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.putdata.util.dao.impl.DaoFactoryImpl;

public class GetConnection {
	public static  Connection getConnection() throws Exception{
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
	
	public static  Connection getLocalConnection() throws Exception{
		// TODO Auto-generated method stub
		Properties props = new Properties();
		InputStream in = DaoFactoryImpl.class.getClassLoader()
				.getResourceAsStream("datasource.properties");
		props.load(in);
		String driverClass = props.getProperty("driverClass");
		String url = props.getProperty("localjdbcUrl");
		String user = props.getProperty("localuser");
		String password = props.getProperty("localpass");
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
}

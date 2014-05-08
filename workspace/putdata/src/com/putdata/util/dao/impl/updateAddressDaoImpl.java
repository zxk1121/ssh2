package com.putdata.util.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.putdata.dto.Certificate;
import com.putdata.util.GetConnection;
import com.putdata.util.dao.updateAddressDao;

public class updateAddressDaoImpl implements updateAddressDao{

	@Override
	public List<Certificate> querySZAddress(String sql) {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Certificate> list=new ArrayList<Certificate>();
		try{
			conn=GetConnection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Certificate certificate=new Certificate();
				certificate.setCid(rs.getLong(1));
				list.add(certificate);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
				if(rs!=null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int updateSZAddress(List<Certificate> list) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement stmt=null;
		String sql="update dongdao.CarInfo set address=? where cid=?";
		try{
			conn=GetConnection.getConnection();
			conn.setAutoCommit(false);
			stmt=conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				stmt.setString(1, list.get(i).getAddress());
				stmt.setLong(2, list.get(i).getCid());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
			
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
		return 0;
	}


}

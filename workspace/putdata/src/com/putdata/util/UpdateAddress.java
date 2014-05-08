package com.putdata.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putdata.dto.Certificate;
import com.putdata.util.dao.CarInfoDao;
import com.putdata.util.dao.updateAddressDao;
import com.putdata.util.dao.impl.CarinfoDaoImpl;
import com.putdata.util.dao.impl.updateAddressDaoImpl;

public class UpdateAddress {
	public static void updateAddress() throws IOException {
		CarInfoDao carInfoDao = new CarinfoDaoImpl();
		List<String> list = ReadAddExcel.listreadXlsx("/root/北京.xls");
		List<Certificate> certlist = carInfoDao.queryCertif("浙B", list.size());
		List<Certificate> putCertlist = new ArrayList<Certificate>();
		System.out.println(certlist.size());
		for (int i = 0; i < certlist.size(); i++) {
			Certificate certif = certlist.get(i);
			String code = "京A"
					+ certif.getCar_board_code().substring(2,
							certif.getCar_board_code().length());
			certif.setCar_board_code(code);
			certif.setAddress(list.get(i));
			putCertlist.add(certif);
		}
		System.out.println(putCertlist);
		carInfoDao.updateAdd(putCertlist);

	}

	// 修改地址
	public static void updateSZAddress(String sql,String filename) {
		try {
			List<Certificate> list=new ArrayList<Certificate>();
			updateAddressDao dao=new updateAddressDaoImpl();
			List<String> strList = ReadAddExcel
					.listreadXlsx(filename);
			System.out.println(strList.size());
			for(int i=0;i<10;i++){
				Collections.shuffle(strList);
			}
			List<Certificate> certList=dao.querySZAddress(sql);
			for(int i=0;i<certList.size();i++){
				Certificate cert=certList.get(i);
				cert.setAddress(strList.get(i));
				list.add(cert);
			}
			
			dao.updateSZAddress(list);
			for(int i=0;i<list.size();i++){
				System.out.println("cid:"+certList.get(i).getCid()+"   address:"+certList.get(i).getAddress());
			}
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
//		String sql="select cid from dongdao.certificate where car_board_code like '%粤B%' and address not like '%号%' and address not like '%深圳%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/深圳地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%渝A%' and  cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/重庆地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%川A%' and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/成都地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%皖%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/安徽地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%苏B%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/江苏地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%赣A%' and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/江西省南昌市地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%贵A%' and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/贵州地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%湘A%' and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/湖南地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%闽A%' and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/福建省地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%冀A%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/河北地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%吉A%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/吉林地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%辽A%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/辽宁地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%津A%'  and cid in (select cid from dongdao.CarInfo where userId is not null)";
//		String filename="/root/Desktop/xls文件/地址/天津地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%陕A%'  and cid in (select cid from dongdao.CarInfo where userId is not null) and address not like '%陕西%' and address not like '%西安%'";
//		String filename="/root/Desktop/xls文件/地址/陕西地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%鄂A%'  and cid in (select cid from dongdao.CarInfo where userId is not null) and address not like '%武汉%'";
//		String filename="/root/Desktop/xls文件/地址/湖北地址.xls";
		
//		String sql="select * from dongdao.certificate where car_board_code like '%晋B%'  and address like '%北京%'";
//		String filename="/root/Desktop/xls文件/地址/山西/大同市地址.xls";
		
		String sql="select * from dongdao.CarInfo where address is null";
		String filename="/root/Desktop/新地址.xls";
		UpdateAddress.updateSZAddress(sql,filename);
	}
}

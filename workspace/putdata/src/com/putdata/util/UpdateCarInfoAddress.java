package com.putdata.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.putdata.dto.Certificate;
import com.putdata.util.dao.CarInfoDao;
import com.putdata.util.dao.impl.CarinfoDaoImpl;

public class UpdateCarInfoAddress {
	public static void updateCarInfoAddress(){
		CarInfoDao carInfodao=new CarinfoDaoImpl();;
		try {
			List<Certificate> list=carInfodao.queryCarInfo();
			List<Certificate> newlist=new ArrayList<Certificate>();
			int k=10,a=15;
			for(int i=0;i<list.size();i++){
				Certificate certif=list.get(i);
				String car_bordstr=certif.getCar_board_code().substring(0,2);
				String address="";
				if(car_bordstr.equals("浙A")){
					address="浙江省杭州市下城区东新路"+k+"号";
				}else if(car_bordstr.equals("浙C")){
					address="浙江省温州市梧田街道南塘大道"+a+"号";
					a=a+2;
				}else if(car_bordstr.equals("浙D")){
					address="浙江省绍兴市诸暨市暨阳街道江东路"+k+"号";
					k=k+2;
				}
				certif.setAddress(address);
				newlist.add(certif);
			}
			carInfodao.updateCarInfo(newlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

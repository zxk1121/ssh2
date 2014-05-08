package com.putdata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putdata.dto.Certificate;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class GenerationCertif {
	public static List<Certificate> generationCertif() throws Exception {
		List<Certificate> list = new ArrayList<Certificate>();
		List<String> brandList = ReadAddExcel
				.listreadXlsx("/root/Desktop/xls文件/行驶证信息/车品牌.xls");
		List<String> addressList = ReadAddExcel
				.listreadXlsx("/root/Desktop/address.xls");
		List<String> nameList = ReadAddExcel
				.listreadXlsx("/root/Desktop/xls文件/行驶证信息/姓名.xls");
		Collections.shuffle(brandList);
		for(int i=0;i<100;i++){
			Collections.shuffle(addressList);
		}
		for (int i = 0; i < 1000; i++) {
			Certificate certif = new Certificate();
			String address=addressList.get(i);
			String code="";
			certif.setAddress(address);
			if(address.indexOf("南京")>0){
				code="苏A";
			}else if(address.indexOf("滁州")>0){
				code="皖M";
			}else if(address.indexOf("阜阳")>0){
				code="皖K";
			}else if(address.indexOf("六安")>0){
				code="皖N";
			}else if(address.indexOf("陇南")>0){
				code="甘K";
			}else if(address.indexOf("洛阳")>0){
				code="豫C";
			}else if(address.indexOf("南阳")>0){
				code="豫R";
			}else if(address.indexOf("平顶山")>0){
				code="豫D";
			}else if(address.indexOf("信阳")>0){
				code="豫S";
			}else if(address.indexOf("驻马店")>0){
				code="豫Q";
			}else if(address.indexOf("牡丹江")>0){
				code="黑C";
			}else if(address.indexOf("仙桃市")>0){
				code="鄂M";
			}else if(address.indexOf("黄冈市")>0){
				code="鄂J";
			}else if(address.indexOf("黄石")>0){
				code="鄂B";
			}else if(address.indexOf("荆州")>0){
				code="鄂M";
			}else if(address.indexOf("随州")>0){
				code="鄂S";
			}else if(address.indexOf("武汉")>0){
				code="鄂A";
			}else if(address.indexOf("孝感")>0){
				code="鄂K";
			}else if(address.indexOf("淮安")>0){
				code="苏H";
			}else if(address.indexOf("东营")>0){
				code="鲁E";
			}else if(address.indexOf("潍坊")>0){
				code="鲁G";
			}else if(address.indexOf("淄博")>0){
				code="鲁C";
			}else if(address.indexOf("阿坝藏族羌族自治州")>0){
				code="川U";
			}else if(address.indexOf("广元市")>0){
				code="川H";
			}else if(address.indexOf("绵阳市")>0){
				code="川B";
			}
			certif.setCar_board_code(code
					+ RandomNum.getCharAndNumr(5));
			certif.setCar_board_type(brandList.get((int) (Math.random() * 615 + 1)));
			certif.setCarType("小型轿车");
			certif.setUserProperty("非营运");
			String resgitTime = RandomNum.RandomDate();
			String paperWorkTime = DateToStringUtil.addMonth(resgitTime);
			certif.setResgitTime(resgitTime);
			certif.setPaperWorkTime(paperWorkTime);
			certif.setEngine_code(RandomNum.RandomEngineCode(10));
			certif.setCar_id("L" + RandomNum.getCharAndNumr(16));
			certif.setName(nameList.get(i));
			list.add(certif);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		List<Certificate> list = generationCertif();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("code:" + list.get(i).getCar_board_code()
					+ " car_type:" + list.get(i).getCar_board_type() + " name:"
					+ list.get(i).getName() + " engine:"
					+ list.get(i).getEngine_code() + " car_id:"
					+ list.get(i).getCar_id() + " regsitTime:"
					+ list.get(i).getResgitTime() + " papTime:"
					+ list.get(i).getPaperWorkTime()+" address"+list.get(i).getAddress());
		}
		DaoFactory dao=new DaoFactoryImpl();
		dao.insertNewCertificate(list);
	}
}

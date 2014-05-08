package com.putdata.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putdata.TestExcel;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class ReadNewphone {
	public static List<String> readphone() {
		DaoFactory dao = new DaoFactoryImpl();
		TestExcel test = new TestExcel();
		List<String> phonelist = new ArrayList<String>();
		List<String> list = null;
		String phone = null;
		try {
			list = test.readXlsx("//home//zxk//每日数据//新号码.xls");
			for (int i = 0; i < list.size(); i++) {
				int[] ints = RandomNum.norand(99);
				for (int j = 0; j < 99; j++) {
					System.out.println(j);
					int end = ints[j];
					phone = list.get(i).trim() + end;
					
						phonelist.add(phone);
					

				}
			}
			for (int i = 0; i < 100; i++) {
				Collections.shuffle(phonelist);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phonelist;
	}
	public static void main(String[] args) {
		List<String> list=readphone();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}

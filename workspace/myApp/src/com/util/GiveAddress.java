package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONObject;

public class GiveAddress {
	private static List<String> list = new ArrayList<String>();
	

	public void giveAddress(String lat, String log,int k) {
		StringBuffer document = new StringBuffer();
		try {
			URL url = new URL(
					"http://api.map.baidu.com/geocoder/v2/?ak=VVLgK9Ul8Q63X8o0ToDUD2SP&location="
							+ lat + "," + log + "&output=json&pois=0");// 远程url
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null)
				document.append(line + " ");
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String json = document.toString();// 返回值
		String addstr = getJSONObject(json);
		System.out.println(addstr+"——"+k);
		if ((addstr.indexOf("公路") < 1 && addstr.indexOf("路") > 0)
				|| addstr.indexOf("号")>0||addstr.indexOf("大道") > 0||addstr.indexOf("街")>0) {
			getStrList(addstr);
		}

	}

	public String getJSONObject(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONObject datajson = jsonObject.getJSONObject("result");
		String addStr = datajson.getString("formatted_address");
		return addStr;
	}

	public void getStrList(String str) {
		boolean issuccess = true;
		if (list.size() == 0) {
			list.add(str);
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (str.equals(list.get(i))) {
					issuccess = false;
					break;
				}
			}
			if (issuccess) {
				System.out.println(list.size());
				list.add(str);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		GiveAddress giveAddress = new GiveAddress();
		
		System.out.println(0);
		for (int i = 0; i < 1000; i++) {
			String lat = String.valueOf(Math.random() + 23);
			String lng = String.valueOf(Math.random() + 106);
			giveAddress.giveAddress(lat, lng,i);
		}
		System.out.println(1);
		HSSFWorkbook wb=ExcelUtil.exportExcel(list);
		System.out.println(2);
		FileOutputStream out=new FileOutputStream(new File("/root/Desktop/add.xls"));
		System.out.println(3);
		wb.write(out);
		System.out.println(4);
		out.close();
		System.out.println(5);
		out.flush();
		System.out.println(6);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}

	}
}

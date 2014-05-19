package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class InsertExcel {
	public static void insertExcel() throws IOException{
		List<String> list=ReadAddExcel.listreadXlsx("/root/Desktop/newadd.xls");
		List<String> newlist=new ArrayList<String>();
		for(int i=0;i<1000;i++){
			String str=list.get((int)(Math.random()*362+1));
			if(str.indexOf("号")<1){
				str=str+(int)(Math.random()*99+1)+"号";
			}
			newlist.add(str);
		}
		HSSFWorkbook wb=ExcelUtil.exportExcel(newlist);
		FileOutputStream out=new FileOutputStream(new File("/root/Desktop/address.xls"));
		System.out.println(3);
		wb.write(out);
		System.out.println(4);
		out.close();
		System.out.println(5);
		out.flush();
	}
	public static void main(String[] args) throws IOException {
		insertExcel();
	}
}

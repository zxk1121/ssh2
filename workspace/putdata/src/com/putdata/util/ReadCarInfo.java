package com.putdata.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.putdata.CarInfo;

public class ReadCarInfo {
	// public static void main(String[] args) throws IOException, ParseException
	// {
	// ReadToExcel read = new ReadToExcel();
	// List<Statisitcs> list = read.readXlsx();
	// System.out.println(list.size());
	// for (Statisitcs statisitcs : list) {
	// System.out.println(statisitcs.getNewUser() + " "
	// + statisitcs.getActiveUser() + " "
	// + statisitcs.getValidateUser() + " "
	// + statisitcs.getUploadcertificate() + " "
	// + statisitcs.getCertificatesuccess() + " "
	// + statisitcs.getCreated());
	// }
	// }

	public List<CarInfo> readXlsx() throws IOException, ParseException {
		String fileName = "//root//Desktop//xingshi//2.xls";
		List<CarInfo> list = new ArrayList<CarInfo>();
		ReadAddExcel readAdd = new ReadAddExcel();
		Map<String,List<String>> map = readAdd.readXlsx();
		int a=0,b=0,c=0,d=0,e=0;
		String enginecode="";
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(
				fileName));

		// 循环工作表Sheet
		int j = 0;
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet xssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			System.out.println(xssfSheet.getSheetName());
			// 循环行Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				CarInfo carInfo = new CarInfo();
				// 循环列Cell
				System.out.println("rowNum:"+rowNum);
				carInfo.setCar_board_code(getValue(xssfRow.getCell(0)));
				carInfo.setCar_board(getValue(xssfRow.getCell(1)));
				carInfo.setName(getValue(xssfRow.getCell(2)));
				carInfo.setPhone(getValue(xssfRow.getCell(3)));
				carInfo.setFirstTime(getValue(xssfRow.getCell(4)));
				carInfo.setExpireTime(getValue(xssfRow.getCell(5)));
				carInfo.setCarType(getValue(xssfRow.getCell(6)));
				carInfo.setCar_id(getValue(xssfRow.getCell(7)));
				if(getValue(xssfRow.getCell(8)).equals("")){
					enginecode=RandomNum.RandomEngineCode(10);
					carInfo.setEngine_code(enginecode);
				}else{
					carInfo.setEngine_code(getValue(xssfRow.getCell(8)));
				}
				
				String car_bordstr=carInfo.getCar_board_code().substring(0,2);
//				String address=;
//				if(car_bordstr.equals("浙A")) {
//					address=map.get("0").get(a++);
//				}
//				else if(car_bordstr.equals("浙B")){
//					address=map.get("1").get(b++);
//				}
//				else if(car_bordstr.equals("浙C")){
//					address=map.get("2").get(c++);
//				}
//				else if(car_bordstr.equals("浙D")){
//					address=map.get("3").get(d++);
//				}
//				else if(car_bordstr.equals("浙F")){
//					address=map.get("4").get(e++);
//				}
				carInfo.setAddress(getValue(xssfRow.getCell(9)));
				list.add(carInfo);
			}
		}

		for (int i = 0; i < 1000; i++) {
			Collections.shuffle(list);
		}

		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell xssfCell) {
		if(xssfCell==null){
			return "";
		}
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
	
	

}

package com.putdata.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.putdata.Statisitcs;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class ReadToExcel {
//	public static void main(String[] args) throws IOException, ParseException {
//		ReadToExcel read = new ReadToExcel();
//		List<Statisitcs> list = read.readXlsx();
//		System.out.println(list.size());
//		for (Statisitcs statisitcs : list) {
//			System.out.println(statisitcs.getNewUser() + " "
//					+ statisitcs.getActiveUser() + " "
//					+ statisitcs.getValidateUser() + " "
//					+ statisitcs.getUploadcertificate() + " "
//					+ statisitcs.getCertificatesuccess() + " "
//					+ statisitcs.getCreated());
//		}
//	}

	public List<Statisitcs> readXlsx() throws IOException, ParseException {
		String fileName = "//home//zxk//每日数据//App2.xls";
		String str = "2014-05-06 01:30:00";
		List list = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(
				fileName));

		// 循环工作表Sheet

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
				System.out.println(rowNum);
				System.out.println(xssfRow.getCell(2));
				Statisitcs statisitc = new Statisitcs();
				// 循环列Cell
				statisitc.setNewUser(getValue(xssfRow.getCell(2)).equals("")?"0":getValue(xssfRow.getCell(2)));
				statisitc.setActiveUser(getValue(xssfRow.getCell(3)).equals("")?"0":getValue(xssfRow.getCell(3)));
				statisitc.setValidateUser(getValue(xssfRow.getCell(4)).equals("")?"0":getValue(xssfRow.getCell(4)));
				statisitc.setUploadcertificate(getValue(xssfRow.getCell(5)).equals("")?"0":getValue(xssfRow.getCell(5)));
				statisitc.setCertificatesuccess(getValue(xssfRow.getCell(6)).equals("")?"0":getValue(xssfRow.getCell(6)));
				long millionSeconds = sdf.parse(str).getTime();// 毫秒
				statisitc.setCreated(millionSeconds/1000);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(str));
				calendar.add(Calendar.DATE, 1);
				str = sdf.format(calendar.getTime());
				list.add(statisitc);
			}
		}

		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		DaoFactory daoFactory=new DaoFactoryImpl();
		ReadToExcel readtoExcel = new ReadToExcel();
		List<Statisitcs> list = readtoExcel.readXlsx();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getNewUser());
//		}
		daoFactory.insertStatisitcs(list);
	}

}

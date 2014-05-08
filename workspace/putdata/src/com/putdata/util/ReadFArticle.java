package com.putdata.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class ReadFArticle {
	public List<Map<String,String>> readXlsx() throws IOException, ParseException {
		String fileName = "//home//zxk//android//精选文章//精选文章.xls";
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
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
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				Map<String,String> map=new HashMap<String, String>();
				System.out.println(getValue(xssfRow.getCell(0)));
//				map.put("articleId", getValue(xssfRow.getCell(0)));
				map.put("title", getValue(xssfRow.getCell(0)));
				map.put("url", getValue(xssfRow.getCell(1)));
				list.add(map);
			}
		}

		return list;
	}
	
	public List<Map<String,String>> readhistory() throws IOException, ParseException {
		String fileName = "//home//zxk//android//精选文章//文章分类(1).xls";
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
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
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				Map<String,String> map=new HashMap<String, String>();
				System.out.println(getValue(xssfRow.getCell(0)));
				if(xssfRow.getCell(0)!=null){
					map.put("articleId", getValue(xssfRow.getCell(0)));
				}else{
					map.put("articleId", "");
				}
				map.put("title", getValue(xssfRow.getCell(1)));
				map.put("url", getValue(xssfRow.getCell(2)));
				if(xssfRow.getCell(3)!=null){
					map.put("imageUrl", getValue(xssfRow.getCell(3)));
				}else{
					map.put("imageUrl", "");
				}
				list.add(map);
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
		ReadFArticle read=new ReadFArticle();
		DaoFactory dao=new DaoFactoryImpl();
//		dao.insertFeaturedArticle(read.readXlsx());
		dao.insertHistoryArticle(read.readhistory());
	}
}

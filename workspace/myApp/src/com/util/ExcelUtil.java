package com.util;


import java.awt.Font;
import java.util.List;
import java.util.Observer;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelUtil {

	public static HSSFWorkbook exportExcel(List<String> list 
    ) {     //创建excel文件对象 
            HSSFWorkbook wb = new HSSFWorkbook();  
            //创建一个张表  
            Sheet sheet = wb.createSheet();  
            //第三行表示  
            int l = 0;  
            //这里将学员的信心存入到表格中          
            for (int i = 0; i < list.size(); i++) {  
                //创建一行  
                Row rowData = sheet.createRow(l++);  
                createCell(wb, rowData, 0, list.get(i), null);  
              
            }  
            return wb;  
        }	/**
	 * 创建单元格并设置样式,值
	 * 
	 * @param wb
	 * @param row
	 * @param column
	 * @param
	 * @param
	 * @param value
	 */
	public static void createCell(Workbook wb, Row row, int column,
			String value, Font font) {
		Cell cell = row.createCell(column);
		cell.setCellValue(value);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
		cell.setCellStyle(cellStyle);
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str.trim()) || str.length() > 10)
			return false;
		Pattern pattern = Pattern.compile("^0|[1-9]\\d*(\\.\\d+)?$");
		return pattern.matcher(str).matches();
	}

}
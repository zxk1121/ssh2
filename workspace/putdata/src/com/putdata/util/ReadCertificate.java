package com.putdata.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.putdata.dto.Certificate;
import com.putdata.dto.UserStunk;

public class ReadCertificate {

	public List<Certificate> readXlsx() throws IOException, ParseException {
		String fileName = "//root//Desktop//xls文件//certificate1.xls";
		List<Certificate> list = new ArrayList<Certificate>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(
				fileName));

		// 循环工作表Sheet
		int j = 0;
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet xssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				Certificate cert = new Certificate();
				cert.setCid(Long.valueOf(getValue(xssfRow.getCell(0))));
				cert.setUserId(Long.valueOf(getValue(xssfRow.getCell(1))));
				cert.setCar_board_code(getValue(xssfRow.getCell(2)));
				cert.setCar_board_type(getValue(xssfRow.getCell(3)));
				cert.setEngine_code(getValue(xssfRow.getCell(4)));
				cert.setName(getValue(xssfRow.getCell(5)));
				cert.setAddress(getValue(xssfRow.getCell(6)));
				cert.setCreated(Long.valueOf(getValue(xssfRow.getCell(7))));
				cert.setCar_id(getValue(xssfRow.getCell(8)));
				cert.setUserProperty(getValue(xssfRow.getCell(9)));
				cert.setCarType(getValue(xssfRow.getCell(10)));
				cert.setResgitTime(getValue(xssfRow.getCell(11)));
				cert.setPaperWorkTime(getValue(xssfRow.getCell(12)));

				list.add(cert);

			}
		}

		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell xssfCell) {
		if (xssfCell == null) {
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

	public static void main(String[] args) throws IOException, ParseException {
		ReadCertificate readcert = new ReadCertificate();
		List<Certificate> list = readcert.readXlsx();
		for (int i = 0; i < list.size(); i++) {
			Certificate cert = list.get(i);
			System.out.println("cid:" + cert.getCid() + " userId:"
					+ cert.getUserId() + " car_board_code:"
					+ cert.getCar_board_code() + " car_board_type:"
					+ cert.getCar_board_type() + " engine_code:"
					+ cert.getEngine_code() + " name:" + cert.getName()
					+ " address:" + cert.getAddress() + " created:"
					+ cert.getCreated() + " car_id:" + cert.getCar_id()
					+ " userProperty:" + cert.getUserProperty() + " carType:"
					+ cert.getCarType() + " resgitTime:" + cert.getResgitTime()
					+ " paperWorkTime:" + cert.getPaperWorkTime());
		}
	}

}

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

import com.putdata.User;
import com.putdata.dto.UserStunk;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class ReadUser {

	public List<UserStunk> readXlsx() throws IOException, ParseException {
		String fileName = "//root//Desktop//user3.xls";
		List<UserStunk> list = new ArrayList<UserStunk>();
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
				UserStunk user = new UserStunk();
				user.setUserId(Long.valueOf(getValue(xssfRow.getCell(0))));
				user.setPhone(getValue(xssfRow.getCell(1)));
				user.setCreated(Long.valueOf(getValue(xssfRow.getCell(2))));
				user.setLatitude(getValue(xssfRow.getCell(3)));
				user.setLongitude(getValue(xssfRow.getCell(4)));
				user.setUserStats(Long.valueOf(getValue(xssfRow.getCell(5))));
				user.setDeviceCode(getValue(xssfRow.getCell(6)));
				user.setSystem(Long.valueOf(getValue(xssfRow.getCell(7))));
				user.setCertificate_pic(getValue(xssfRow.getCell(8)));
				user.setCertificate_status(getValue(xssfRow.getCell(9)));
				user.setRegistTime(Long.valueOf(getValue(xssfRow.getCell(10))));
				user.setAppVersion(getValue(xssfRow.getCell(11)));
				user.setLocationUpdateTime(Long.valueOf(getValue(xssfRow
						.getCell(12))));
				user.setDevice(getValue(xssfRow.getCell(13)));
				user.setNewtime(Long.valueOf(getValue(xssfRow.getCell(14))==""?"0":getValue(xssfRow.getCell(14))));
				list.add(user);

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
		ReadUser readuser = new ReadUser();
		List<UserStunk> list = readuser.readXlsx();
		DaoFactory dao=new DaoFactoryImpl();
		dao.insertNewUserStunk(list);
		for (int i = 0; i < list.size(); i++) {
			UserStunk user = list.get(i);
			System.out.println("userId:" + user.getUserId() + "phone:"
					+ user.getPhone() + "created:" + user.getCreated()
					+ "latitude:" + user.getLatitude() + "longitude:"
					+ user.getLongitude() + "userStats:" + user.getUserStats()
					+ "devicecode:" + user.getDeviceCode() + "system:"
					+ user.getSystem() + "certificate_pic:"
					+ user.getCertificate_pic() + "certificate_status:"
					+ user.getCertificate_status());

		}
	}

}

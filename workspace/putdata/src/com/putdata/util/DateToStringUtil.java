package com.putdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateToStringUtil {
	public static void millDateFormat(long millis) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(millis);
		String time = format.format(date);
		System.out.println("time1:" + time);
	}

	public static void DatemillFormat(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timestr = format.parse(date);
		long time = timestr.getTime() / 1000;
		System.out.println(date+":"+time);
	}

	public static String DateStr(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(time * 1000);
		String timeStr = format.format(date);
		return timeStr;
	}

	public static void main(String[] args) throws ParseException {
		
		DateToStringUtil.millDateFormat(1378691798L*1000);
		DateToStringUtil.DatemillFormat("2014-02-15 01:30:00");
	}
	
	public static String millDateFormatYMD(long millis) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(millis);
		String time = format.format(date);
		System.out.println("time:" + time);
		return time;
	}
	
	public static String addMonth(String str) throws ParseException{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=format.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,(int) (Math.random()*5+1));
		String newStr=format.format(calendar.getTime());
		return newStr;
	}

}

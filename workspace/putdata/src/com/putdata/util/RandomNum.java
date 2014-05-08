package com.putdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomNum {

	// 产生不重复的随机数

	public long[] NoRepeatRandomNum(Long startNum, Long endNum, int count) {
		System.out.println("startNum:" + startNum + "endNum:" + endNum
				+ "count:" + count);
		long[] time = new long[count];
		for (int i = 0; i < count; i++) {
			time[i] = startNum + (long) (Math.random() * (endNum - startNum));
			for (int j = 0; j < i; j++) {
				if (time[i] == time[j]) {
					i--;
					break;
				}
			}
		}
		return time;
	}
	
	public static int[] norand(int count){
		int[] ints=new int[count];
		for (int i = 0; i < count; i++) {
			ints[i] =(int) ((Math.random() * 9000+1000));
			for (int j = 0; j < i; j++) {
				if (ints[i] == ints[j]) {
					i--;
					break;
				}
			}
		}
		return ints;
	}

	public static long[] yesterDayTotoDayRandomNum(String startStr, String endStr,
			Long startNum, Long endNum, int count) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String maxStr=startStr+" 23:30:00";
		String minStr=endStr+" 09:00:00";
		Date maxDate=sdf.parse(maxStr);
		Date minDate=sdf.parse(minStr);
		long maxTime=maxDate.getTime()/1000;
		long minTime=minDate.getTime()/1000;
		long[] time = new long[count];
		for (int i = 0; i < count; i++) {
			time[i] = startNum + (long) (Math.random() * (endNum - startNum));
			if(time[i]>maxTime&&time[i]<minTime){
				
				i--;
			}
			for (int j = 0; j < i; j++) {
				if (time[i] == time[j]) {
					i--;
					break;
				}
			}
		}
		return time;
	}

	public static String RandomEngineCode(int length) {
		Random select = new Random();

		// 更改数字可以选择随机出现的字符串长度
//		int letterNum = 10 + select.nextInt(10);
		int nowletter = 0;
		char nowlet = ' ';
		StringBuffer target = new StringBuffer(50);

		out: for (int i = 0; i < length;) {
			// 这里更改数字可以决定出现的字符是出现在什么字符之间
			nowletter = select.nextInt(90);
			int j = 1;
			if ((nowletter > 49 && nowletter < 58) || nowletter > 65) {
				if (nowletter > 49 && nowletter < 58) {
					j++;
				}
				if (j == 4)
					continue out;
				nowlet = (char) nowletter;
				target.append(nowlet);
				i++;
			} else {
				continue;
			}
		}
		return target.toString();
	}
	
	public static String getCharAndNumr(int length)     
	{     
	    String val = "";     
	             
	    Random random = new Random();     
	    for(int i = 0; i < length; i++)     
	    {     
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
	                 
	        if("char".equalsIgnoreCase(charOrNum)) // 字符串     
	        {     
	            int choice =  65; //取得大写字母还是小写字母     
	            val += (char) (choice + random.nextInt(26));     
	        }     
	        else if("num".equalsIgnoreCase(charOrNum)) // 数字     
	        {     
	            val += String.valueOf(random.nextInt(10));     
	        }     
	    }     
	             
	    return val;     
	} 
	
	public static String RandomDate() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String maxStr="2005-01-01";
		String minStr="2013-12-31";
		Date maxDate=sdf.parse(maxStr);
		Date minDate=sdf.parse(minStr);
		long maxTime=maxDate.getTime();
		long minTime=minDate.getTime();
		long time = minTime + (long) (Math.random() * (maxTime - minTime));
		String timeStr=sdf.format(new Date(time));
		return timeStr;
	}
	
	public static void main(String[] args) throws ParseException {
		String str=RandomDate();
		System.out.println(str);
	}

}

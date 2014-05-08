package com.putdata;

import java.lang.Character.UnicodeBlock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.putdata.util.QuickSort;
import com.putdata.util.RandomNum;
import com.putdata.util.ReadNewphone;
import com.putdata.util.ReadToExcel;
import com.putdata.util.UpdatePhone;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.impl.DaoFactoryImpl;

public class InsertPhoneData {
	private static DaoFactory daoFactory;

	public static void main(String[] args) throws Exception {

		 daoFactory = new DaoFactoryImpl();

		// newinsert();
		// ReadAddExcel read=new ReadAddExcel();
		// List<String> list=read.readXlsx();
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i));
		// }
		// System.out.println(list.size());
		// ReadCarInfo readCar = new ReadCarInfo();
		// List<CarInfo> list = readCar.readXlsx();
		// daoFactory.insertCarInfo(list);

		// 插入用户
		// InsertUserStunk.insertUserStunk();

		// App每日统计表

//		ReadToExcel readtoExcel = new ReadToExcel();
//		List<Statisitcs> list = readtoExcel.readXlsx();
////		for (int i = 0; i < list.size(); i++) {
////			System.out.println(list.get(i).getNewUser());
////		}
//		daoFactory.insertStatisitcs(list);

		// long start=System.currentTimeMillis();
		// 连接Ftp
		// String serverUrl = "v0.ftp.upyun.com";
		// String username = "zxk/newcertifpic";
		// String password = "zxk12345";
		// String fromuser = "wxm/certif";
		// String frompass = "wxm12345";
		// InputStream in=null;
		// FtpServerUtil downFtp=new FtpServerUtil(serverUrl, 21, fromuser,
		// frompass);
		// FtpServerUtil uploadFtp=new FtpServerUtil(serverUrl, 21, username,
		// password);
		// if(downFtp.ftplogin()){
		// in = downFtp.downFile("/2013/11/11", "1384140855639.jpg");
		// }
		// if(in!=null&uploadFtp.ftplogin()){
		// uploadFtp.uploadFile("/2014/01/17/", "1384140855639.jpg", in);
		// }

		// SimpleDateFormat format=new SimpleDateFormat("yyyy/MM");
		// Date date=new Date();
		// String mouth=format.format(date);
		//
		// FtpServerUtil deleDir=new FtpServerUtil(serverUrl, 21, username,
		// password);
		// if(deleDir.ftplogin()){
		// deleDir.deleFile("/"+mouth);
		// deleDir.getFtpclient().removeDirectory("/"+mouth);
		// deleDir.getFtpclient().makeDirectory("/"+mouth);
		// deleDir.getFtpclient().logout();
		// deleDir.disContent();
		// }
		// InsertUserStunk.insertUserStunk();
		// newinsert();
		// System.out.println((System.currentTimeMillis()-start)/1000+"s");
		// DateToStringUtil.millDateFormat(1386861100L*1000);
		// InsertPhone inphone = new InsertPhone();
		// inphone.insertPhone();
		// UpdateCarInfoAddress.updateCarInfoAddress();

		// UpdateCarInfoUserId.updateCarInfoUserId();
		// ReadNewphone.readphone();
		 newinsert();
//		 insert();
	}

	public static void newinsert() throws Exception {
		long s=System.currentTimeMillis();
		RandomNum randonNum = new RandomNum();
		List<String> phonelist = ReadNewphone.readphone();
		List<Statisitcs> statisitcslist = daoFactory.queryStatisitcs();
		List<User> userlist = new ArrayList<User>();
		int k = 0;
		long id =885458;
		String timeStr;
		String startTimeStr;
		String endTimeStr;
		long startTime;
		long endTime;
		for (int i = statisitcslist.size()-4; i < statisitcslist.size(); i++) {
			Statisitcs statisitcs = statisitcslist.get(i);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = new Date(statisitcs.getCreated() * 1000);
			timeStr = format.format(start);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(timeStr));
			calendar.add(Calendar.DATE, -1);
			timeStr = format.format(calendar.getTime());
			endTimeStr = timeStr + " 23:30:00";
			startTimeStr = timeStr + " 09:00:00";
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = format.parse(startTimeStr);
			startTime = time.getTime() / 1000;
			time = format.parse(endTimeStr);
			endTime = time.getTime() / 1000;
			long randtime[] = randonNum.NoRepeatRandomNum(startTime, endTime,
					Integer.valueOf(statisitcslist.get(i).getValidateUser()));
			QuickSort.quitesort(randtime, 0, randtime.length - 1);
			for (int j = 0; j < Integer.valueOf(statisitcslist.get(i)
					.getValidateUser()); j++) {
				String phone = phonelist.get(k);
				User user = new User();
				user.setPhone(phone);
				user.setCreated(randtime[j]);
				user.setUserId(id);
				userlist.add(user);
				k++;
				id++;
			}
			k++;
			// k=k+Integer.valueOf(statisitcslist.get(i).getValidateUser());
		}
		for(int i=0;i<userlist.size();i++){
			System.out.println(userlist.get(i).getPhone());
		}
		daoFactory.insertPhone(userlist);
//		
//		UpdatePhone.updatePhone();
		long e=System.currentTimeMillis();
		System.out.println((e-s)/1000);		
	}
	

	public static void insert() {
		try {
			long startSys = System.currentTimeMillis();
			RandomNum randonNum = new RandomNum();
			List<?> phonelist = ReadNewphone.readphone();
			List<User> userList = new ArrayList<User>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse("2014-01-24");
			long startTime = start.getTime() / 1000;
			long endTime = System.currentTimeMillis() / 1000;
			long[] timearr = randonNum.NoRepeatRandomNum(startTime, endTime,
					phonelist.size());
			QuickSort.quitesort(timearr, 0, timearr.length - 1);
			long k=213000;
			for (int i = 0; i < phonelist.size(); i++) {
				User user = new User();
				user.setUserId(k++);
				user.setPhone(phonelist.get(i).toString());
				user.setCreated(timearr[i]);
				userList.add(user);
			}
			DaoFactory daoFactory = new DaoFactoryImpl();
			daoFactory.insertPhone(userList);
			System.out.println((System.currentTimeMillis() - startSys) / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String utf8ToUnicode(String inStr) {
		char[] myBuffer = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++) {
			UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
			if (ub == UnicodeBlock.BASIC_LATIN) {
				// 英文及数字等
				sb.append(myBuffer[i]);
			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				// 全角半角字符
				int j = (int) myBuffer[i] - 65248;
				sb.append((char) j);
			} else {
				// 汉字
				int s = (int) myBuffer[i];
				String hexS = Integer.toHexString(s);
				String unicode = "\\u" + hexS;
				sb.append(unicode.toLowerCase());
			}
		}
		return sb.toString();
	}

}

package com.putdata.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import sun.net.ftp.FtpClient;

import com.putdata.CarInfo;
import com.putdata.dto.Certificate;
import com.putdata.dto.RejectReason;
import com.putdata.dto.UserStunk;
import com.putdata.util.dao.DaoFactory;
import com.putdata.util.dao.InsertUserStunkDao;
import com.putdata.util.dao.impl.DaoFactoryImpl;
import com.putdata.util.dao.impl.InsertUserStunkDaoImpl;

public class InsertUserStunk {
	private static DaoFactory daoFactory;

	// private static final String URL = "v0.ftp.upyun.com";
	// private static final int PORT = 21;
	// private static final String DOWN_USERNAME = "wxm/certif";
	// private static final String DOWN_PASSWORD = "wxm12345";
	// private static final String UPLOAD_USERNAME = "zxk/newcertif";
	// private static final String UPLOAD_PASSWORD = "zxk12345";

	public static void insertUserStunk() {
		daoFactory = new DaoFactoryImpl();
		daoFactory.deleNewUserStunk();
		daoFactory.deleNewCertificate();
		daoFactory.deleReject();
		List<UserStunk> list = daoFactory.queryUserStunk();
		List<Certificate> clist = daoFactory.queryCertificate();
		List<RejectReason> rlist = daoFactory.queryrejectReason();
		List<Certificate> cerlist = new ArrayList<Certificate>();
		List<RejectReason> rejlist = new ArrayList<RejectReason>();
		List<UserStunk> newlist = new ArrayList<UserStunk>();
		long[] times = null;
		int count = 211450;
		try {
			times = InsertUserStunk.setcreated(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// FtpServerUtil downFile = new FtpServerUtil(URL, PORT, DOWN_USERNAME,
		// DOWN_PASSWORD);
		// FtpServerUtil uploadFile = new FtpServerUtil(URL, PORT,
		// UPLOAD_USERNAME, UPLOAD_PASSWORD);
		for (int i = 0; i < list.size(); i++) {
			if (count % 10 == 0) {
				count = count;
			} else {
				count++;
			}

			UserStunk userstunk = list.get(i);
			UserStunk newuserstunk = new UserStunk();
			newuserstunk.setUserId(count);
			newuserstunk.setCreated(times[i]);
			newuserstunk.setPhone(userstunk.getPhone());
			newuserstunk.setLongitude(userstunk.getLongitude());
			newuserstunk.setLatitude(userstunk.getLatitude());
			newuserstunk.setUserStats(userstunk.getUserStats());
			newuserstunk.setDeviceCode(userstunk.getDeviceCode());
			newuserstunk.setSystem(userstunk.getSystem());
			newuserstunk.setCertificate_status(userstunk
					.getCertificate_status());
			newuserstunk.setAppVersion(userstunk.getAppVersion());
			newuserstunk.setDevice(userstunk.getDevice());
			newuserstunk.setTtid(userstunk.getTtid());
			newuserstunk.setRegistTime(userstunk.getRegistTime());
			newuserstunk.setNewtime(times[i]);

			if (userstunk.getLocationUpdateTime() > 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date(times[i] * 1000));
				calendar.add(Calendar.DATE, 2);
				newuserstunk
						.setLocationUpdateTime(calendar.getTimeInMillis() / 1000);
			}

			String datePath = DateToStringUtil.DateStr(times[i]);
			String path = userstunk.getCertificate_pic();
			if (path != null && !"".equals(path)) {
				String[] paths = path.split("/");
				String picname = paths[paths.length - 1];
				String[] url = paths[2].split("\\.");
				String http = paths[0] + "//new" + url[0] + "pic." + url[1]
						+ "." + url[2] + "." + url[3] + "/certifpic/image"
						+ "/" + picname;
				newuserstunk.setCertificate_pic(http);
				// InputStream in = null;
				// if (downFile.ftplogin()) {
				// in = downFile.downFile("/" + paths[3] + "/" + paths[4]
				// + "/" + paths[5], picname);
				// }
				// if (in!=null&uploadFile.ftplogin()) {
				// System.out.println(datePath+"/"+picname);
				// uploadFile.uploadFile("/" + datePath, picname, in);
				// }
			}

			newlist.add(newuserstunk);

			// 插入行驶证数据
			for (int j = 0; j < clist.size(); j++) {
				Certificate certificate = clist.get(j);
				Certificate newcer = new Certificate();
				if (userstunk.getUserId() == certificate.getUserId()) {
					newcer.setUserId(newuserstunk.getUserId());
					newcer.setCid(100000 + certificate.getCid());
					newcer.setCar_board_code(certificate.getCar_board_code());
					newcer.setCar_board_type(certificate.getCar_board_type());
					newcer.setEngine_code(certificate.getEngine_code());
					newcer.setName(certificate.getName());
					newcer.setAddress(certificate.getAddress());
					newcer.setCreated(newuserstunk.getCreated());
					newcer.setCar_id(certificate.getCar_id());
					newcer.setUserProperty(certificate.getUserProperty());
					newcer.setCarType(certificate.getCarType());
					newcer.setCreatedTime(certificate.getCreatedTime());
					newcer.setResgitTime(certificate.getResgitTime());
					newcer.setPaperWorkTime(certificate.getPaperWorkTime());
					// System.out.println("userId:"+newcer.getUserId()+" cid:"+newcer.getCid()+" car_board_code:"+newcer.getCar_board_code()
					// +" car_board_type:"+newcer.getCar_board_type()+" engine_code:"+newcer.getEngine_code()+" name:"+newcer.getName()
					// +" address:"+newcer.getAddress()+" created:"+newcer.getCreated()+" car_id:"+newcer.getCar_id()+" UserProperty:"+newcer.getUserProperty()
					// +" carType:"+newcer.getCarType()+" createTime:"+newcer.getCreatedTime()+" resgittime:"+newcer.getResgitTime()+" paperworktime:"+newcer.getPaperWorkTime());
					cerlist.add(newcer);
				}
			}

			for (int k = 0; k < rlist.size(); k++) {
				RejectReason rejReason = rlist.get(k);
				RejectReason reason = new RejectReason();
				if (rejReason.getUserId() == userstunk.getUserId()) {
					reason.setId(100000 + rejReason.getId());
					reason.setUserId(newuserstunk.getUserId());
					reason.setReason(rejReason.getReason());
					reason.setCreated(newuserstunk.getCreated());
					// System.out.println("id:"+reason.getId()+" userid:"+reason.getUserId()+"reason:"+reason.getReason()+" created:"+reason.getCreated());
					rejlist.add(reason);
				}
			}
		}
		daoFactory.insertNewUserStunk(newlist);
		daoFactory.insertNewCertificate(cerlist);
		daoFactory.insertRejectReason(rejlist);
	}

	private static long[] setcreated(int count) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowStr = format.format(now);
		long endtime = now.getTime() / 1000;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(format.parse(nowStr));
		calendar.add(Calendar.DATE, -1);
		String yDayStr = format.format(calendar.getTime());
		String startStr = yDayStr + " 09:00:00";
		Date startdate = sdf.parse(startStr);
		long starttime = startdate.getTime() / 1000;
		long[] times = RandomNum.yesterDayTotoDayRandomNum(yDayStr, nowStr,
				starttime, endtime, count);
		QuickSort.quitesort(times, 0, times.length - 1);
		return times;
	}

	public static void insertUserCert() {
		try {
			InsertUserStunkDao insertuser=new InsertUserStunkDaoImpl();
			ReadUser readUser = new ReadUser();
			ReadCertificate readCert = new ReadCertificate();
			List<UserStunk> userlist = readUser.readXlsx();
			List<Certificate> certlist = readCert.readXlsx();
			List<UserStunk> newuserlist = new ArrayList<UserStunk>();
			List<Certificate> newcertlist = new ArrayList<Certificate>();
			List<Long> createdtimes = new ArrayList<Long>();
			int k = 220300, certCount = 21050;
			long[] times = InsertUserStunk.setcreated(userlist.size());
			for (int i = 0; i < times.length; i++) {
				createdtimes.add(times[i]);
			}
			for (int i = 0; i < 100; i++) {
				Collections.shuffle(createdtimes);
			}
			for (int i = 0; i < userlist.size(); i++) {
				UserStunk user = userlist.get(i);
				user.setCreated(createdtimes.get(i));
				if (user.getCertificate_pic() != null
						&& user.getCertificate_pic() != "") {
					user.setNewtime(createdtimes.get(i));
				}
				for (int j = 0; j < certlist.size(); j++) {
					Certificate cert = certlist.get(j);
					if (cert.getUserId() == user.getUserId()) {
						cert.setCid(certCount++);
						cert.setCreated(user.getCreated()
								+ Long.valueOf((int) Math.random() * 82800 + 300));
						cert.setUserId(k);
						newcertlist.add(cert);
					}
				}
				user.setUserId(k++);
				newuserlist.add(user);
			}
			insertuser.insertUserStunk(newuserlist);;
			insertuser.insertCertificate(newcertlist);
			for (int i = 0; i < 100; i++) {
				UserStunk user = userlist.get(i);
				System.out.println("userId:" + user.getUserId() + " phone:"
						+ user.getPhone() + " created:" + user.getCreated()
						+ " latitude:" + user.getLatitude() + " longitude:"
						+ user.getLongitude() + " userStats:"
						+ user.getUserStats() + " devicecode:"
						+ user.getDeviceCode() + " system:" + user.getSystem()
						+ " certificate_pic:" + user.getCertificate_pic()
						+ " certificate_status:" + user.getCertificate_status()
						+ " registTime:" + user.getRegistTime()
						+ " appVersion:" + user.getAppVersion()
						+ " locationUpdateTime:" + user.getLocationUpdateTime()
						+ " device:" + user.getDevice() + " newtime:"
						+ user.getNewtime());
			}
			for(int i=0;i<50;i++){
				Certificate cert = newcertlist.get(i);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		InsertUserStunk.insertUserCert();
	}
}

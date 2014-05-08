package com.putdata.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

public class FtpServerUtil {

	private FTPClient ftpclient;
	private String url;
	private int port;
	private String username;
	private String password;

	private static int k = 1;

	public FtpServerUtil(String url, int port, String username, String password) {
		this.url = url;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public boolean ftplogin() {
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setDataTimeout(60000); // 设置传输超时时间为60秒
			ftp.setConnectTimeout(60000); // 连接超时为60秒
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			this.ftpclient = ftp;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public boolean uploadFile(String path, String filename, InputStream input) {
		boolean success = false;
		try {
//			int reply;
//			reply = ftpclient.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftpclient.disconnect();
//				return success;
//			}
			boolean isDir = ftpclient.changeWorkingDirectory(path);
			System.out.println(isDir);
			if (!isDir) {
				ftpclient.makeDirectory(path);
				ftpclient.changeWorkingDirectory(path);
			}
			ftpclient.storeFile(filename, input);
			input.close();
			ftpclient.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpclient.isConnected()) {
				try {
					ftpclient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		System.out.println(++k);
		return success;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public InputStream downFile(String remotePath, String fileName) {
		InputStream in = null;
		try {
//			int reply;
//			ftpclient.connect(url, port);
//			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//			ftpclient.login(username, password);// 登录
//			reply = ftpclient.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftpclient.disconnect();
//				return null;
//			}
//			 ftpclient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
//			FTPFile[] fs = ftpclient.listFiles();
//			for (FTPFile ff : fs) {
//				if (ff.getName().equals(fileName)) {
//					// File localFile = new File(localPath + "/" +
//					// ff.getName());
//
//					// OutputStream is = new FileOutputStream(localFile);
//					// ftp.retrieveFile(ff.getName(), is);
					in = ftpclient.retrieveFileStream(remotePath+"/"+fileName);
					// is.close();
//				}
//			}

			ftpclient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpclient.isConnected()) {
				try {
					ftpclient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return in;
	}

	public void deleFile(String path) {
		try {
			int reply;
			reply = ftpclient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpclient.disconnect();
				return;
			}
			boolean success = ftpclient.changeWorkingDirectory(path);
			if (success) {
				FTPFile[] ftf = ftpclient.listFiles(path);
				for (FTPFile ftpFile : ftf) {
					if (ftpFile.isDirectory()) {
						deleFile(path + "/" + ftpFile.getName());
						if (ftpFile.getSize() == 0) {
							ftpclient.removeDirectory(path + "/"
									+ ftpFile.getName());
						}
					} else {
						ftpclient.deleteFile(path + "/" + ftpFile.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void disContent() {
		if (ftpclient.isConnected()) {
			try {
				ftpclient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public FTPClient getFtpclient() {
		return ftpclient;
	}

	public void downlocal(String remotePath, String fileName, String localPath) {
		try {
			int reply;
			ftpclient.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpclient.login(username, password);// 登录
			reply = ftpclient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpclient.disconnect();
				return;
			}
			ftpclient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftpclient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftpclient.retrieveFile(ff.getName(), is);

					// in = ftpclient.retrieveFileStream(ff.getName());
					// is.close();
				}
			}

			ftpclient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpclient.isConnected()) {
				try {
					ftpclient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

	}

	public InputStream downloadFile(String serverurl, int pt, String user,
			String pd, String path, String filename) {
		TelnetInputStream is = null;
		sun.net.ftp.FtpClient ftp = new FtpClient();
		try {
			ftp.openServer(serverurl, pt);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(user, pd);// 登录
			ftp.binary();
			System.out.println(path+"/"+filename);
			is = ftp.get(path + "/" + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}

	public void upload(String serverurl, int pt, String user, String pd, String newname, InputStream in) throws Exception {
		TelnetOutputStream os = null;
		InputStream is = in;
		try {
			sun.net.ftp.FtpClient ftp = new FtpClient();
			ftp.openServer(serverurl, pt);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(user, pd);// 登录
			ftp.binary();
			System.out.println(newname);
			os = ftp.put(newname);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				System.out.println(bytes.toString());
				os.write(bytes, 0, c);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}

		}
	}
}

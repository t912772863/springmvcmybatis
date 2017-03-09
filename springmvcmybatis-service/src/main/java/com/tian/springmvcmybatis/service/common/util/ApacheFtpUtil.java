package com.tian.springmvcmybatis.service.common.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * 下载网维用户文本工具类
 * @author Administrator
 *
 */
public class ApacheFtpUtil {
	
	private static String LOCAL_CHARSET = "GBK";

    /**
     * 从FTP服务器上下载文件
	 * @param ftpPath ftp服务器的IP地址
	 * @param user 用户名
	 * @param password 密码
	 * @param remotePath 远程路径(不包含文件名)
	 * @param localFullPath 本地文件路径(包含文件名)
	 * @param port 端口号
	 * @param fileName 目标文件名
	 * @return
     */
	public static boolean downloadFromFtp(String ftpPath, String user, String password, String remotePath, String localFullPath, int port,
                                          String fileName){
		FTPClient ftpClient = new FTPClient();
	    FileOutputStream fos = null;
	    boolean flag = false;
		try{
			//连接FTP服务器 
			ftpClient.connect(ftpPath, port); 
			//登录FTP服务器
			ftpClient.enterLocalPassiveMode();
			ftpClient.login(user, password);
			//验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode(); 
			if(!FTPReply.isPositiveCompletion(replyCode))
			{
				return flag;
			}  
			 
			if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(
			"OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
			LOCAL_CHARSET = "UTF-8";
			}
			ftpClient.setControlEncoding(LOCAL_CHARSET); 
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			//切换FTP目录  
			ftpClient.changeWorkingDirectory(remotePath);
			//判斷目標文件夾是否存在,沒有則創建
			checkTargetPath(remotePath);
			//下载压缩文件到本地 
			File Localfile = new File(localFullPath);
			FTPFile[]fs = ftpClient.listFiles();
            for(FTPFile f:fs)
            {   
            	if(f.getName().equals(fileName))
	              {    
	            	  fos = new FileOutputStream(Localfile);
	       			  flag = ftpClient.retrieveFile(new String(fileName.getBytes("UTF-8"),"ISO-8859-1"), fos);
	       			  fos.flush();
	       			  fos.close();  
	               }    
            } 
			System.out.println("end of download file from ftp:"+new Timestamp(System.currentTimeMillis()));
			 
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ftpClient.isConnected()){
				try {
					fos.close();
					ftpClient.logout();
				} 
				catch (IOException e) {
					e.printStackTrace();	
				}
			}     
		}
		return flag; 
	}
	
	/**
	 * 检查文件夹是否存在并且创建文件夹
	 * @param dirpath
	 */
	public static void checkTargetPath(String dirpath) {
		File file=new File(dirpath);
		mkDir(file);
	}
	
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {  
            file.mkdir();  
			file.setReadable(true, false);
			file.setExecutable(true, false); 
        } else {    
            mkDir(file.getParentFile());  
            file.mkdir();  
			file.setReadable(true, false);
			file.setExecutable(true, false);  
        }  
	}  
    
    public static void main(String[] args){
    	String filePath = "/home/richmail/dmp_dataservers/ftp/Files/20170112/22.5657870000,113.8696260000/100";
    	downloadFromFtp("10.153.93.101",//ip地址
				"etonfile",// 用户名
				"etonfile",// 密码
				"/home/richmail/dmp_dataservers/ftp/Files/20170112/22.5657870000,113.8696260000/100",// 文件夹路径
				"E:/test001.txt", // 本地路径+文件名
				21, //端口号
				"用户常驻地_1484181501045_99_1484181700.txt" //目标文件名
		);
    }
}

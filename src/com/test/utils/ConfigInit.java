package com.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigInit {
	

	public static String message_url;
	public static String message_user;
	public static String message_pwd;
	
	public static String server_url;
	public static int server_port;
	
	static{
		server_url = ConfigInit.readConfig("shhost");
		server_port = Integer.parseInt(ConfigInit.readConfig("shport"));
//		message_url=ConfigInit.readConfig("message_url");
//		message_user=ConfigInit.readConfig("message_user");
//		message_pwd=ConfigInit.readConfig("message_pwd");
	}
	
	public static String readConfig(String key) {
		InputStream in = ConfigInit.class.getResourceAsStream("/properties/system.properties");
		Properties pr = new Properties();
		String value=null;
		try {
			pr.load(in);
			value = pr.getProperty(key);
		} catch (Exception e) {
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}
}

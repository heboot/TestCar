package com.test.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class Utils {
	public final static String BAIDU_MAP_KEY = "BB4FEFE6EE3ADDA18993E36DC6A4F4827F30C604";
	public static ProgressDialog progressDialog;
	/*
	 * 功能说明：判断手机是否连接到网络
	 */
	public static boolean isConnectToInternet(ConnectivityManager conManager) {
		// 获取当前活动的网络
		NetworkInfo info = conManager.getActiveNetworkInfo();
		if (info == null || "".equals(info) || !info.isAvailable()) {
			return false;
		}
		return true;
	}
	
	public static boolean isEmpty(String testString, String hintText) {
		if (testString == null || "".equals(testString.trim())
				|| testString.equals(hintText)) {
			return true;
		}
		return false;
	}
	
	public static String getIMSI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String IMSI = telephonyManager.getSubscriberId();
		return IMSI;
	}
}

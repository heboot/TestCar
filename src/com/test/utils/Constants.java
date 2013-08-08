package com.test.utils;

import java.util.HashMap;
import java.util.Map;

/**@功能说明：
 *@author:zzh
 *@date:2012-2-8
 */
public class Constants {
	
	public static String IMSI= "";
	
	public static final Map<Byte,String> areaMap = new HashMap<Byte, String>(6);
	static{
		areaMap.put((byte)-1, "不限");
		areaMap.put((byte)19, "嘉定区域出租");
		areaMap.put((byte)20, "松江区域出租");
		areaMap.put((byte)21, "金山区域出租");
		areaMap.put((byte)22, "奉贤区域出租");
		areaMap.put((byte)23, "崇明区域出租");
	}
	
	public static final String tableCrossRoad = "C_CROSSROAD";
	
	public static final int sdCardSizeMin = 6;//在进行软件更新时，sdcard的剩余空间大小最小值，单位为M

	public static String getIMSI() {
		return IMSI;
	}

	public static void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	
}

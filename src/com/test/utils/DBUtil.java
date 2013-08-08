package com.test.utils;

import java.io.PrintStream;
import java.util.Map;

import com.test.car.entity.Cartypes;
import com.test.car.entity.Driver;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DBUtil {
	private static final PrintStream printStream = System.out;
	private static final String DATABASE_NAME = "Shouji.db";
	private static final String TAG = DBUtil.class.getSimpleName();
	SQLiteDatabase db;

	public DBUtil(Context myContext) {
		try {
			db = myContext.openOrCreateDatabase(DATABASE_NAME, 2, null);
			CreateTable();

		} catch (IllegalStateException illegalstateexception) {
			Log.v(TAG, illegalstateexception.getMessage());
		}
		Log.v("DBUtil", (new StringBuilder("db path=")).append(db.getPath())
				.toString());
	}

	public void CreateTable() {
		if (!tabbleIsExist("C_RECORD")) {
			db.execSQL("CREATE TABLE C_RECORD ( _ID INTEGER PRIMARY KEY autoincrement, NAME TEXT,"
					+ " PHONE TEXT, TYPE TEXT, ADDRESS TEXT, GPSADDRESS TEXT, NOTES TEXT, DATE TEXT, "
					+ "TIME TEXT, ARRIVETIME TEXT, MEMNO TEXT, TASKID TEXT, STATUS TEXT, AType TEXT, "
					+ "Crossing TEXT,DRIVER_PHONE TEXT,APPRAISE TEXT,IMEI TEXT,SERVICENO TEXT,"
					+ "FINISHPOINT TEXT,PLACEMARK TEXT,ROAD TEXT,NONG TEXT,ZHINONG TEXT,HOUSENO TEXT,"
					+ "RIDGEPOLE TEXT,ROOMNO TEXT,lAT TEXT,LNG TEXT" + ");");
			Log.v("DBUtil", "Create Table C_RECORD ok");
		}
		if (!tabbleIsExist(Constants.tableCrossRoad)) {
			db.execSQL("CREATE TABLE " + Constants.tableCrossRoad
					+ " ( _ID INTEGER PRIMARY KEY autoincrement, LAT TEXT,"
					+ " LNG TEXT, ROAD TEXT, CROSSROAD TEXT);");
			Log.v("DBUtil", "Create Table C_CROSSROAD ok");
		}

		if (!tabbleIsExist("C_PARAMSET")) {
			db.execSQL("CREATE TABLE C_PARAMSET ( _ID INTEGER PRIMARY KEY autoincrement, FIRSTNAME TEXT,"
					+ " Sex TEXT, Phone TEXT, OUT_NUM TEXT, OUT_STATUS TEXT, PASSWORD TEXT, EMAIL TEXT, "
					+ "ISREMEMBER TEXT, ISAUTOLOGON TEXT, DEVICEID TEXT,SECONDNAME TEXT);");
			Log.v("DBUtil", "Create Table C_PARAMSET ok");
		}
		if (!tabbleIsExist("C_USERINFO")) {
			db.execSQL("CREATE TABLE C_USERINFO ( _ID INTEGER PRIMARY KEY autoincrement,SEX TEXT, PHONE TEXT,DEVICEID TEXT);");
			Log.v("DBUtil", "Create Table C_PARAMSET ok");
		}
		if (!tabbleIsExist("C_MyFavorites")) {
			db.execSQL("CREATE TABLE C_MyFavorites ( _ID INTEGER PRIMARY KEY autoincrement, Name TEXT,"
					+ " Address TEXT, X TEXT, Y TEXT, Notes TEXT, AType TEXT, Crossing TEXT);");
			Log.v(TAG, "Create Table C_MyFavorites ok");
		}
		// 存放广告信息
		if (!tabbleIsExist("C_ADS")) {
			db.execSQL("CREATE TABLE C_ADS ( _ID INTEGER PRIMARY KEY autoincrement, NUM TEXT,"
					+ " CONTENT TEXT);");
			Log.v(TAG, "Create Table C_ADS ok");
		}
		Log.v(TAG, "create all table success.");
		// 存放司机好友
		if (!tabbleIsExist("C_FRIEND")) {
			db.execSQL("CREATE TABLE C_FRIEND ( _ID INTEGER PRIMARY KEY autoincrement, NAME TEXT,"
					+ "PHONE TEXT,DENGJI TEXT,CARNO TEXT,SEX TEXT,COMPANY TEXT,SERVICENO TEXT);");
			Log.v(TAG, "Create Table C_ADS ok");
		}
		// 存放用户目的地
		if (!tabbleIsExist("DESTATION")) {
			db.execSQL("CREATE TABLE DESTATION ( _ID INTEGER PRIMARY KEY autoincrement, Des TEXT);");
			Log.v(TAG, "Create Table DESTATION ok");
		}
		Log.v(TAG, "create all table success.");

		// 存放车辆类型
		if (!tabbleIsExist("CARTYPE")) {
			db.execSQL("CREATE TABLE CARTYPE ( _ID INTEGER PRIMARY KEY autoincrement,carId,carType TEXT);");
			Log.v(TAG, "Create Table CARTYPE ok");
		}
	}

	public void close() {
		if (db.isOpen()) {
			db.close();
		}
	}

	public boolean deleteMyFavorite(String s) {
		String str = null;
		if (Utils.isEmpty(s, null)) {
			str = "DELETE FROM C_MyFavorites ";
		} else {
			str = (new StringBuilder("DELETE FROM C_MyFavorites WHERE _ID = "))
					.append(s).toString();
		}
		try {
			db.execSQL(str);
			Log.v("DBUtil", "DELETE Table C_FAVORITE ok");
			return true;
		} catch (Exception e) {
			Log.v("DBUtil", "DELETE Table C_MyFavorites err ,sql: " + str);
		}
		return false;
	}

	public boolean deleteUserInfo(String s) {
		String str = (new StringBuilder("DELETE FROM C_USERINFO WHERE _ID = "))
				.append(s).toString();
		try {
			db.execSQL(str);
			Log.v("DBUtil", "DELETE Table C_USERINFO ok");
			return true;
		} catch (Exception e) {
			Log.v("DBUtil", "DELETE Table C_USERINFO err ,sql: " + str);
		}
		return false;
	}

	public boolean deleteParamSet() {
		String s = "DELETE FROM C_PARAMSET ";
		try {
			db.execSQL(s);
			Log.v("DBUtil", "DELETE Table C_PARAMSET ok");
			return true;
		} catch (Exception e) {
			Log.v("DBUtil", "DELETE Table C_PARAMSET err ,sql: " + s);
		}
		return false;
	}

	public boolean deleteUserInfo() {
		String s = "DELETE FROM C_USERINFO ";
		try {
			db.execSQL(s);
			Log.v("DBUtil", "DELETE Table C_USERINFO ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("DBUtil", "DELETE Table C_USERINFO err ,sql: " + s);
		}
		return false;
	}

	public boolean deleteRecord(String s) {
		String str = null;
		if (Utils.isEmpty(s, null)) {
			str = "DELETE FROM C_RECORD ";
		} else {
			str = (new StringBuilder("DELETE FROM C_RECORD WHERE _ID = '"))
					.append(s).append("'").toString();
		}
		try {
			db.execSQL(str);
			Log.v("DBUtil", "DELETE Table C_RECORD ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.v(TAG, "DELETE Table C_RECORD err ,sql: " + str);
		}
		return false;
	}

	/**
	 * @方法描述:删除广告，包括全部和部分删除
	 * @class:DBUtil.java
	 * @param s
	 * @return:boolean
	 */
	public boolean deleteAds(String s) {
		String str = null;
		if (Utils.isEmpty(s, null)) {
			str = "DELETE FROM C_ADS ";
		} else {
			str = (new StringBuilder("DELETE FROM C_ADS WHERE _ID = '"))
					.append(s).append("'").toString();
		}
		try {
			db.execSQL(str);
			Log.v("DBUtil", "DELETE Table C_ADS ok");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Log.v(TAG, "DELETE Table C_ADS err ,sql: " + str);
		}
		return false;
	}

	/**
	 * @方法描述:删除广告，包括全部和部分删除
	 * @class:DBUtil.java
	 * @param s
	 * @return:boolean
	 */
	public boolean deleteAdsWithNum(String s) {
		String str = null;
		str = (new StringBuilder("DELETE FROM C_ADS WHERE NUM = '")).append(s)
				.append("'").toString();
		try {
			db.execSQL(str);
			Log.v("DBUtil", "DELETE Table C_ADS ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.v(TAG, "DELETE Table C_ADS err ,sql: " + str);
		}
		return false;
	}

	public boolean insertMyFavorite(String s, String s1, String s2, String s3,
			String s4, String s5, String s6) {
		// 判断记录大小
		Cursor cursor = loadMyFavorite("");
		if (cursor != null) {
			int j = cursor.getCount();
			if (j >= 15) {
				cursor.moveToFirst();
				deleteMyFavorite(cursor.getString(0));
			}
		}
		cursor.close();

		String s7 = (new StringBuilder(
				"insert into C_MyFavorites values(null,'")).append(s)
				.append("','").append(s1).append("','").append(s2)
				.append("','").append(s3).append("','").append(s4)
				.append("','").append(s5).append("','").append(s6).append("')")
				.toString();
		try {
			db.execSQL(s7);
			Log.v(TAG, "insert Table C_MyFavorites ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, "insert Table C_MyFavorites err ,sql: " + s7);
		}
		return false;
	}

	public boolean insertParamSet(String firstname, String Sex, String Phone,
			String OUT_NUM, String OUT_STATUS, String password, String email,
			String isremember, String isautologon, String deviceid,
			String secondname) {
		String s5 = (new StringBuilder("INSERT INTO C_PARAMSET VALUES(null,'"))
				.append(firstname).append("','").append(Sex).append("','")
				.append(Phone).append("','").append(OUT_NUM).append("','")
				.append(OUT_STATUS).append("','").append(password)
				.append("','").append(email).append("','").append(isremember)
				.append("','").append(isautologon).append("','")
				.append(deviceid).append("','").append(secondname).append("')")
				.toString();
		Cursor cursor = loadParamSet();
		if (cursor != null && cursor.getCount() > 0) {
			deleteUserInfo();
		}
		try {
			cursor.close();
			db.execSQL(s5);
			Log.v(TAG, "insert Table C_USERINFO ok");
			return true;
		} catch (Exception e) {
			Log.v(TAG, "insert Table C_USERINFO err ,sql: " + s5);
		}
		return false;
	}

	public boolean insertUserInfo(String sex, String phone, String deviceid) {
		String s5 = (new StringBuilder("INSERT INTO C_USERINFO VALUES(null,'"))
				.append(sex).append("','").append(phone).append("','")
				.append(deviceid).append("')").toString();
		Cursor cursor = loadUserInfo();
		if (cursor != null && cursor.getCount() > 0) {
			deleteUserInfo();
		}
		try {
			cursor.close();
			db.execSQL(s5);
			Log.v(TAG, "insert Table C_USERINFO ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, "insert Table C_USERINFO err ,sql: " + s5);
		}
		return false;
	}

	public boolean insertRecords(String s, String s1, byte s2, String s3,
			String s4, String s5, String s6, String s7, String s8, String s9,
			String s10, String s11, String s12, String s13, String s14,
			String s15, String s16, String s17, String s18, String s19,
			String s20, String s21, String s22, String s23, String s24,
			double s25, double s26) {
		Cursor cursor = loadRecord("");
		if (cursor != null) {
			int i = cursor.getCount();
			if (i >= 15) {
				cursor.moveToFirst();
				deleteRecord(cursor.getString(0));
			}
		}
		StringBuilder stringbuilder = (new StringBuilder(
				"INSERT INTO C_RECORD VALUES(null,'")).append(s).append("','")
				.append(s1).append("','").append(s2).append("','").append(s3)
				.append("','").append(s4).append("','").append(s5)
				.append("','").append(s6).append("','").append(s7)
				.append("','").append(s8).append("','").append(s9)
				.append("','").append(s10).append("','").append(s11)
				.append("','").append(s12).append("','").append(s13)
				.append("','").append(s14).append("','").append("0")
				.append("','").append(s15).append("','").append(s16)
				.append("','").append(s17).append("','").append(s18)
				.append("','").append(s19).append("','").append(s20)
				.append("','").append(s21).append("','").append(s22)
				.append("','").append(s23).append("','").append(s24)
				.append("','").append(s25).append("','").append(s26)
				.append("')");

		try {
			Log.v(TAG, "Sql:" + stringbuilder);
			db.execSQL(stringbuilder.toString());
			cursor.close();
			Log.v(TAG, "insert Table C_RECORD ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, "insert Table C_RECORD err ,sql: " + stringbuilder);
		}
		return false;
	}

	/**
	 * @方法描述:插入广告信息
	 * @class:DBUtil.java
	 * @param title
	 * @param content
	 * @return:boolean
	 */
	public boolean insertAds(String num, String content) {
		// 判断记录大小
		Cursor cursor = loadAds();
		if (cursor != null) {
			int j = cursor.getCount();
			if (j >= 15) {
				cursor.moveToFirst();
				deleteAds(cursor.getString(0));
			}
		}
		cursor.close();
		String str = (new StringBuilder("insert into C_ADS values(null,'"))
				.append(num).append("','").append(content).append("')")
				.toString();
		try {
			db.execSQL(str);
			Log.v(TAG, "insert Table C_ADS ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, "insert Table C_ADS err ,sql: " + str);
		}
		return false;
	}

	public Cursor loadMyFavorite(String s) {
		String as[] = null;
		Cursor cursor = null;
		// 查询全部
		if (Utils.isEmpty(s, null)) {
			Object obj = as;
			Object obj1 = as;
			Object obj2 = as;
			cursor = db.query("C_MyFavorites", as, ((String) (obj)), as,
					((String) (obj1)), ((String) (obj2)), "_ID Asc");
		} else {
			String s1 = (new StringBuilder("AType = '")).append(s).append("'")
					.toString();
			String as2[] = as;
			Object s2 = as;
			Object s3 = as;
			cursor = db.query("C_MyFavorites", as, s1, as2, (String) s2,
					(String) s3, "_ID Desc");
		}
		return cursor;
	}

	public Cursor loadParamSet() {
		String as[] = null;
		return db.query("C_PARAMSET", null, null, as, null, null, "_ID Desc");
	}

	public Cursor loadUserInfo() {
		String as[] = null;
		return db.query("C_USERINFO", null, null, as, null, null, "_ID Desc");
	}

	public Cursor loadAds() {
		String as[] = null;
		return db.query("C_ADS", null, null, as, null, null, "_ID Asc");
	}

	public Cursor loadNewFriend(String s) {
		String as[] = null;
		String where = "";
		if (s != null && !s.equals("")) {
			as = new String[] { s };
			where = "name like %?%";
		}
		return db.query("C_FRIEND", null, where, as, null, null, "_ID Desc");
	}
	public Cursor loadRecordByTaskId(String[] taskId){
		Cursor cursor = null;
		cursor = db.query("C_RECORD", null, "TASKID = ?", taskId, null, null, null);
		return cursor;
	}

	public Cursor loadRecord(String s) {
		String as[] = null;
		Cursor cursor = null;
		if (Utils.isEmpty(s, null)) {
			Object obj = as;
			Object obj1 = as;
			Log.i("as1", as + "===========");
			cursor = db.query("C_RECORD", as, ((String) (obj)), as,
					((String) (obj1)), ((String) (obj1)), "DATE Desc,TIME Desc");
		} else {
			String s1 = (new StringBuilder("AType = '")).append(s).append("'")
					.toString();
			Object s2 = as;
			Object s3 = as;
			cursor = db.query("C_RECORD", as, s1, as, (String) s2, (String) s3,
					"DATE Desc,TIME Desc");
		}
		Log.i("_ID", cursor.getColumnName(0) + "============ididid");
		return cursor;
	}

	public boolean updateMyFavorite(String s, String s1, String s2, String s3,
			String s4, String s5, String s6, String s7, String s8) {
		String s9 = (new StringBuilder("update C_MyFavorites set Name = '"))
				.append(s1).append("',Address = '").append(s3)
				.append("',X = '").append(s4).append("',Y = '").append(s5)
				.append("',Notes = '").append(s6).append("',Crossing = '")
				.append(s8).append("' where _ID=").append(s).toString();
		try {
			Log.v(TAG, "Sql:" + s9);
			db.execSQL(s9);
			Log.v(TAG, "update Table C_MyFavorites ok");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, "update Table C_MyFavorites err ,sql: " + s9);
		}
		return false;
	}

	/**
	 * 判断某张表是否存在
	 * 
	 * @param tabName
	 *            表名
	 * @return
	 */
	public boolean tabbleIsExist(String tableName) {
		if (tableName == null) {
			return false;
		}
		Cursor cursor = null;
		try {
			String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"
					+ tableName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, e.getMessage());
		} finally {
			cursor.close();
		}
		return false;
	}

	/**
	 * @方法描述:插入道路信息
	 * @class:DBUtil.java
	 * @param title
	 * @param content
	 * @return:boolean
	 */
	public void insertRoadBack(Map<Integer, String[]> map) {
		String str = null;
		int i = 0;
		while (i < map.size()) {
			Log.v(TAG, "i=" + String.valueOf(i));
			str = (new StringBuilder("INSERT INTO " + Constants.tableCrossRoad
					+ " VALUES(null,'")).append(map.get(i)[1]).append("','")
					.append(map.get(i)[0]).append("','").append(map.get(i)[2])
					.append("','").append(map.get(i)[3]).append("')")
					.toString();
			try {
				db.execSQL(str);
				Log.v(TAG, str + "insert into Table C_CROSSROAD ok");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.v(TAG, "insert Table C_CROSSROAD err ,sql: " + str);
			}
			i++;
		}
		// db.endTransaction();
	}

	/**
	 * @方法描述:根据一条路信息查询所有的交叉路口信息
	 * @class:DBUtil.java
	 * @param title
	 * @param content
	 * @return:boolean
	 */
	public Cursor getItemBytRoad(String strRoad) {
		String as[] = null;
		Cursor cursor = null;
		// 查询全部
		if (Utils.isEmpty(strRoad, null)) {
			Object obj = as;
			Object obj1 = as;
			Object obj2 = as;
			cursor = db.query(Constants.tableCrossRoad, as, ((String) (obj)),
					as, ((String) (obj1)), ((String) (obj2)), "_ID Asc");
		} else {
			String s1 = (new StringBuilder("ROAD = '")).append(strRoad)
					.append("' or CROSSROAD = '").append(strRoad).append("'")
					.toString();
			String as2[] = as;
			Object s2 = as;
			Object s3 = as;
			cursor = db.query(Constants.tableCrossRoad, as, s1, as2,
					(String) s2, (String) s3, "_ID Desc");
		}
		return cursor;
	}

	/**
	 * 更新用户表信息
	 */
	public void updateUserInfo(String sex, String phoneNumber) {
		String s9 = (new StringBuilder("update C_USERINFO set SEX = '"))
				.append(sex).append("',PHONE = '").append(phoneNumber)
				.append("'").toString();
		try {
			Log.v(TAG, "Sql:" + s9);
			db.execSQL(s9);
			Log.v(TAG, "update Table C_USERINFO ok");
		} catch (Exception e) {
			Log.v(TAG, "update Table C_USERINFO err ,sql: " + s9);
		}
	}

	/**
	 * 判断是不是存在好友了，如果存在好友的话，就弹出是否添加好友
	 */
	public boolean isExisFriend(String serviceNo) {
		String sql = "select * from C_FRIEND where SERVICENO =" + serviceNo;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null && cursor.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 插入司机好友
	 * 
	 * @param name
	 *            姓名
	 * @param shoujihao
	 *            手机号码
	 * @param level
	 *            等级
	 * @param bodacishu
	 *            拨打次数
	 */
	public void insertNewFriend(String name, String shoujihao, String level,
			String bodacishu) {
		String s5 = (new StringBuilder("INSERT INTO C_FRIEND VALUES(null,'"))
				.append(name).append("','").append(shoujihao).append("','")
				.append(level).append("','").append(bodacishu).append("')")
				.toString();
		try {
			db.execSQL(s5);
			Log.v(TAG, "insert Table C_FRIEND ok");
		} catch (Exception e) {
			Log.v(TAG, "insert Table C_FRIEND err ,sql: " + s5);
		}
	}

	// 注册时 保存司机信息为司机好友
	public void insertDriver(Driver driver) {
		String sex = "";
		if (driver.getDriversex() == 0) {
			sex = "男";
		} else {
			sex = "女";
		}
		String sql = (new StringBuilder("INSERT INTO C_FRIEND VALUES(null,'"))
				.append(driver.getName()).append("','")
				.append(driver.getTelphone()).append("','").append(0)
				.append("','").append(driver.getCarNo()).append("','")
				.append(sex).append("','").append(driver.getSuozaigongsi())
				.append("','").append(driver.getServiceNo()).append("')")
				.toString();
		db.execSQL(sql);
	}

	/**
	 * 增加拨打次数
	 */
	public void updateCount(String phonenumber) {
		String sql = "update C_FRIEND set CALLNUMBER=(select CALLNUMBER from C_FRIEND where PHONE"
				+ phonenumber + "+1)  where PHONE=" + phonenumber;
		try {
			db.execSQL(sql);
			Log.v(TAG, "update  count c_friend ok");
		} catch (Exception e) {
			Log.v(TAG, "update c_friend is err");
		}
	}

	/**
	 * 
	 * s 主键Id s1 要更新为的值
	 * 
	 * @return
	 */
	public void updateRecord(String s, String s1) {
		String s9 = (new StringBuilder("update C_RECORD set APPRAISE = '"))
				.append(s1).append("' where _ID=").append(s).toString();
		try {
			Log.v(TAG, "Sql:" + s9);
			db.execSQL(s9);
			Log.v(TAG, "update Table C_RECORD ok");
		} catch (Exception e) {
			// TODO: handle exception
			Log.v(TAG, "update Table C_RECORD err ,sql: " + s9);
		}
	}

	/**
	 * 插入目的地
	 */
	public void insertIntoDestion(String destation) {
		String str = (new StringBuilder("insert into DESTATION values(null,'"))
				.append(destation).append("')").toString();
		try {
			db.execSQL(str);
			Log.v(TAG, "insert  DESTATION is ok");
		} catch (Exception e) {
			Log.v(TAG, "insert DESTATION is err");
		}
	}

	/**
	 * 插入车辆类型
	 */
	public void insertIntoCartype(Cartypes cartypes) {
		if (cartypes != null) {
			String str = (new StringBuilder("insert into CARTYPE values(null,'"))
					.append(cartypes.getValue()).append("','")
					.append(cartypes.getName()).append("')").toString();
			try {
				db.execSQL(str);
				Log.v(TAG, "insert  DESTATION is ok");
			} catch (Exception e) {
				Log.v(TAG, "insert DESTATION is err");
			}
		}

	}

	/**
	 * 获取目的地,只显示最近的5个
	 */
	public Cursor getDesation() {
		String as[] = null;
		return db.query("DESTATION", null, null, as, null, null, "_ID Desc");
	}

	/**
	 * 获取车辆类型
	 */
	public Cursor getCarType() {
		String as[] = null;
		return db.query("CARTYPE", null, null, as, null, null, "");
	}
	/**
	 * 根据编码获取车型
	 */
	public Cursor loadCarType(String [] code){
		return db.query("CARTYPE", null, "carId = ?", code , null, null, "_ID Desc");
	}
	/**
	 * 根据车型获取编码
	 */
	public Cursor loadCarCode(String [] type){
		return db.query("CARTYPE", null, "cartype = ?", type , null, null, "_ID Desc");
	}

	/**
	 * 判断存不存在订单
	 * false 为有
	 * true 为没有
	 */
	public boolean findTaskId(String[] takID) {
		Cursor cursor = db.query("C_RECORD", null, "TASKID = ?",takID , null, null, "_ID Desc");
		Log.i("查数据库", cursor.getCount()+"========================");
		if (cursor != null && cursor.getCount() > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 删除车辆类型表
	 */
	public boolean deletecarType() {
		String str = (new StringBuilder("DELETE FROM CARTYPE").toString());
		try {
			db.execSQL(str);
			Log.v("DBUtil", "DELETE Table CARTYPE ok");
			return true;
		} catch (Exception e) {
			Log.v("DBUtil", "DELETE Table CARTYPE err ,sql: " + str);
		}
		return false;
	}
}

package com.test.car.activitys;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.test.utils.Utils;

public class MyApplication extends Application{
	static MyApplication myApplication;
	BMapManager mBMapManager = null;
	
	boolean m_bKeyRight = true; 
	private List<Activity> activityList = new LinkedList<Activity>();
	
	
	
	@Override
	public void onCreate() {
		myApplication = this;
		initEngineManager(this);
		super.onCreate();
		
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
			Toast.makeText(MyApplication.getInstance().getApplicationContext(),
					"BMapManager SUCSUC!", Toast.LENGTH_LONG).show();
		}

		if (!mBMapManager.init(Utils.BAIDU_MAP_KEY, new MyGeneralListener())) {
			Toast.makeText(MyApplication.getInstance().getApplicationContext(),
					"BMapManager ", Toast.LENGTH_LONG).show();
		}
	}

	public static MyApplication getInstance() {
		if (myApplication == null) {
			myApplication = new MyApplication();
		}
		return myApplication;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

		System.exit(0);

	}

	static class MyGeneralListener implements MKGeneralListener {

		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						MyApplication.getInstance().getApplicationContext(),
						"错误", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						MyApplication.getInstance().getApplicationContext(),
						"错误", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				Toast.makeText(
						MyApplication.getInstance().getApplicationContext(),
						"请输入KEY",
						Toast.LENGTH_LONG).show();
				MyApplication.getInstance().m_bKeyRight = false;
			}
		}

	}
}

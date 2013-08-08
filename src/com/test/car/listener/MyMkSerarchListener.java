package com.test.car.listener;

import android.app.Activity;

import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.test.car.activitys.MyMapActivity;

public class MyMkSerarchListener implements MKSearchListener {

	private Activity activity;

	public MyMkSerarchListener(Activity activity) {
		this.activity = activity;
	}

	public void onGetAddrResult(MKAddrInfo info, int arg1) {
		//MyMapActivity.txt_show_addr.setText(info.strAddr);
		MyMapActivity.edt_userpoint.setText(info.strAddr);
	}

	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetPoiDetailSearchResult(int arg0, int arg1) {
		
	}

	public void onGetPoiResult(MKPoiResult res, int type, int error) {

	}

	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {

	}

}

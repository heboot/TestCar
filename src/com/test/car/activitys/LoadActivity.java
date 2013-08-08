package com.test.car.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import com.test.utils.Constants;
import com.test.utils.DBUtil;
import com.test.utils.SPUtil;
import com.test.utils.Utils;

public class LoadActivity extends PublicActivity implements Runnable{

	DBUtil db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		uninstallOldVersion();
		initView();
		new Thread(this).start();
	}
	
	private void initView(){
		if(!Utils.isConnectToInternet((ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE))){
			this.showMydialog(this, "没有联网",this);
		}
		db = new DBUtil(this);
		if(Utils.getIMSI(this) == null){
			this.showMydialog(this, "插入SIM卡",this);
		}
		Constants.setIMSI(Utils.getIMSI(this));
	}
	
	
	
	private void uninstallOldVersion(){
		//这里进行卸载提示
		 PackageInfo packageInfo;
	        try {
	            packageInfo = this.getPackageManager().getPackageInfo(
	                    "com.dz.cocas", 0);
	        } catch (NameNotFoundException e) {
	            packageInfo = null;
	            e.printStackTrace();
	        }
	     if(packageInfo !=null){
	    	 new AlertDialog.Builder(LoadActivity.this)
				.setMessage("尊敬的用户，系统检测到您已安装了旧版的手机扬招，但是因为证书过期，需要卸载！")
				.setPositiveButton(
						"确定",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Uri packageURI = Uri.parse("package:com.dz.cocas");         
								Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);         
								startActivity(uninstallIntent);
							}
						})
				.show();
	    	 return;
	      }
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			SPUtil spUtil = new SPUtil(this);
			boolean noFirst = spUtil.getValue("noFirst", false);
			if (noFirst) {
				Intent intent0 = new Intent(LoadActivity.this,
						MyMapActivity.class);
				LoadActivity.this.startActivity(intent0);
				LoadActivity.this.finish();
			} else {
				Intent intent0 = new Intent(LoadActivity.this,
						WelcomeActivity.class);
				LoadActivity.this.startActivity(intent0);
				LoadActivity.this.finish();
			}
			spUtil.setValue("noFirst", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

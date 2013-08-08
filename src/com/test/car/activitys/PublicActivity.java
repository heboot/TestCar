package com.test.car.activitys;


import com.test.car.interfaces.TitleBackInter;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

public class PublicActivity extends Activity implements TitleBackInter{
	
	public void showMydialog(Context contextMed, String msg) {
		android.app.AlertDialog.Builder dialog = new Builder(contextMed);
		dialog.setTitle("提示信息");
		dialog.setPositiveButton("确定", new OnClickListener() {
			
			public void onClick(DialogInterface dialoginterface, int which) {
				dialoginterface.dismiss();
			}
		});
		dialog.setMessage(msg);
		dialog.show();
	}
	public void showMydialog(Context contextMed, String msg,final Activity activity) {
		android.app.AlertDialog.Builder dialog = new Builder(contextMed);
		dialog.setTitle("提示信息");
		dialog.setPositiveButton("确定", new OnClickListener() {
			
			public void onClick(DialogInterface dialoginterface, int which) {
				activity.finish();
			}
		});
		dialog.setMessage(msg);
		dialog.show();
	}
	public void SendInformatToNext(Context contextPublic, Class<?> classCon) {
		Intent intent = new Intent();
		intent.setClass(contextPublic, classCon);
		startActivity(intent);
	}
	@Override
	public void titleBack(View v) {
		onBackPressed();
	}
	
}

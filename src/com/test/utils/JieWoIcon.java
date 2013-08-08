package com.test.utils;



import com.test.car.activitys.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;




public class JieWoIcon extends View{
	public static int w;
	public static int h;
	private Bitmap mBitmap;

	public JieWoIcon(Context context) {
		super(context);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jiewo);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		w = this.getWidth() / 2 - mBitmap.getWidth() / 2;
		h = (this.getHeight() / 2 - mBitmap.getHeight() / 2) + (	MeIcon.mBitmap.getHeight()/2);
		canvas.drawBitmap(mBitmap, w, h, null);
	}
}

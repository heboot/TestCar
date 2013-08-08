package com.test.car.activitys;

import java.util.ArrayList;

import com.test.car.adapter.MyPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


public class WelcomeActivity extends Activity {

	// 翻页控件
	private ViewPager mViewPager;

	// 这5个是底部显示当前状态点imageview
	private ImageView mPage0;
	private ImageView mPage1;
	private ImageView mPage2;

	// private ImageView mPage3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		MyApplication.getInstance().addActivity(this);
		mViewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		mPage0 = (ImageView) findViewById(R.id.page0);
		mPage1 = (ImageView) findViewById(R.id.page1);
		mPage2 = (ImageView) findViewById(R.id.page2);
		// mPage3 = (ImageView) findViewById(R.id.page3);

		/*
		 * 这里是每一页要显示的布局，根据应用需要和特点自由设计显示的内容 以及需要显示多少页等
		 */
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.welcome_one, null);
		View view2 = mLi.inflate(R.layout.welcome_two, null);
		View view3 = mLi.inflate(R.layout.welcome_three, null);

		/*
		 * 这里将每一页显示的view存放到ArrayList集合中 可以在ViewPager适配器中顺序调用展示
		 */
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		/*
		 * 每个页面的Title数据存放到ArrayList集合中 可以在ViewPager适配器中调用展示
		 */
		final ArrayList<String> titles = new ArrayList<String>();
		titles.add("tab1");
		titles.add("tab2");
		titles.add("tab3");

		// 填充ViewPager的数据适配器
		MyPagerAdapter mPagerAdapter = new MyPagerAdapter(views, titles);
		mViewPager.setAdapter(mPagerAdapter);

		// startBtn = (Button)findViewById(R.id.startBtn);
		// startBtn.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent intent0 = new Intent(WelcomeActivity.this,
		// LogonActivity.class);
		// // intent0.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		// WelcomeActivity.this.startActivity(intent0);
		// WelcomeActivity.this.finish();
		// }
		// });

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		public void onPageSelected(int page) {

			// 翻页时当前page,改变当前状态园点图片
			switch (page) {
			case 0:
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				// mPage3.setImageDrawable(getResources().getDrawable(
				// R.drawable.page));
				break;
			// case 3:
			// mPage3.setImageDrawable(getResources().getDrawable(
			// R.drawable.page_now));
			// //
			// mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
			// mPage2.setImageDrawable(getResources().getDrawable(
			// R.drawable.page));
			// break;
			}
		}

		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}
	}

	public void start(View view) {
		Intent intent0 = new Intent(WelcomeActivity.this, MyMapActivity.class);
		// intent0.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		WelcomeActivity.this.startActivity(intent0);
		WelcomeActivity.this.finish();
	}

}

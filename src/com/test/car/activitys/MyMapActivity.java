package com.test.car.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.test.car.listener.MyMkSerarchListener;
import com.test.utils.JieWoIcon;
import com.test.utils.MeIcon;
import com.test.utils.MyMapView;
import com.test.utils.SlidingLayout;

public class MyMapActivity extends PublicActivity {

	public static MyMapView mMapView = null;
	public MyApplication app;
	
	public MKMapViewListener mMapListener = null;
	MyLocationOverlay myLocationOverlay = null;
	
	public MyLocationListenner myListener = new MyLocationListenner();
	LocationData locData = null;
	private MapController mMapController = null;

	public static MKSearch mkSearch;
	public static GeoPoint currentGeoPoint;
	
	public static Handler mHandler = new Handler();
	public static LocationClient mLocClient;
	
	public LinearLayout layout_bottommenu;
	/**
	 * 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏。
	 */
	private SlidingLayout slidingLayout;

	/**
	 * menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局。
	 */
	private Button menuButton,btn_order_record,btn_mylocation;
	public static EditText edt_userpoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		menuButton = (Button) findViewById(R.id.btn_title_menu);
		//contentListView = (ListView) findViewById(R.id.contentList);
		//contentListView.setAdapter(contentListAdapter);
		// 将监听滑动事件绑定在contentListView上
		//slidingLayout.setScrollEvent(contentListView);
		menuButton.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实现点击一下menu展示左侧布局，再点击一下隐藏左侧布局的功能
				if (slidingLayout.isLeftLayoutVisible()) {
					slidingLayout.scrollToRightLayout();
					layout_bottommenu.setVisibility(View.VISIBLE);
				} else {
					slidingLayout.scrollToLeftLayout();
					layout_bottommenu.setVisibility(View.GONE);
				}
			}
		});
		initView();
		initIcon();
		initMap();
		
	}
	
	
	private void initView(){
		edt_userpoint = (EditText)findViewById(R.id.edt_userpoint);
		layout_bottommenu = (LinearLayout)findViewById(R.id.layout_bottommenu);
		btn_order_record = (Button)findViewById(R.id.btn_order_record);
		btn_mylocation = (Button)findViewById(R.id.btn_mylocation);
		btn_order_record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SendInformatToNext(MyMapActivity.this, OrderRecordActivity.class);
			}
		});
		btn_mylocation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mLocClient.requestLocation();
			}
		});
	}
	
	private void initIcon(){
		MeIcon me = new MeIcon(this);
		getWindow().addContentView(
				me,
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));
		JieWoIcon jiewo = new JieWoIcon(this);
		getWindow().addContentView(
				jiewo,
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));
	}
	
	private void initMap(){
		mMapView = (MyMapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();

		app = MyApplication.getInstance();
		mkSearch = new MKSearch();
		mkSearch.init(app.mBMapManager, new MyMkSerarchListener(this));
		mLocClient = new LocationClient(getApplicationContext());
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setCoorType("bd09ll");
		option.setScanSpan(222220000);
		mLocClient.setLocOption(option);
		mLocClient.start();
		mMapView.getController().setZoom(17);
		mMapView.getController().enableClick(true);
		mMapView.setBuiltInZoomControls(true);
		mMapView.regMapViewListener(MyApplication.getInstance().mBMapManager,
				mMapListener);
		myLocationOverlay = new MyLocationOverlay(mMapView);
		locData = new LocationData();
		myLocationOverlay.setData(locData);
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mMapView.refresh();
	}
	
	public class MyLocationListenner implements BDLocationListener {
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			locData.direction = 2.0f;
			locData.accuracy = location.getRadius();
			locData.direction = location.getDerect();
			Log.d("loctest",
					String.format("before: lat: %f lon: %f",
							location.getLatitude(), location.getLongitude()));
			myLocationOverlay.setData(locData);
			mMapView.refresh();
			currentGeoPoint = new GeoPoint((int) (locData.latitude * 1e6),
					(int) (locData.longitude * 1e6));
			getPosition(currentGeoPoint);
			mMapController
					.animateTo(currentGeoPoint, mHandler
							.obtainMessage(1));
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}
	public static void getPosition(GeoPoint g) {
		mkSearch.reverseGeocode(g);
	}

}

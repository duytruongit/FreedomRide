package org.thyee.freedomride.client.view;

import java.util.ArrayList;
import java.util.List;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.StrategyDetailListAdapter;
import org.thyee.freedomride.client.entity.Attractions;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyItem;
import org.thyee.freedomride.client.task.AttractionsTask;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.view.base.AddStrategyOverItem;
import org.thyee.freedomride.client.view.base.OverItemT;
import org.thyee.freedomride.client.view.base.PageActivity;
import org.thyee.freedomride.client.view.base.ScrollLayout;
import org.thyee.freedomride.client.view.base.ScrollLayout.OnScrollSideChangedListener;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKOfflineMap;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapActivity extends PageActivity {
	BMapManager mBMapMan = null;
	public MapView mMapView = null;
	MKSearch mMKSearch = null;
	MKOfflineMap mOffline = null; // 申明变量
	private Strategy strategy;
	ScrollLayout mScrollLayout;
	public StrategyDetailListAdapter addListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(Data.MAP_KEY, null);
		setContentView(R.layout.add_strategy);
		// setContentView(R.layout.activity_main);

		final LinearLayout right = (LinearLayout) findViewById(R.id.right_menu_2);
		right.setVisibility(View.VISIBLE);
		mMapView = (MapView) findViewById(R.id.add_mapView);
		mMapView.setBuiltInZoomControls(true);
		mScrollLayout = (ScrollLayout) findViewById(R.id.main_layout);
		// mScrollLayout.scrollToScreen(2);

		mScrollLayout
				.setScrollSideChangedListener(new OnScrollSideChangedListener() {
					@Override
					public void onScrollSideChanged(View v, boolean rightSide) {

					}
				});
		ImageButton imageButton = (ImageButton) findViewById(R.id.map_detail);
		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mScrollLayout.isShowRight()) {
					mScrollLayout.scrollToScreen(1);
					mScrollLayout.setShowRight(false);
				} else {
					mScrollLayout.scrollToScreen(2);
					mScrollLayout.setShowRight(true);
				}
			}
		});

		// 设置启用内置的缩放控件
		MapController mMapController = mMapView.getController();
		GeoPoint point = new GeoPoint((int) (23.139 * 1E6),
				(int) (113.269 * 1E6));
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别

		Intent intent = getIntent();

		String city = intent.getStringExtra("city");
		String type = intent.getStringExtra("type");
		String name = intent.getStringExtra("name");
		TextView nameTextView = (TextView) findViewById(R.id.add_strategy_name);
		nameTextView.setText(name);
		strategy = new Strategy();
		strategy.setType(type);
		strategy.setName(name);
		if ("全部".equals(type)) {
			strategy.setType("其他");
		}
		List<StrategyItem> strategyItems = new ArrayList<StrategyItem>();
		strategy.setStrategyItems(strategyItems);
		strategy.setName(name);
		strategy.setState(0);

		AttractionsTask aTask = new AttractionsTask(this, city, type);
		aTask.start();

		ListView addList = (ListView) findViewById(R.id.add_listView);
		addListAdapter = new StrategyDetailListAdapter(this, strategy);
		addList.setAdapter(addListAdapter);

		ImageButton saveButton = (ImageButton) findViewById(R.id.strategy_save);
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent data = new Intent();
				ObjectMapper objectMapper = new ObjectMapper();
				String strategyString;
				try {
					strategyString = objectMapper.writeValueAsString(strategy);
					data.putExtra(Data.RESULT_DATA, strategyString);
				} catch (Exception e) {
					e.printStackTrace();
				}
				setResult(2, data);
				finish();
			}
		});

	}

	// public void update(List<Attractions> list) {
	// Drawable marker = getResources().getDrawable(R.drawable.pin); //
	// 得到需要标在地图上的资源
	// // OverItemT overItemT = new AddStrategyOverItem(marker, this, list);
	// GeoPoint geoPoint = null;
	// AttrOverlay attrOverlay = new AttrOverlay(mMapView, this);
	// ArrayList<MKPoiInfo> mkPoiInfos = new ArrayList<MKPoiInfo>();
	// for (Attractions attractions : list) {
	// // GeoPoint gp = new GeoPoint((int) (23.146868 * 1E6),
	// // (int) (113.311688 * 1E6));
	// MKPoiInfo mkPoiInfo = new MKPoiInfo();
	// int x = (int) (attractions.getX() * 1E6);
	// int y = (int) (attractions.getY() * 1E6);
	// GeoPoint gp = new GeoPoint(y, x);
	// geoPoint = gp;
	// mkPoiInfo.ePoiType = 1;
	// mkPoiInfo.pt = gp;
	// mkPoiInfo.name = attractions.getName();
	// mkPoiInfo.address = attractions.getAddress();
	// mkPoiInfo.city = attractions.getCity();
	// mkPoiInfos.add(mkPoiInfo);
	// }
	// attrOverlay.setData(mkPoiInfos);
	// mMapView.getOverlays().clear();
	// mMapView.getOverlays().add(attrOverlay); // 添加ItemizedOverlay实例到mMapView
	// mMapView.refresh();// 刷新地图
	// mMapView.getController().animateTo(geoPoint);
	// }
	public void update(List<Attractions> list) {
		Drawable marker = getResources().getDrawable(R.drawable.pin); // 得到需要标在地图上的资源
		OverItemT overItemT = new AddStrategyOverItem(marker, this, list);
		GeoPoint geoPoint = null;
		for (Attractions attractions : list) {
			// GeoPoint gp = new GeoPoint((int) (23.146868 * 1E6),
			// (int) (113.311688 * 1E6));
			int x = (int) (attractions.getX() * 1E6);
			int y = (int) (attractions.getY() * 1E6);
			GeoPoint gp = new GeoPoint(y, x);
			geoPoint = gp;
			System.out.println(gp);
			overItemT
					.addItems(gp, attractions.getName(), attractions.getName());
		}
		overItemT.reflash();
		mMapView.getOverlays().add(overItemT); // 添加ItemizedOverlay实例到mMapView
		mMapView.refresh();// 刷新地图
		mMapView.getController().animateTo(geoPoint);
	}
}

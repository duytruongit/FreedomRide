package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.StrategyDetailListAdapter;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyItem;
import org.thyee.freedomride.client.listener.StrategySearchListener;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.view.base.OverItemT;
import org.thyee.freedomride.client.view.base.PageActivity;
import org.thyee.freedomride.client.view.base.ScrollLayout;
import org.thyee.freedomride.client.view.base.ScrollLayout.OnScrollSideChangedListener;
import org.thyee.freedomride.client.view.base.StrategyOverItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKOfflineMap;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class StrategyActivity extends PageActivity implements
		OnItemClickListener {

	TextView lengthView;
	TextView cityView;
	TextView typeView;
	ImageButton searchButton;
	private StrategySearchListener strategySearchListener;
	BMapManager mBMapMan = null;
	MapView mMapView = null;
	MKSearch mMKSearch = null;
	MKOfflineMap mOffline = null; // 申明变量
	ScrollLayout mScrollLayout;
	StrategyDetailListAdapter detailAdapter;
	View progressBar;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initMapMan();
		setContentView(R.layout.strategy);
		initMap();

		if (strategySearchListener == null) {
			strategySearchListener = new StrategySearchListener(this);
		}

		lengthView = (TextView) findViewById(R.id.strategy_length);
		lengthView.setOnClickListener(strategySearchListener);
		cityView = (TextView) findViewById(R.id.choose_city);
		cityView.setOnClickListener(strategySearchListener);
		typeView = (TextView) findViewById(R.id.choose_type);
		typeView.setOnClickListener(strategySearchListener);
		searchButton = (ImageButton) findViewById(R.id.strategy_search);
		searchButton.setOnClickListener(strategySearchListener);

		progressBar = (View) findViewById(R.id.refresh_bar);

		final LinearLayout right = (LinearLayout) findViewById(R.id.right_detail);
		right.setVisibility(View.VISIBLE);

		mScrollLayout = (ScrollLayout) findViewById(R.id.main_layout);
		mScrollLayout
				.setScrollSideChangedListener(new OnScrollSideChangedListener() {
					@Override
					public void onScrollSideChanged(View v, boolean rightSide) {

					}
				});
		ImageButton imageButton = (ImageButton) findViewById(R.id.strategy_detail);
		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mScrollLayout.isShowRight()) {
					mScrollLayout.scrollToScreen(1);
					mScrollLayout.setShowRight(false);
				} else {
					mScrollLayout.scrollToScreen(2);
					mScrollLayout.setShowRight(true);
				}
			}
		});
		ListView addList = (ListView) findViewById(R.id.detail_listView);
		detailAdapter = new StrategyDetailListAdapter(this, null);
		addList.setAdapter(detailAdapter);
		addList.setOnItemClickListener(this);

		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				detailAdapter.notifyDataSetChanged();
				Toast.makeText(StrategyActivity.this, "加载完成",
						Toast.LENGTH_SHORT).show();
				searchButton.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			};
		};
	}

	private void initMapMan() {
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(Data.MAP_KEY, null);
	}

	private void initMap() {
		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错
		mMapView = (MapView) findViewById(R.id.strategy_map_view);
		mMapView.setBuiltInZoomControls(true);
		// 设置启用内置的缩放控件
		MapController mMapController = mMapView.getController();
		GeoPoint point = new GeoPoint((int) (23.139 * 1E6),
				(int) (113.269 * 1E6));
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别
	}

	public void startSearch() {
		progressBar.setVisibility(View.VISIBLE);
		searchButton.setVisibility(View.GONE);
	}

	public void update(Strategy strategy) {
		Drawable marker = getResources().getDrawable(R.drawable.pin); // 得到需要标在地图上的资源

		GeoPoint geoPoint = null;
		if (strategy != null) {

			OverItemT overItemT = new StrategyOverItem(marker, this,
					strategy.getStrategyItems());
			for (StrategyItem strategyItem : strategy.getStrategyItems()) {
				int x;
				int y;
				String name = "";
				if (strategyItem.getAttractions() != null) {
					x = (int) (strategyItem.getAttractions().getX() * 1E6);
					y = (int) (strategyItem.getAttractions().getY() * 1E6);
					name = strategyItem.getAttractions().getName();
				} else if (strategyItem.getRestaurant() != null) {
					x = (int) (strategyItem.getRestaurant().getX() * 1E6);
					y = (int) (strategyItem.getRestaurant().getY() * 1E6);
					name = strategyItem.getRestaurant().getName();
				} else {
					x = (int) (strategyItem.getHotel().getX() * 1E6);
					y = (int) (strategyItem.getHotel().getY() * 1E6);
					name = strategyItem.getHotel().getName();
				}
				GeoPoint gp = new GeoPoint(y, x);
				geoPoint = gp;
				overItemT.addItems(gp, name, name);
			}
			overItemT.reflash();
			mMapView.getOverlays().clear();
			mMapView.getOverlays().add(overItemT); // 添加ItemizedOverlay实例到mMapView
		}
		mMapView.refresh();// 刷新地图
		mMapView.getController().animateTo(geoPoint);
		detailAdapter.setStrategy(strategy);
		handler.sendEmptyMessage(0);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mScrollLayout.scrollToScreen(1);
		mScrollLayout.setShowRight(false);
		StrategyItem strategyItem = detailAdapter.sItems.get(position);
		int x;
		int y;
		String name = "";
		if (strategyItem.getAttractions() != null) {
			x = (int) (strategyItem.getAttractions().getX() * 1E6);
			y = (int) (strategyItem.getAttractions().getY() * 1E6);
			name = strategyItem.getAttractions().getName();
		} else if (strategyItem.getRestaurant() != null) {
			x = (int) (strategyItem.getRestaurant().getX() * 1E6);
			y = (int) (strategyItem.getRestaurant().getY() * 1E6);
			name = strategyItem.getRestaurant().getName();
		} else {
			x = (int) (strategyItem.getHotel().getX() * 1E6);
			y = (int) (strategyItem.getHotel().getY() * 1E6);
			name = strategyItem.getHotel().getName();
		}
		GeoPoint gp = new GeoPoint(y, x);
		mMapView.getController().animateTo(gp);
	}
}

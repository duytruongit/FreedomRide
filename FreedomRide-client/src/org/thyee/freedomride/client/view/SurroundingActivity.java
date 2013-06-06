package org.thyee.freedomride.client.view;

import java.util.ArrayList;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.listener.MapSearchListener;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.view.base.PageActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKOfflineMap;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem.RadialMenuItemClickListener;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;

public class SurroundingActivity extends PageActivity {

	BMapManager mBMapMan = null;
	MapView mMapView = null;
	MKSearch mMKSearch = null;
	MKOfflineMap mOffline = null; // 申明变量
	GeoPoint geoPoint = new GeoPoint((int) (23.139 * 1E6),
			(int) (113.269 * 1E6));
	private RadialMenuWidget locationMenu;
	public RadialMenuItem attractionItem, menuCloseItem, restaurantItem,
			hotelItem, suppermarketItem;

	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		if (location != null) {
			LogUtils.log(location.getLongitude() + "," + location.getLatitude());
		}

		initMap();
		createRadialMenuWidget();

		ImageButton imageButton = (ImageButton) findViewById(R.id.surrounding_loaction);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MapController mMapController = mMapView.getController();
				geoPoint = new GeoPoint((int) (23.041614 * 1E6),
						(int) (113.405514 * 1E6));
				mMapController.animateTo(geoPoint);

				locationMenu.show(v);
			}
		});

	}

	@Override
	protected void onDestroy() {
		if (mMapView != null) {
			mMapView.destroy();
		}
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mMapView != null) {
			mMapView.onPause();
		}
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mMapView != null) {
			mMapView.onResume();
		}
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

	private void initMap() {
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(Data.MAP_KEY, null);
		setContentView(R.layout.surrounding);
		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错
		mMapView = (MapView) findViewById(R.id.surrounding_map_view);
		mMapView.setBuiltInZoomControls(true);
		// 设置启用内置的缩放控件
		MapController mMapController = mMapView.getController();
		GeoPoint point = new GeoPoint((int) (23.041614 * 1E6),
				(int) (113.405514 * 1E6));
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别
	}

	private void mapSearch(GeoPoint geoPoint, String keyword) {
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MapSearchListener(this, mMapView));
		mMKSearch.poiSearchNearBy(keyword, geoPoint, 5000);
	}

	private void createRadialMenuWidget() {
		locationMenu = new RadialMenuWidget(this);
		menuCloseItem = new RadialMenuItem(getString(R.string.ok), null);
		menuCloseItem
				.setDisplayIcon(android.R.drawable.ic_menu_close_clear_cancel);
		attractionItem = new RadialMenuItem(getString(R.string.attraction),
				getString(R.string.attraction));
		hotelItem = new RadialMenuItem(getString(R.string.hotel),
				getString(R.string.hotel));
		restaurantItem = new RadialMenuItem(getString(R.string.restaurant),
				getString(R.string.restaurant));
		suppermarketItem = new RadialMenuItem(
				getString(R.string.suppermarcket),
				getString(R.string.suppermarcket));
		menuCloseItem
				.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
					@Override
					public void execute() {
						locationMenu.dismiss();
					}
				});
		locationMenu.setAnimationSpeed(0L);
		locationMenu.setSourceLocation(200, 200);
		locationMenu.setIconSize(15, 30);
		locationMenu.setTextSize(13);
		locationMenu.setOutlineColor(
				getResources().getColor(R.color.dack_blue), 225);
		locationMenu.setInnerRingColor(
				getResources().getColor(R.color.dack_blue), 180);
		locationMenu.setOuterRingColor(0x0099CC, 180);
		locationMenu.setCenterCircle(menuCloseItem);

		locationMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {
			{
				add(attractionItem);
				add(restaurantItem);
				add(hotelItem);
				add(suppermarketItem);
			}
		});

		attractionItem.setOnMenuItemPressed(new RadialMenuItemClickListener() {
			@Override
			public void execute() {
				mapSearch(geoPoint, SurroundingActivity.this.getResources()
						.getString(R.string.attraction));
				locationMenu.dismiss();
			}
		});
		restaurantItem.setOnMenuItemPressed(new RadialMenuItemClickListener() {
			@Override
			public void execute() {
				mapSearch(geoPoint, SurroundingActivity.this.getResources()
						.getString(R.string.restaurant));
				locationMenu.dismiss();
			}
		});
		hotelItem.setOnMenuItemPressed(new RadialMenuItemClickListener() {
			@Override
			public void execute() {
				mapSearch(geoPoint, SurroundingActivity.this.getResources()
						.getString(R.string.hotel));
				locationMenu.dismiss();
			}
		});
		suppermarketItem
				.setOnMenuItemPressed(new RadialMenuItemClickListener() {
					@Override
					public void execute() {
						mapSearch(geoPoint,
								SurroundingActivity.this.getResources()
										.getString(R.string.suppermarcket));
						locationMenu.dismiss();
					}
				});
	}

}

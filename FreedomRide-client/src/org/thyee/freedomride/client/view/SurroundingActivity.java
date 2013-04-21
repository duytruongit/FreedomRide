package org.thyee.freedomride.client.view;

import java.util.ArrayList;
import java.util.List;

import org.thyee.freedomride.client.R;

import android.os.Bundle;
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
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;

public class SurroundingActivity extends PageActivity {

	BMapManager mBMapMan = null;
	MapView mMapView = null;
	MKSearch mMKSearch = null;
	MKOfflineMap mOffline = null; // 申明变量
	private RadialMenuWidget pieMenu;
	public RadialMenuItem menuItem, menuCloseItem, menuExpandItem, menuItem2,
			menuExpandItem2;
	private List<RadialMenuItem> children = new ArrayList<RadialMenuItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("5403F22683017282F657273A7DCABD674A288323", null);
		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错
		setContentView(R.layout.surrounding);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		// 设置启用内置的缩放控件
		MapController mMapController = mMapView.getController();
		GeoPoint point = new GeoPoint((int) (23.139 * 1E6),
				(int) (113.269 * 1E6));
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别

		pieMenu = new RadialMenuWidget(this);
		menuCloseItem = new RadialMenuItem(getString(R.string.ok), null);
		menuCloseItem
				.setDisplayIcon(android.R.drawable.ic_menu_close_clear_cancel);
		menuItem = new RadialMenuItem(getString(R.string.attration),
				getString(R.string.attration));
		menuExpandItem = new RadialMenuItem(getString(R.string.hotel),
				getString(R.string.hotel));
		menuItem2 = new RadialMenuItem(getString(R.string.restaurant),
				getString(R.string.restaurant));
		menuExpandItem2 = new RadialMenuItem(getString(R.string.suppermarcket),
				getString(R.string.suppermarcket));
		menuCloseItem
				.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
					@Override
					public void execute() {
						// menuLayout.removeAllViews();
						pieMenu.dismiss();
					}
				});
		pieMenu.setAnimationSpeed(0L);
		pieMenu.setSourceLocation(200, 200);
		pieMenu.setIconSize(15, 30);
		pieMenu.setTextSize(13);
		pieMenu.setOutlineColor(getResources().getColor(R.color.dack_blue), 225);
		pieMenu.setInnerRingColor(getResources().getColor(R.color.dack_blue),
				180);
		pieMenu.setOuterRingColor(0x0099CC, 180);
		// pieMenu.setHeader("Test Menu", 20);
		pieMenu.setCenterCircle(menuCloseItem);

		pieMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {
			{
				add(menuItem);
				add(menuItem2);
				add(menuExpandItem);
				add(menuExpandItem2);
			}
		});

		ImageButton imageButton = (ImageButton) findViewById(R.id.surrounding_loaction);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pieMenu.show(v);
			}
		});

	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}
}

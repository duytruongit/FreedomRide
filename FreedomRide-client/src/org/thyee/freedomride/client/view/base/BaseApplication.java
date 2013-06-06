package org.thyee.freedomride.client.view.base;

import java.lang.ref.WeakReference;

import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.LogUtils;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapView;

public class BaseApplication extends Application {

	BMapManager mBMapMan = null;
	WeakReference<MapView> mapView;

	@Override
	public void onCreate() {

		LogUtils.log("application");
		super.onCreate();
	}

	public MapView getMapView(Context c) {
		if (mapView == null) {
			if (mBMapMan == null) {
				mBMapMan = new BMapManager(this);
				mBMapMan.init(Data.MAP_KEY, null);
			}
			mapView = new WeakReference<MapView>(new MapView(c));
		}

		return mapView.get();
	}

	public void setMapView(WeakReference<MapView> mapView) {
		this.mapView = mapView;
	}

}

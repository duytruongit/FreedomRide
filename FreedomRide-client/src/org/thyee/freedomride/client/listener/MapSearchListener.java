package org.thyee.freedomride.client.listener;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;

public class MapSearchListener implements MKSearchListener {

	private Context context;
	private MapView mMapView;

	public MapSearchListener(Context context, MapView mMapView) {
		this.context = context;
		this.mMapView = mMapView;
	}

	@Override
	public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetPoiDetailSearchResult(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetPoiResult(MKPoiResult res, int type, int error) {
		// 错误号可参考MKEvent中的定义

		if (error == MKEvent.ERROR_RESULT_NOT_FOUND) {
			Toast.makeText(context, "抱歉，未找到结果", Toast.LENGTH_LONG).show();
			return;
		} else if (error != 0 || res == null) {
			Toast.makeText(context, "搜索出错啦..", Toast.LENGTH_LONG).show();
			return;
		}
		PoiOverlay poiOverlay = new PoiOverlay((Activity) context, mMapView);
		poiOverlay.setData(res.getAllPoi());
		mMapView.getOverlays().clear();
		mMapView.getOverlays().add(poiOverlay);
		mMapView.refresh();

		// 当ePoiType为2（公交线路）或4（地铁线路）时， poi坐标为空
		for (MKPoiInfo info : res.getAllPoi()) {
			if (info.pt != null) {
				mMapView.getController().animateTo(info.pt);
				break;
			}
		}
	}

	@Override
	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}

package org.thyee.freedomride.client.view.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PoiOverlay;

public class AttrOverlay extends PoiOverlay {
	public List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	private Context mContext;

	public AttrOverlay(MapView mapView, Context context) {
		super(((Activity) context), mapView);

		this.mContext = context;

	}

	// 处理当点击事件
	@Override
	protected boolean onTap(int i) {
		Toast.makeText(this.mContext, GeoList.get(i).getSnippet(),
				Toast.LENGTH_SHORT).show();
		return true;
	}
}

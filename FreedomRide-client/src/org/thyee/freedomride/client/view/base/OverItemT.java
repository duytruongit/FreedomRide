package org.thyee.freedomride.client.view.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class OverItemT extends ItemizedOverlay<OverlayItem> {
	public List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	private Context mContext;

	public OverItemT(Drawable marker, Context context) {
		super(marker);

		this.mContext = context;

	}

	public void reflash() {
		populate();// createItem(int)方法构造item。一旦有了数据，在调用其它方法前，首先调用这个方法
	}

	public void addItems(GeoPoint geoPoint, String title, String snippet) {
		// 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)
		GeoList.add(new OverlayItem(geoPoint, title, snippet));
	}

	@Override
	protected OverlayItem createItem(int i) {
		if (GeoList != null) {
			return GeoList.get(i);
		}
		return null;
	}

	@Override
	public int size() {
		return GeoList.size();
	}

	// 处理当点击事件
	@Override
	protected boolean onTap(int i) {
		Toast.makeText(this.mContext, GeoList.get(i).getSnippet(),
				Toast.LENGTH_SHORT).show();
		return true;
	}
}

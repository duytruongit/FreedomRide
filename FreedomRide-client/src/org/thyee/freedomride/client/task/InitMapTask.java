package org.thyee.freedomride.client.task;

import java.util.HashMap;
import java.util.Map;

import org.thyee.freedomride.client.view.base.PageActivity;

import android.content.Context;

import com.baidu.mapapi.map.MapView;

public class InitMapTask extends Thread {

	private Context context;

	public InitMapTask(Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		Map<String, Object> map = new HashMap<String, Object>();

		MapView mapView = new MapView(context);
		map.put("mapView", mapView);
		((PageActivity) context).refreshFinish(map);

		super.run();
	}
}

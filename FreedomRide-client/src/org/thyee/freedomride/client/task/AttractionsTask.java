package org.thyee.freedomride.client.task;

import org.thyee.freedomride.client.http.AttractionsPage;
import org.thyee.freedomride.client.http.HttpUtils;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.view.MapActivity;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AttractionsTask extends Thread {

	private Context context;
	private String city;
	private String type;

	public AttractionsTask(Context context, String city, String type) {
		this.context = context;
		this.city = city;
		this.type = type;
	}

	@Override
	public void run() {

		String param = "search_LIKE_city=" + city;
		if (!"全部".equals(type)) {
			param += "&search_LIKE_type=" + type;
		}
		String content = HttpUtils.getContentByPost(Data.GETATTRACTIONS, param);
		ObjectMapper objectMapper = new ObjectMapper();
		AttractionsPage page;
		try {
			page = objectMapper.readValue(content, AttractionsPage.class);

			((MapActivity) context).update(page.getContent());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}

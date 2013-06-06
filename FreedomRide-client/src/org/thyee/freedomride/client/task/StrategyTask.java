package org.thyee.freedomride.client.task;

import java.io.IOException;

import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.http.HttpUtils;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.view.StrategyActivity;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StrategyTask extends Thread {

	private Context context;
	private String params;

	public StrategyTask(Context context) {
		this.context = context;
	}

	public StrategyTask(Context context, String params) {
		this.context = context;
		this.params = params;
	}

	@Override
	public void run() {
		if (params == null) {
			params = "";
		}
		String content = HttpUtils.getContentByPost(Data.GETSTRATEGY, params);
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		Strategy strategy = null;
		LogUtils.log(content + "");
		try {
			if (content != null) {
				strategy = mapper.readValue(content, Strategy.class);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		((StrategyActivity) context).update(strategy);

		super.run();
	}
}

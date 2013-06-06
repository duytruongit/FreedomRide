package org.thyee.freedomride.client.task;

import org.thyee.freedomride.client.entity.Result;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.http.HttpUtils;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.view.CollectionActivity;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ShareTask extends Thread {

	private Context context;
	private Strategy strategy;
	private int i;

	public ShareTask(Context context, Strategy strategy, int i) {
		this.context = context;
		this.strategy = strategy;
		this.i = i;
	}

	@Override
	public void run() {
		Result result = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String strategyContent = objectMapper.writeValueAsString(strategy);
			if (strategyContent != null) {
				strategyContent = strategyContent.replace(",\"add\":true", "")
						.replace(",\"add\":false", "");
			}

			String param = "content=" + strategyContent;

			String content = HttpUtils.getContentByPost(Data.SHARE_STRATEGY,
					param);
			result = objectMapper.readValue(content, Result.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((CollectionActivity) context).update(result, i, 1);
		}
		super.run();
	}
}

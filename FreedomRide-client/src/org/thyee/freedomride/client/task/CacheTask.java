package org.thyee.freedomride.client.task;

import org.thyee.freedomride.client.http.StrategyPage;
import org.thyee.freedomride.client.service.CacheService;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.view.Square2Activity;

import android.content.Context;
import android.os.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CacheTask extends Thread {

	private Context context;
	private CacheService cacheService;
	private boolean isRead = false;
	private String content;

	public CacheTask(Context context, boolean isRead, String content) {
		this.context = context;
		cacheService = new CacheService(context);
		this.isRead = isRead;
		this.content = content;
	}

	public CacheTask(Context context, boolean isRead) {
		this.context = context;
		cacheService = new CacheService(context);
		this.isRead = isRead;
	}

	public CacheTask(Context context) {
		this.context = context;
		cacheService = new CacheService(context);
	}

	@Override
	public void run() {
		if (isRead) {
			String content = cacheService.getLastCache();
			Message msg = Message.obtain();
			ObjectMapper mapper = new ObjectMapper();
			StrategyPage page = null;
			LogUtils.log(content + "");
			try {
				if (content != null) {
					page = mapper.readValue(content, StrategyPage.class);
					((Square2Activity) context).getSquareListAdepter().addList(
							page.getContent());
				}
				msg.what = Data.CACHE_SUCCESS;
			} catch (Exception e) {
				msg.what = Data.CACHE_FAIL;
			} finally {
				((Square2Activity) context).update(msg);
			}
		} else {
			try {
				LogUtils.log(content);
				cacheService.saveCache(content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}
}

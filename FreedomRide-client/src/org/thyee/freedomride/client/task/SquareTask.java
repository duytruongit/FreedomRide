package org.thyee.freedomride.client.task;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.http.HttpUtils;
import org.thyee.freedomride.client.http.StrategyPage;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.utils.NetWorkUtils;
import org.thyee.freedomride.client.view.Square2Activity;

import android.content.Context;
import android.os.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SquareTask extends Thread {

	private Context context;
	private String lastUpdateTime;

	public SquareTask(Context context, String lastUpdateTime) {
		this.context = context;
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public void run() {
		NetWorkUtils netWorkUtils = new NetWorkUtils(context);
		Message msg = Message.obtain();
		if (netWorkUtils.isConnectingToInternet()) {
			String path = Data.GETSQUAE;
			String params = "search_GT_updateTime=" + lastUpdateTime;
			System.out.println(path);
			String content = HttpUtils.getContentByPost(path, params);
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share
														// globally
			StrategyPage page = null;
			LogUtils.log(content + "");
			try {
				if (content != null) {
					page = mapper.readValue(content, StrategyPage.class);
					((Square2Activity) context).getSquareListAdepter().addPre(
							page.getContent());
					if (page.getContent() != null
							&& page.getContent().size() != 0) {
						new CacheTask(context, false, content).start();
					}
					msg.what = Data.REFRESH_SUCCESS;
					msg.obj = context.getResources().getString(
							R.string.refresh_complete);
				}
			} catch (Exception e) {
				msg.what = Data.REFRESH_FAIL;
				msg.obj = context.getResources().getString(
						R.string.refresh_fail);
				e.printStackTrace();
			} finally {
				((Square2Activity) context).update(msg);
			}
		} else {
			msg.what = Data.NETWORK_DISABLE;
			msg.obj = context.getResources().getString(
					R.string.network_disabled);
			((Square2Activity) context).update(msg);

		}
		super.run();
	}
}

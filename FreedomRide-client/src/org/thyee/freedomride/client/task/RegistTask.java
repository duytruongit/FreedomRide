package org.thyee.freedomride.client.task;

import org.thyee.freedomride.client.entity.Account;
import org.thyee.freedomride.client.entity.Result;
import org.thyee.freedomride.client.http.HttpUtils;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.view.LoginAndRegistActivity;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistTask extends Thread {

	private Context context;
	private Account account;

	public RegistTask(Context context, Account account) {
		this.context = context;
		this.account = account;
	}

	@Override
	public void run() {
		Result result = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			String param = "content="
					+ objectMapper.writeValueAsString(account);
			String content = HttpUtils.getContentByPost(Data.REGIST, param);

			result = objectMapper.readValue(content, Result.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((LoginAndRegistActivity) context).update(result);
		}
		super.run();
	}
}

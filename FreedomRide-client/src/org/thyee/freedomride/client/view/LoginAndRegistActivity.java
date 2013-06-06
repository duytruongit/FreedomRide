package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.entity.Account;
import org.thyee.freedomride.client.entity.Result;
import org.thyee.freedomride.client.http.HttpUtils;
import org.thyee.freedomride.client.listener.LoginOrRegistListener;
import org.thyee.freedomride.client.utils.ResUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAndRegistActivity extends Activity {

	EditText nameEditText;
	EditText passEditText;
	Button loginBtn;
	Button registBtn;
	View processing;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		setContentView(R.layout.login_dialog);
		initHandler();
		findView();

	}

	public void update(Result result) {
		Message msg = Message.obtain();
		msg.what = 1;
		if (result != null) {
			if (result.isSuccess()) {
				msg.what = 0;
				if (result.getIsLogin()) {
					HttpUtils.JSESSIONID = "JSESSIONID=" + result.getsId()
							+ ";Path=/FreedomRideServer;";
				}
			}
			msg.obj = result.getMessage();
		} else {
			msg.obj = ResUtils.getString(this, R.string.network_error);
		}
		handler.sendMessage(msg);
	}

	private void initHandler() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				actionMsg(msg);
				super.handleMessage(msg);
			}
		};
	}

	private void actionMsg(Message msg) {
		stopProcessing();
		ToastMsg((String) msg.obj);
		if (msg.what == 0) {
			finish();
		}
	}

	private void findView() {
		nameEditText = (EditText) findViewById(R.id.login_name);
		passEditText = (EditText) findViewById(R.id.login_pwd);
		processing = (View) findViewById(R.id.loading_bar);
		loginBtn = (Button) findViewById(R.id.login_btn);
		loginBtn.setTag(0);
		registBtn = (Button) findViewById(R.id.regist_btn);
		registBtn.setTag(1);
		LoginOrRegistListener listener = new LoginOrRegistListener(this);
		loginBtn.setOnClickListener(listener);
		registBtn.setOnClickListener(listener);
	}

	@SuppressLint("NewApi")
	public Account getInputInfo() {
		String name = nameEditText.getText().toString();
		String password = passEditText.getText().toString();
		if (name.isEmpty()) {
			ToastMsg(ResUtils.getString(this, R.string.please_input_name));
			stopProcessing();
			return null;
		}
		if (password.isEmpty()) {
			ToastMsg(ResUtils.getString(this, R.string.please_input_pwd));
			stopProcessing();
			return null;
		}
		Account account = new Account();
		account.setLoginName(name);
		account.setPassword(password);
		return account;
	}

	public void processing() {
		processing.setVisibility(View.VISIBLE);
	}

	public void stopProcessing() {
		processing.setVisibility(View.GONE);
	}

	private void ToastMsg(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}

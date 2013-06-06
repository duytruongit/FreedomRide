package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.entity.Account;
import org.thyee.freedomride.client.task.LoginTask;
import org.thyee.freedomride.client.task.RegistTask;
import org.thyee.freedomride.client.view.LoginAndRegistActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginOrRegistListener implements OnClickListener {

	private Context context;
	private int position;
	private Account account;

	public LoginOrRegistListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		((LoginAndRegistActivity) context).processing();
		account = ((LoginAndRegistActivity) context).getInputInfo();
		if (account != null) {

			position = (Integer) ((Button) v).getTag();
			switch (position) {
			case 0:
				new LoginTask(context, account).start();
				break;
			case 1:
				new RegistTask(context, account).start();
				break;
			default:
				break;
			}

		}
	}
}

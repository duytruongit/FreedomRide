package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.view.CollectionActivity;
import org.thyee.freedomride.client.view.SettingActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuListener implements OnClickListener {

	public enum MenuSelect {
		collect, setting, exit;
	}

	private Context context;
	private int position;

	public MenuListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		position = (Integer) ((Button) v).getTag();
		action(position);
	}

	private void action(int position) {
		MenuSelect menuSelect = MenuSelect.values()[position];
		switch (menuSelect) {
		case collect:
			LogUtils.log("menu——收藏");
			collect();
			break;
		case setting:
			LogUtils.log("menu——设置");
			setting();
			break;
		case exit:
			LogUtils.log("menu——退出");
			exit();
			break;
		default:
			break;
		}
	}

	private void collect() {
		Intent intent = new Intent(context, CollectionActivity.class);
		context.startActivity(intent);
	}

	private void setting() {
		Intent intent = new Intent(context, SettingActivity.class);
		context.startActivity(intent);
	}

	private void exit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.exit_dialog_title);
		builder.setMessage(R.string.exit_dialog_msg);
		builder.setNegativeButton(R.string.cancle, null);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent startMain = new Intent(Intent.ACTION_MAIN);
						startMain.addCategory(Intent.CATEGORY_HOME);
						startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(startMain);
						System.exit(0);
					}
				});
		builder.create();
		builder.show();
	}
}

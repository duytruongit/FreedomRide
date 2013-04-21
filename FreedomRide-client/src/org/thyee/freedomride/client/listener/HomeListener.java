package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.view.IndexActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeListener implements OnClickListener {

	private Context context;

	public HomeListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, IndexActivity.class);
		context.startActivity(intent);
	}
}

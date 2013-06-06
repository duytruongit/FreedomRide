package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.task.ShareTask;
import org.thyee.freedomride.client.task.SyncTask;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class CollentionItemListener implements OnClickListener {

	private Context context;
	private View view;
	private Strategy strategy;
	private int index;
	private int what;

	public CollentionItemListener(Context context, View view,
			Strategy strategy, int index, int what) {
		this.context = context;
		this.view = view;
		this.strategy = strategy;
		this.index = index;
		this.what = what;
	}

	@Override
	public void onClick(View v) {
		v.setVisibility(View.GONE);
		v.setTag(true);
		view.setVisibility(View.VISIBLE);
		action(what);
	}

	private void action(int what) {
		switch (what) {
		case 0:
			SyncTask st = new SyncTask(context, strategy, index);
			st.start();
			break;
		case 1:
			ShareTask shareTask = new ShareTask(context, strategy, index);
			shareTask.start();
			break;
		default:
			break;
		}
	}

}

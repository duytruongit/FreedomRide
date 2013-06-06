package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.SquareListAdapter;
import org.thyee.freedomride.client.task.CacheTask;
import org.thyee.freedomride.client.task.SquareTask;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.view.base.PageActivity;
import org.thyee.freedomride.client.view.base.PullRefreshListView;
import org.thyee.freedomride.client.view.base.PullRefreshListView.OnRefreshListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Square2Activity extends PageActivity {

	PullRefreshListView listView;
	private SquareListAdapter squareListAdapter;
	public Handler handler;
	private boolean isSuccess = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.square_2);
		handler = new MyHander();
		listView = (PullRefreshListView) findViewById(R.id.square_list2);
		setSquareListAdepter(new SquareListAdapter(this));
		listView.setAdapter(getSquareListAdepter());

		CacheTask ct = new CacheTask(this, true);
		ct.start();

		listView.setonRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				LogUtils.log(listView.getLastRefreshTime());
				SquareTask squareTask = new SquareTask(Square2Activity.this,
						listView.getLastRefreshTime());
				squareTask.start();
			}

		});
	}

	class MyHander extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Data.REFRESH_SUCCESS:
				squareListAdapter.notifyDataSetChanged();
				showMessage((String) msg.obj);
				completeRefresh(true);
				break;
			case Data.REFRESH_FAIL:
				showMessage((String) msg.obj);
				completeRefresh(false);
				break;
			case Data.CACHE_SUCCESS:
				squareListAdapter.notifyDataSetChanged();
				break;
			case Data.CACHE_FAIL:

				break;
			case Data.NETWORK_DISABLE:
				showMessage((String) msg.obj);
				completeRefresh(false);
				break;

			default:
				break;
			}

			super.handleMessage(msg);
		}
	}

	private void showMessage(String message) {
		Toast.makeText(Square2Activity.this, message, Toast.LENGTH_SHORT)
				.show();
	}

	private void completeRefresh(boolean isUpdateTime) {
		try {
			listView.onRefreshComplete(isUpdateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Message msg) {
		handler.sendMessage(msg);
	}

	public SquareListAdapter getSquareListAdepter() {
		return squareListAdapter;
	}

	public void setSquareListAdepter(SquareListAdapter squareListAdapter) {
		this.squareListAdapter = squareListAdapter;
	}
}

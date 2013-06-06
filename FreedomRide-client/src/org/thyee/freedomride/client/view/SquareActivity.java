package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.SquareListAdapter;
import org.thyee.freedomride.client.view.base.PageActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

public class SquareActivity extends PageActivity {

	ListView listView;
	private SquareListAdapter squareListAdapter;
	public Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.square);
		handler = new Handler();
		listView = (ListView) findViewById(R.id.square_list);
		setSquareListAdepter(new SquareListAdapter(this));
		listView.setAdapter(getSquareListAdepter());
		// SquareTask squareTask = new SquareTask(this, new Date());
		// squareTask.start();
	}

	public void update() {
		handler.post(runnableUi);
	}

	Runnable runnableUi = new Runnable() {
		@Override
		public void run() {
			// 更新界面
			squareListAdapter.notifyDataSetChanged();
		}

	};

	public SquareListAdapter getSquareListAdepter() {
		return squareListAdapter;
	}

	public void setSquareListAdepter(SquareListAdapter squareListAdapter) {
		this.squareListAdapter = squareListAdapter;
	}
}

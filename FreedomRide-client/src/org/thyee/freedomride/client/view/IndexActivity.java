package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class IndexActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		GridView gridView = (GridView) findViewById(R.id.menu_gridview);
		gridView.setAdapter(new MenuGridViewAdepter(this));
		GridView indexGridView = (GridView) findViewById(R.id.index_gridview);
		indexGridView.setAdapter(new IndexGridViewAdepter(this));

	}

	@Override
	protected void onPause() {
		System.out.println("onPause");
		super.onPause();

	}

	@Override
	protected void onResume() {
		System.out.println("onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		System.out.println("onStart");
		super.onStart();
	}

}

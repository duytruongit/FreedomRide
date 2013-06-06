package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.IndexGridViewAdapter;
import org.thyee.freedomride.client.adapter.MenuGridViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class IndexActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		GridView gridView = (GridView) findViewById(R.id.menu_gridview);
		gridView.setAdapter(new MenuGridViewAdapter(this));
		GridView indexGridView = (GridView) findViewById(R.id.index_gridview);
		indexGridView.setAdapter(new IndexGridViewAdapter(this));

		// setContentView(R.layout.activity_main);
		//
		// final LinearLayout left = (LinearLayout)
		// findViewById(R.id.left_menu);
		// final LinearLayout right = (LinearLayout)
		// findViewById(R.id.right_menu);
		// right.setVisibility(View.GONE);
		// left.setVisibility(View.VISIBLE);
		//
		// ScrollLayout mScrollLayout = (ScrollLayout)
		// findViewById(R.id.my_scrollLayout);
		// mScrollLayout
		// .setScrollSideChangedListener(new OnScrollSideChangedListener() {
		// @Override
		// public void onScrollSideChanged(View v, boolean leftSide) {
		// if (leftSide) {
		// right.setVisibility(View.GONE);
		// left.setVisibility(View.VISIBLE);
		// } else {
		// right.setVisibility(View.VISIBLE);
		// left.setVisibility(View.GONE);
		// }
		// }
		// });
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

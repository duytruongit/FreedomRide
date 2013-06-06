package org.thyee.freedomride.client.view;

import java.util.Map;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.WallectListAdapter;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.view.base.PageActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class WallectActivity extends PageActivity {

	private WallectListAdapter wallectListAdapter;
	private SharedPreferences sharedPreferences;
	private ListView listView;
	private Map<String, Boolean> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallect);

		listView = (ListView) findViewById(R.id.wallect_list);

		sharedPreferences = getSharedPreferences(Data.WALLECT_SETTING,
				Context.MODE_PRIVATE);

		map = (Map<String, Boolean>) sharedPreferences.getAll();
		wallectListAdapter = new WallectListAdapter(this, map);
		listView.setAdapter(wallectListAdapter);

		ImageButton imageButton = (ImageButton) findViewById(R.id.wallect_add);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText editText = new EditText(WallectActivity.this);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						WallectActivity.this);
				builder.setTitle(R.string.wallect_add_dialog_title);
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setView(editText);
				builder.setNegativeButton(R.string.cancle, null);
				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String text = editText.getText().toString();
								Editor editor = sharedPreferences.edit();
								editor.putBoolean(text, false);
								editor.commit();
								wallectListAdapter.map.put(text, Boolean.FALSE);
								wallectListAdapter.keys = wallectListAdapter.map
										.keySet().toArray();
								wallectListAdapter.notifyDataSetChanged();
								listView.setAdapter(wallectListAdapter);
							}
						});
				builder.show();
			}
		});

	}
}

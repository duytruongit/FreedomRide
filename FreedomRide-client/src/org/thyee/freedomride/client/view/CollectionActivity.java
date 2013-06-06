package org.thyee.freedomride.client.view;

import java.util.List;
import java.util.Map;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.CollectionAdapter;
import org.thyee.freedomride.client.entity.Result;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyTemp;
import org.thyee.freedomride.client.task.CollectionTask;
import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.SharedPreferencesUtils;
import org.thyee.freedomride.client.view.base.PageActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectionActivity extends PageActivity {

	SharedPreferencesUtils sharedPreferencesUtils;
	CollectionAdapter collectionAdapter;
	Map<String, String> map = null;
	Handler handler;
	ListView collectionListView;
	View headView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection);

		collectionListView = (ListView) findViewById(R.id.collection_list);
		Map<String, ?> temp = getAll();
		// headView = LayoutInflater.from(this).inflate(R.layout.refresh_header,
		// null);
		// collectionListView.addHeaderView(headView);
		if (temp != null) {
			map = (Map<String, String>) temp;
			System.out.println(map.size());
			new CollectionTask(this, map).start();
		}

		ImageButton imageButton = (ImageButton) findViewById(R.id.collection_add);

		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						CollectionActivity.this);
				builder.setTitle(R.string.choice_condition);
				View view = LayoutInflater.from(CollectionActivity.this)
						.inflate(R.layout.condition_dialog, null);
				final Spinner citySpinner = (Spinner) view
						.findViewById(R.id.condition_city_spinner);
				final Spinner typeSpinner = (Spinner) view
						.findViewById(R.id.condition_type_spinner);
				final EditText nameEditText = (EditText) view
						.findViewById(R.id.condition_name);

				builder.setView(view);
				builder.setNegativeButton(R.string.cancle, null);
				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(
										CollectionActivity.this,
										MapActivity.class);
								String city = citySpinner.getSelectedItem()
										.toString();
								String type = typeSpinner.getSelectedItem()
										.toString();
								String name = nameEditText.getText().toString();
								System.out.println(citySpinner
										.getSelectedItemPosition());
								intent.putExtra("city", city);
								intent.putExtra("type", type);
								intent.putExtra("name", name);
								startActivityForResult(intent, 1);
							}
						});
				builder.show();
			}
		});

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				MsgObject mObject = null;
				if (msg.what <= 3) {
					mObject = (MsgObject) msg.obj;
				}
				switch (msg.what) {
				case 0:
					updateUi(mObject);
					showToast("请先登录");
					Intent intent = new Intent(CollectionActivity.this,
							LoginAndRegistActivity.class);
					startActivity(intent);
					break;
				case 1:
					updateUi(mObject);
					showToast("网络出错");
					break;
				case 2:
					updateUi(mObject);
					showToast(mObject.result.getMessage());
					break;
				case 3:
					updateUi(mObject);
					Result result = mObject.result;
					String strategy = result.getOther();
					String key = collectionAdapter.updateItem(strategy,
							mObject.i);
					saveData(strategy, key);
					showToast(result.getMessage());
					break;
				case 4:
					// headView.setVisibility(View.GONE);
					// collectionListView.removeHeaderView(headView);
					collectionAdapter = new CollectionAdapter(
							CollectionActivity.this,
							(List<StrategyTemp>) msg.obj);
					collectionListView.setAdapter(collectionAdapter);
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}

			private void showToast(String msg) {
				Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT)
						.show();
			}

			public void updateUi(MsgObject mObject) {
				int first = collectionListView.getFirstVisiblePosition();
				View view = collectionListView.getChildAt(mObject.i - first);
				if (view != null) {
					ProgressBar refreshBar = (ProgressBar) view
							.findViewById(R.id.refresh_process_bar);
					if (mObject.result != null && mObject.result.isSuccess()) {
						if (mObject.what == 0) {
							view.findViewById(R.id.share_btn).setVisibility(
									View.VISIBLE);
						}
					} else {
						int viewId = R.id.sync_btn;
						if (mObject.what == 0) {
							viewId = R.id.sync_btn;
						} else if (mObject.what == 1) {
							viewId = R.id.share_btn;
						}
						view.findViewById(viewId).setVisibility(View.VISIBLE);
					}
					refreshBar.setVisibility(View.GONE);
				}
			}
		};

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println(resultCode);
		if (resultCode == 2) {
			String content = data.getStringExtra(Data.RESULT_DATA);
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				Strategy strategy = objectMapper.readValue(content,
						Strategy.class);
				String key = collectionAdapter.addItem(strategy);
				collectionAdapter.notifyDataSetChanged();
				saveData(content, key);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void saveData(String strategy, String key) {
		initSharedPreferencesUtils();
		sharedPreferencesUtils.write(Data.COLLECTION_SETTING, key, strategy,
				this);
	}

	private Map<String, ?> getAll() {
		initSharedPreferencesUtils();
		return sharedPreferencesUtils.readAll(Data.COLLECTION_SETTING, this);
	}

	private void initSharedPreferencesUtils() {
		if (sharedPreferencesUtils == null) {
			sharedPreferencesUtils = new SharedPreferencesUtils();
		}
	}

	@Override
	public void refreshFinish(Object object) {
		Message msg = Message.obtain();
		msg.what = 4;
		msg.obj = object;
		handler.sendMessage(msg);
		super.refreshFinish(object);
	}

	public void update(Result result, int i, int what) {
		Message msg = Message.obtain();
		if (result == null) {
			msg.what = 1;
		} else if (!result.isSuccess()) {
			if (!result.getIsLogin()) {
				msg.what = 0;
			} else {
				msg.what = 2;
			}
		} else {
			msg.what = 3;
		}
		MsgObject msgObject = new MsgObject();
		msg.obj = msgObject;
		msgObject.result = result;
		msgObject.i = i;
		msgObject.what = what;
		handler.sendMessage(msg);
	}

	class MsgObject {
		Result result;
		int i;
		int what;
	}
}

package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.task.StrategyTask;
import org.thyee.freedomride.client.utils.ResUtils;
import org.thyee.freedomride.client.view.StrategyActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class StrategySearchListener implements OnClickListener {

	private String result;
	private Context context;
	private int position;
	private View pView;
	private View dialogView;
	private String params = null;

	public StrategySearchListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		pView = v;
		position = v.getId();
		action(position);
	}

	public void action(int position) {
		switch (position) {
		case R.id.choose_city:
			createDialog(position, R.layout.city_dialog, R.string.where_go);
			break;
		case R.id.choose_type:
			createDialog(position, R.layout.type_dialog, R.string.what_type);
			break;
		case R.id.strategy_length:
			createDialog(position, R.layout.legth_dialog, R.string.how_long);
			break;
		case R.id.strategy_search:
			search();
			// ((StrategyActivity) context).update(null);
			break;

		default:
			break;
		}
	}

	private void createDialog(int id, int layoutId, int titleId) {

		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(titleId);
		dialog.setIcon(android.R.drawable.ic_dialog_map);
		initDialog(id);
		dialog.setView(dialogView);
		dialog.setNegativeButton(R.string.cancle, null);
		dialog.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						TextView textView = (TextView) (StrategySearchListener.this.pView);
						textView.setText(result);
					}
				});
		dialog.show();
	}

	private void initDialog(int id) {
		switch (id) {
		case R.id.choose_city:
			initCityDialog();
			break;
		case R.id.choose_type:
			initTypeDialog();
			break;
		case R.id.strategy_length:
			initLengthDialog();
			break;

		default:
			break;
		}
	}

	private void initLengthDialog() {
		LayoutInflater layoutInflater = ((Activity) context)
				.getLayoutInflater();
		dialogView = layoutInflater.inflate(R.layout.legth_dialog, null);
		final SeekBar seekBar = (SeekBar) dialogView
				.findViewById(R.id.strategy_length_seekbar);
		final TextView lenText = (TextView) dialogView
				.findViewById(R.id.strategy_length_text);
		String day = context.getResources().getString(R.string.day);
		String defalut = ((TextView) this.pView).getText().toString();
		lenText.setText(defalut);
		int progress = (int) (Float.parseFloat(defalut.replace(day, "").trim()) * 2);
		seekBar.setProgress(progress);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				result = progress / 2.0
						+ context.getResources().getString(R.string.day);

				lenText.setText(result);
			}
		});
	}

	private void initCityDialog() {
		LayoutInflater layoutInflater = ((Activity) context)
				.getLayoutInflater();
		dialogView = layoutInflater.inflate(R.layout.city_dialog, null);
		final Spinner provinceSpinner = (Spinner) dialogView
				.findViewById(R.id.province_spinner);
		final Spinner citySpinner = (Spinner) dialogView
				.findViewById(R.id.city_spinner);
		provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				citySpinner.setAdapter(new ArrayAdapter(context,
						android.R.layout.simple_spinner_item, context
								.getResources().getStringArray(
										R.array.guangdong)));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String[] cityStrings = context.getResources().getStringArray(
						R.array.guangdong);
				result = cityStrings[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initTypeDialog() {
		LayoutInflater layoutInflater = ((Activity) context)
				.getLayoutInflater();
		dialogView = layoutInflater.inflate(R.layout.type_dialog, null);
		final Spinner typeSpinner = (Spinner) dialogView
				.findViewById(R.id.type_spinner);
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				result = typeSpinner.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	private void search() {

		TextView cityText = (TextView) ((StrategyActivity) context)
				.findViewById(R.id.choose_city);
		TextView typeText = (TextView) ((StrategyActivity) context)
				.findViewById(R.id.choose_type);
		TextView lenText = (TextView) ((StrategyActivity) context)
				.findViewById(R.id.strategy_length);

		String length = lenText.getText().toString().replace("天", "");
		Double double1 = new Double(length);
		int i = (int) (double1 * 2);
		params = "search_EQ_length=" + i;

		/*
		 * if (!context.getResources().getString(R.string.choose_city)
		 * .equals(cityText.getText())) { params += "&search_EQ_city=" +
		 * cityText.getText(); }
		 */
		if (!ResUtils.getString(context, R.string.choose_type).equals(
				typeText.getText())
				&& !ResUtils.getString(context, R.string.all).equals(
						typeText.getText())) {
			params += "&search_LIKE_type=" + typeText.getText();
		}
		((StrategyActivity) context).startSearch();
		StrategyTask strategyTask;
		System.out.println(params);
		if (params != null) {
			strategyTask = new StrategyTask(context, params);
		} else {
			strategyTask = new StrategyTask(context);
		}
		strategyTask.start();
	}
}

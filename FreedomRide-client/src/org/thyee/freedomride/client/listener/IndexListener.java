package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.utils.LogUtils;
import org.thyee.freedomride.client.view.Square2Activity;
import org.thyee.freedomride.client.view.StrategyActivity;
import org.thyee.freedomride.client.view.SurroundingActivity;
import org.thyee.freedomride.client.view.WallectActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IndexListener implements OnClickListener {

	public enum IndexSelect {
		strategy, surrounding, wallect, publicsquare;
	}

	private Context context;
	private int position;

	public IndexListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		position = (Integer) ((Button) v).getTag();
		action(position);
	}

	private void action(int position) {
		IndexSelect indexSelect = IndexSelect.values()[position];
		switch (indexSelect) {
		case strategy:
			LogUtils.log("攻略搜索");
			strategy();
			break;
		case surrounding:
			LogUtils.log("周边搜索");
			surrounding();
			break;
		case wallect:
			LogUtils.log("行囊管理");
			wallect();
			break;
		case publicsquare:
			LogUtils.log("广场搜索");
			square();
			break;

		default:
			break;
		}
	}

	private void strategy() {
		Intent intent = new Intent(context, StrategyActivity.class);
		context.startActivity(intent);
	}

	private void surrounding() {
		Intent intent = new Intent(context, SurroundingActivity.class);
		context.startActivity(intent);
	}

	private void wallect() {
		Intent intent = new Intent(context, WallectActivity.class);
		context.startActivity(intent);
	}

	private void square() {
		Intent intent = new Intent(context, Square2Activity.class);
		context.startActivity(intent);
	}
}

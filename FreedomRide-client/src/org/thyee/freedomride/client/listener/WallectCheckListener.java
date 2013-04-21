package org.thyee.freedomride.client.listener;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.utils.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class WallectCheckListener implements OnCheckedChangeListener {

	private Context context;

	public WallectCheckListener(Context context) {
		this.context = context;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Data.WALLECT_SETTING, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		TextView textView = (TextView) buttonView.getTag();
		editor.putBoolean(textView.getText().toString(), isChecked);
		editor.commit();
		if (isChecked) {
			textView.setTextColor(context.getResources().getColor(
					R.color.dack_blue));
		} else {
			textView.setTextColor(context.getResources().getColor(R.color.gray));
		}
	}

}

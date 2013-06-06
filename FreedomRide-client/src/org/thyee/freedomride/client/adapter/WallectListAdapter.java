package org.thyee.freedomride.client.adapter;

import java.util.Map;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.listener.WallectCheckListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class WallectListAdapter extends BaseAdapter {
	private Context mContext;
	public Map<String, Boolean> map;
	public Object[] keys;
	private WallectCheckListener wallectCheckListener;

	public WallectListAdapter(Context c, Map<String, Boolean> map) {
		mContext = c;
		this.map = map;
		if (map != null && map.keySet() != null) {
			this.keys = map.keySet().toArray();
		}
		wallectCheckListener = new WallectCheckListener(mContext);

	}

	public int getCount() {
		return keys.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater.inflate(R.layout.wallect_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textview = (TextView) convertView
					.findViewById(R.id.wallect_text);
			viewHolder.checkBox = (CheckBox) convertView
					.findViewById(R.id.wallect_check);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textview.setText(keys[position].toString());
		Boolean checked = (Boolean) map.get(keys[position]);
		if (checked) {
			viewHolder.textview.setTextColor(mContext.getResources().getColor(
					R.color.dack_blue));
		}
		viewHolder.checkBox.setChecked(checked);
		viewHolder.checkBox.setTag(viewHolder.textview);
		viewHolder.checkBox.setOnCheckedChangeListener(wallectCheckListener);
		return convertView;
	}

	class ViewHolder {
		TextView textview;
		CheckBox checkBox;
	}

}
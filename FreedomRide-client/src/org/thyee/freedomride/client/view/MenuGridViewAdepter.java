package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.listener.MenuListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class MenuGridViewAdepter extends BaseAdapter {
	private Context mContext;
	private MenuListener menuListener;

	private int[] imageIds = { R.drawable.menu_collect,
			R.drawable.menu_setting, R.drawable.menu_exit };

	public MenuGridViewAdepter(Context c) {
		mContext = c;
		menuListener = new MenuListener(mContext);
	}

	public int getCount() {
		return 3;
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
			convertView = layoutInflater.inflate(R.layout.menu_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageButton = (Button) convertView
					.findViewById(R.id.menu_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imageButton.setBackgroundResource(imageIds[position]);
		viewHolder.imageButton.setTag(Integer.valueOf(position));
		viewHolder.imageButton.setOnClickListener(menuListener);
		return convertView;
	}

	class ViewHolder {
		Button imageButton;
	}

}
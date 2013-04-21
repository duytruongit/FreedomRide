package org.thyee.freedomride.client.view;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.listener.IndexListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class IndexGridViewAdepter extends BaseAdapter {
	private Context mContext;
	private IndexListener listener;

	private int[] imageIds = { R.drawable.index_search,
			R.drawable.index_surrounding, R.drawable.index_wallet,
			R.drawable.index_publicsquare };
	private String[] names = { "攻略", "周边", "行囊", "广场" };

	public IndexGridViewAdepter(Context c) {
		mContext = c;
		listener = new IndexListener(mContext);
	}

	public int getCount() {
		return imageIds.length;
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
			convertView = layoutInflater.inflate(R.layout.index_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageButton = (Button) convertView
					.findViewById(R.id.index_image);
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.index_text);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imageButton.setBackgroundResource(imageIds[position]);
		viewHolder.imageButton.setTag(Integer.valueOf(position));
		viewHolder.imageButton.setOnClickListener(listener);
		viewHolder.textView.setText(names[position]);
		return convertView;
	}

	class ViewHolder {
		Button imageButton;
		TextView textView;
	}

}
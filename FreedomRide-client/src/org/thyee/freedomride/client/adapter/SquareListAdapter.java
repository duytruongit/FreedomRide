package org.thyee.freedomride.client.adapter;

import java.util.ArrayList;
import java.util.List;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SquareListAdapter extends BaseAdapter {

	private Context mContext;
	private List<Strategy> list;

	public SquareListAdapter(Context c) {
		mContext = c;
		if (list == null) {
			list = new ArrayList<Strategy>();
		}
	}

	public SquareListAdapter(Context c, List<Strategy> list) {
		mContext = c;
		this.list = list;
	}

	public void setList(List<Strategy> list) {
		this.list = list;
	}

	public void addPre(List<Strategy> list) {
		if (this.list != null) {
			this.list.addAll(0, list);
		} else {
			this.list = list;
		}
	}

	public void addList(List<Strategy> list) {
		if (this.list != null) {
			this.list.addAll(list);
		} else {
			this.list = list;
		}
	}

	public int getCount() {
		return list.size();
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
		if (convertView == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater.inflate(R.layout.square_item, null);
			viewHolder = new ViewHolder();

			viewHolder.title = (TextView) convertView
					.findViewById(R.id.square_title);
			viewHolder.tag = (TextView) convertView
					.findViewById(R.id.square_tag);
			viewHolder.update = (TextView) convertView
					.findViewById(R.id.square_updateTime);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(list.get(position).getName());
		StringBuilder sb = new StringBuilder();
		for (StrategyItem s : (list.get(position).getStrategyItems())) {
			sb.append(s.getAttractions().getName());
			sb.append("     ");
		}
		viewHolder.tag.setText(sb);
		viewHolder.update.setText(list.get(position).getUpdateTime());

		return convertView;
	}

	class ViewHolder {
		TextView title;
		TextView tag;
		TextView update;
	}

}
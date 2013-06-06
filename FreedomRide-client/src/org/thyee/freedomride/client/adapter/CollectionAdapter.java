package org.thyee.freedomride.client.adapter;

import java.util.Date;
import java.util.List;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyItem;
import org.thyee.freedomride.client.entity.StrategyTemp;
import org.thyee.freedomride.client.listener.CollentionItemListener;
import org.thyee.freedomride.client.utils.DateUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectionAdapter extends BaseAdapter {

	private Context mContext;
	private List<StrategyTemp> list;

	public CollectionAdapter(Context c, List<StrategyTemp> list) {
		mContext = c;
		this.list = list;
	}

	public String addItem(Strategy strategy) {
		StrategyTemp strategyTemp = new StrategyTemp();
		strategyTemp.setStrategy(strategy);
		String key = DateUtils.getDateString(new Date());
		strategyTemp.setKey(key);
		list.add(strategyTemp);
		return key;
	}

	public String updateItem(String content, int i) {
		StrategyTemp strategyTemp = list.get(i);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Strategy strategy = objectMapper.readValue(content, Strategy.class);
			strategyTemp.setStrategy(strategy);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strategyTemp.getKey();
	}

	@Override
	public int getCount() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater
					.inflate(R.layout.collection_item, null);
			viewHolder = new ViewHolder();
			viewHolder.syncBtn = (ImageButton) convertView
					.findViewById(R.id.sync_btn);
			if (viewHolder.syncBtn.getTag() == null) {
				viewHolder.syncBtn.setTag(false);
			}
			viewHolder.shareBtn = (ImageButton) convertView
					.findViewById(R.id.share_btn);
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.collection_strategy_title);
			viewHolder.tag = (TextView) convertView
					.findViewById(R.id.collection_strategy_tag);
			viewHolder.progressBar = (ProgressBar) convertView
					.findViewById(R.id.refresh_process_bar);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Strategy strategy = null;
		try {
			strategy = list.get(position).getStrategy();
			viewHolder.title.setText(strategy.getName());
			StringBuilder sb = new StringBuilder();
			for (StrategyItem s : (list.get(position).getStrategy()
					.getStrategyItems())) {
				sb.append(s.getAttractions().getName());
				sb.append("     ");
			}
			viewHolder.tag.setText(sb);
			viewHolder.progressBar.setVisibility(View.GONE);
			switch (strategy.getState()) {
			case 0:
				viewHolder.shareBtn.setVisibility(View.GONE);
				viewHolder.syncBtn.setVisibility(View.VISIBLE);
				break;
			case 1:
				viewHolder.shareBtn.setVisibility(View.VISIBLE);
				viewHolder.syncBtn.setVisibility(View.GONE);
				break;
			case 2:
				viewHolder.shareBtn.setVisibility(View.GONE);
				viewHolder.syncBtn.setVisibility(View.GONE);
				break;

			default:
				break;
			}
			viewHolder.syncBtn.setOnClickListener(new CollentionItemListener(
					mContext, viewHolder.progressBar, strategy, position, 0));
			viewHolder.shareBtn.setOnClickListener(new CollentionItemListener(
					mContext, viewHolder.progressBar, strategy, position, 1));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}

	class ViewHolder {
		ImageButton syncBtn;
		ImageButton shareBtn;
		ProgressBar progressBar;
		TextView title;
		TextView tag;
	}

}
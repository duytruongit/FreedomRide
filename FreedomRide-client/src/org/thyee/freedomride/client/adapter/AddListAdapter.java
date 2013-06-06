package org.thyee.freedomride.client.adapter;

import java.util.List;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.entity.Attractions;
import org.thyee.freedomride.client.entity.Picture;
import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyItem;
import org.thyee.freedomride.client.task.AsynImageLoader;
import org.thyee.freedomride.client.utils.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AddListAdapter extends BaseAdapter {
	private Context mContext;

	private Strategy strategy;
	public List<StrategyItem> sItems;

	public AddListAdapter(Context c, Strategy strategy) {
		mContext = c;
		this.strategy = strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public int getCount() {
		if (strategy == null) {
			return 0;
		}
		sItems = strategy.getStrategyItems();
		if (sItems != null) {
			return strategy.getStrategyItems().size();
		}
		return 0;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public void addItem(Attractions attractions) {
		StrategyItem strategyItem = new StrategyItem();
		strategyItem.setAttractions(attractions);
		strategyItem.setStep(sItems.size() + 1);
		System.out.println(attractions.getName());
		sItems.add(strategyItem);
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater.inflate(R.layout.add_strategy_item,
					null);
			viewHolder = new ViewHolder();
			viewHolder.textview = (TextView) convertView
					.findViewById(R.id.add_strategyitem_title);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.add_strategytem_image);
			viewHolder.ticketTextView = (TextView) convertView
					.findViewById(R.id.add_strategyitem_ticket);
			viewHolder.addrTextView = (TextView) convertView
					.findViewById(R.id.add_strategyitem_addr);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textview.setText(sItems.get(position).getAttractions()
				.getName());
		viewHolder.ticketTextView.setText("门票："
				+ sItems.get(position).getAttractions().getTicket());
		viewHolder.addrTextView.setText("地址："
				+ sItems.get(position).getAttractions().getAddress());
		AsynImageLoader loader = new AsynImageLoader();
		List<Picture> ps = sItems.get(position).getAttractions().getPictures();
		if (ps != null && ps.size() != 0) {
			String path = Data.HOST + "/picture" + ps.get(0).getPath()
					+ ps.get(0).getFile_name();
			Bitmap cachedImage = loader.loadDrawableFromNet(
					viewHolder.imageView, path);
			System.out.println("path " + path);
			viewHolder.imageView.setImageBitmap(cachedImage);
		}
		return convertView;
	}

	class ViewHolder {
		TextView textview;
		ImageView imageView;
		TextView ticketTextView;
		TextView addrTextView;
	}

}
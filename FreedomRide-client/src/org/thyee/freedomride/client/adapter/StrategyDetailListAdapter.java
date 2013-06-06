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

public class StrategyDetailListAdapter extends BaseAdapter {
	private Context mContext;

	private Strategy strategy;
	public List<StrategyItem> sItems;

	public StrategyDetailListAdapter(Context c, Strategy strategy) {
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
		strategyItem.setStep(sItems.size());
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
			viewHolder.ticketTextView = (TextView) convertView
					.findViewById(R.id.add_strategyitem_ticket);
			viewHolder.timeTextView = (TextView) convertView
					.findViewById(R.id.add_strategyitem_time);
			viewHolder.addrTextView = (TextView) convertView
					.findViewById(R.id.add_strategyitem_addr);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.add_strategytem_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Picture p = null;

		if (sItems.get(position).getAttractions() != null) {
			viewHolder.timeTextView.setText("时间："
					+ sItems.get(position).getAttractions().getVisit_length());
			viewHolder.textview.setText(sItems.get(position).getAttractions()
					.getName());
			viewHolder.ticketTextView.setText("门票："
					+ sItems.get(position).getAttractions().getTicket());
			viewHolder.addrTextView.setText("地址："
					+ sItems.get(position).getAttractions().getAddress());
			List<Picture> ps = sItems.get(position).getAttractions()
					.getPictures();
			if (ps != null && ps.size() != 0) {
				p = ps.get(0);
			}
		}
		if (sItems.get(position).getRestaurant() != null) {
			viewHolder.textview.setText(sItems.get(position).getRestaurant()
					.getName());
			viewHolder.ticketTextView.setText("人均："
					+ sItems.get(position).getRestaurant().getConsumption());
			viewHolder.addrTextView.setText("地址："
					+ sItems.get(position).getRestaurant().getAddress());
			List<Picture> ps = sItems.get(position).getRestaurant()
					.getPictures();
			if (ps != null && ps.size() != 0) {
				p = ps.get(0);
			}
		}
		if (sItems.get(position).getHotel() != null) {
			viewHolder.textview.setText(sItems.get(position).getHotel()
					.getName());
			viewHolder.ticketTextView.setText("最低："
					+ sItems.get(position).getHotel().getPrice());
			viewHolder.addrTextView.setText("地址："
					+ sItems.get(position).getHotel().getAddress());
			p = sItems.get(position).getHotel().getPicture();
		}
		if (sItems.get(position).getTime() != null) {
			viewHolder.timeTextView.setText("时间："
					+ sItems.get(position).getTime());
		}
		AsynImageLoader loader = new AsynImageLoader();
		if (p != null) {
			String path = Data.HOST + "/picture" + p.getPath()
					+ p.getFile_name();
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
		TextView timeTextView;
		TextView addrTextView;
	}

}
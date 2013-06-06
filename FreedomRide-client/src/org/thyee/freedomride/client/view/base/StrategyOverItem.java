package org.thyee.freedomride.client.view.base;

import java.util.List;

import org.thyee.freedomride.client.entity.StrategyItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

public class StrategyOverItem extends OverItemT {
	private Context mContext;
	private List<StrategyItem> strategyItems;

	public StrategyOverItem(Drawable marker, Context context,
			List<StrategyItem> strategyItems) {
		super(marker, context);

		this.strategyItems = strategyItems;
		this.mContext = context;

	}

	// 处理当点击事件
	@Override
	protected boolean onTap(int i) {
		String name = "";
		if (strategyItems.get(i).getAttractions() != null) {
			name = strategyItems.get(i).getAttractions().getName();
		} else if (strategyItems.get(i).getRestaurant() != null) {
			name = strategyItems.get(i).getRestaurant().getName();
		} else {
			name = strategyItems.get(i).getHotel().getName();
		}

		Toast.makeText(this.mContext, name, Toast.LENGTH_SHORT).show();
		return true;
	}
}

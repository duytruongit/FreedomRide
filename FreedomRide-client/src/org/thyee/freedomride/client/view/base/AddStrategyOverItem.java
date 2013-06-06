package org.thyee.freedomride.client.view.base;

import java.util.List;

import org.thyee.freedomride.client.R;
import org.thyee.freedomride.client.adapter.StrategyDetailListAdapter;
import org.thyee.freedomride.client.entity.Attractions;
import org.thyee.freedomride.client.view.MapActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class AddStrategyOverItem extends OverItemT {
	private Context mContext;

	private List<Attractions> list;
	private Attractions attractions;

	PopupOverlay pop;

	public AddStrategyOverItem(Drawable marker, Context context,
			List<Attractions> list) {
		super(marker, context);
		this.list = list;
		this.mContext = context;

	}

	// 处理当点击事件
	@Override
	protected boolean onTap(int i) {
		attractions = list.get(i);
		Toast.makeText(this.mContext, attractions.getName(), Toast.LENGTH_SHORT)
				.show();

		pop = new PopupOverlay(((MapActivity) mContext).mMapView,
				new PopupClickListener() {
					@Override
					public void onClickedPopup(int index) {
						if (index == 1) {
							String message = "";
							if (!attractions.isAdd()) {
								StrategyDetailListAdapter addListAdapter = ((MapActivity) mContext).addListAdapter;
								addListAdapter.addItem(attractions);
								addListAdapter.notifyDataSetChanged();
								attractions.setAdd(true);
								message = "添加成功";
							} else {
								message = "已经添加过了";
							}
							pop.hidePop();
							Toast.makeText(mContext, message,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		/**
		 * 准备pop弹窗资源，根据实际情况更改 弹出包含三张图片的窗口，可以传入三张图片、两张图片、一张图片。
		 * 弹出的窗口，会根据图片的传入顺序，组合成一张图片显示. 点击到不同的图片上时，回调函数会返回当前点击到的图片索引index
		 */

		Bitmap[] bmps = new Bitmap[3];
		try {
			bmps[0] = BitmapFactory.decodeResource(mContext.getResources(),
					R.drawable.pop_title).copy(Bitmap.Config.ARGB_8888, true);
			Canvas canvas = new Canvas(bmps[0]);
			Resources resources = mContext.getResources();

			float scale = resources.getDisplayMetrics().density;
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			// text color - #3D3D3D
			paint.setColor(Color.WHITE);
			// text size in pixels
			paint.setTextSize((int) (14 * scale));

			// draw text to the Canvas center
			Rect bounds = new Rect();
			paint.getTextBounds(GeoList.get(i).getSnippet(), 0, GeoList.get(i)
					.getSnippet().length(), bounds);
			int x = (bmps[0].getWidth() - bounds.width()) / 2;
			int y = 1 * (bmps[0].getHeight() - bounds.height()) / 2;

			canvas.drawText(GeoList.get(i).getSnippet(), x * scale, y * scale,
					paint);
			bmps[1] = BitmapFactory.decodeResource(mContext.getResources(),
					R.drawable.pop_add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 弹窗弹出位置
		GeoPoint ptTAM = GeoList.get(i).getPoint();
		// 弹出pop,隐藏pop
		pop.showPopup(bmps, ptTAM, 32);
		return true;
	}
}

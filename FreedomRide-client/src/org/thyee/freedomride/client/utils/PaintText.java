package org.thyee.freedomride.client.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class PaintText {

	public Bitmap paintText(Bitmap bitmap, String text, Context context, int x,
			int y) {

		Canvas canvas = new Canvas(bitmap);
		Resources resources = context.getResources();

		float scale = resources.getDisplayMetrics().density;
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// text color - #3D3D3D
		paint.setColor(Color.WHITE);
		// text size in pixels
		paint.setTextSize((int) (14 * scale));

		// draw text to the Canvas center
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		// int x = (bitmap.getWidth() - bounds.width()) / 2;
		// int y = 1 * (bitmap.getHeight() - bounds.height()) / 2;

		canvas.drawText(text, x * scale, y * scale, paint);

		return bitmap;

	}

	public Bitmap paintLocalText(Bitmap bitmap, String text, Context context) {

		int x = 21;
		int y = 14;

		paintText(bitmap, text, context, x, y);

		return bitmap;

	}

	public Bitmap paintPlaceText(Bitmap bitmap, String text, Context context) {

		int x = 21;
		int y = 8;

		return bitmap;

	}
}

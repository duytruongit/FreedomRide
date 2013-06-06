package org.thyee.freedomride.client.utils;

import android.content.Context;
import android.content.res.Resources;

public class ResUtils {

	public static String getString(Context c, int id) {
		Resources res = c.getResources();
		return res.getString(id);
	}
}

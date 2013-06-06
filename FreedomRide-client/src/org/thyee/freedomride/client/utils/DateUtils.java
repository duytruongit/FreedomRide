package org.thyee.freedomride.client.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getDateString(Date date) {
		return format.format(date);
	}

	public static Date getDateFromString(String timeString) throws Exception {
		return format.parse(timeString);
	}

	public static Date getLastWeekDate() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day - 7);
		return calendar.getTime();
	}

	public static String getLastWeekDateString() {
		return getDateString(getLastWeekDate());
	}

	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
}

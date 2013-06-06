package org.thyee.freedomride.client.utils;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtils {

	public String read(String fileName, String key, Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE);
		String value = sharedPreferences.getString(key, "");
		return value;
	}

	public Map<String, Object> readAll(String fileName, Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE);

		return (Map<String, Object>) sharedPreferences.getAll();
	}

	public void write(String fileName, String key, String value, Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
}

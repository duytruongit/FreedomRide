package org.thyee.freedomride.client.service;

import java.io.File;
import java.io.RandomAccessFile;

import org.thyee.freedomride.client.utils.Data;
import org.thyee.freedomride.client.utils.SdCardUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CacheService {

	private String path;
	private Context context;

	public CacheService() {
		SdCardUtils sdCardUtils = new SdCardUtils();
		path = sdCardUtils.getCachePath();
	}

	public CacheService(Context context) {
		SdCardUtils sdCardUtils = new SdCardUtils();
		path = sdCardUtils.getCachePath();
		this.context = context;
	}

	public void saveCache(String content) throws Exception {
		File pathDir = new File(path);
		int length = 0;
		if (!pathDir.exists()) {
			pathDir.mkdirs();
			length = 0;
		} else {
			length = pathDir.list().length;
		}
		String name = "cache_" + length + ".free";
		saveCache(content, name);
	}

	public String getLastCache() {
		File pathDir = new File(path);
		int length = 0;
		if (!pathDir.exists()) {
			pathDir.mkdirs();
			length = 0;
		} else {
			length = pathDir.list().length - 1;
		}
		String name = "cache_" + length + ".free";
		return getCache(name);
	}

	public String nextCache() {
		int num = getCurrentCacheNum();

		if (num > 0) {
			String name = "cache_" + (num - 1) + ".free";
			saveCurrentCacheNum(num - 1);
			return getCache(name);
		}
		return null;
	}

	public int getCurrentCacheNum() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Data.SOME_SETTING, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(Data.CURRENT_CACHE_NUM, -1);
	}

	public void saveCurrentCacheNum(int num) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Data.SOME_SETTING, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putInt(Data.CURRENT_CACHE_NUM, num);
		editor.commit();
	}

	public void saveCache(String content, String fileName) throws Exception {
		RandomAccessFile ra = null;
		try {
			File pathDir = new File(path);
			if (!pathDir.exists()) {
				pathDir.mkdirs();
			}

			ra = new RandomAccessFile(path + "/" + fileName, "rw");

			ra.write(content.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ra != null) {
				ra.close();
			}
		}
	}

	public String getCache(String fileName) {
		StringBuilder stringBuilder = new StringBuilder();
		RandomAccessFile ra = null;
		File pathDir = new File(path);
		if (!pathDir.exists()) {
			return null;
		}
		File file = new File(path + "/" + fileName);
		if (!file.exists()) {
			return null;
		}
		try {
			ra = new RandomAccessFile(path + "/" + fileName, "rw");
			String readLine = "";
			while ((readLine = ra.readLine()) != null) {
				stringBuilder.append(new String(
						readLine.getBytes("ISO-8859-1"), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}
}

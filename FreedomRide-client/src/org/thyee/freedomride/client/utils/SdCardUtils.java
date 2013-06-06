package org.thyee.freedomride.client.utils;

import android.os.Environment;

public class SdCardUtils {

	public boolean isSdCardInstall() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	public String getSDCardPath() {
		if (isSdCardInstall()) {
			return Environment.getExternalStorageDirectory().toString();
		} else {
			return null;
		}
	}

	public String getProjectRootPath() {
		if (isSdCardInstall()) {
			return getSDCardPath() + "/freedomride";
		} else {
			return null;
		}
	}

	public String getCachePath() {
		if (isSdCardInstall()) {
			return getProjectRootPath() + "/cache";
		} else {
			return null;
		}
	}
}

package org.thyee.freedomride.client.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.thyee.freedomride.client.utils.LogUtils;

public class HttpUtils {

	private static HttpURLConnection httpURLConnection;
	private static URL url;

	public static String JSESSIONID = null;

	public static String getContent(String path) {
		String content = null;
		Reader reader = null;
		try {
			url = new URL(path);

			httpURLConnection = (HttpURLConnection) url.openConnection();

			httpURLConnection.setConnectTimeout(3 * 1000);
			httpURLConnection.setReadTimeout(3 * 1000);
			LogUtils.log(httpURLConnection.getResponseCode() + "");
			if (httpURLConnection.getResponseCode() == 200) {
				reader = new InputStreamReader(new BufferedInputStream(
						httpURLConnection.getInputStream()), "UTF-8");
				int c;
				StringBuffer sb = new StringBuffer();
				while ((c = reader.read()) != -1) {
					sb.append((char) c);
				}
				content = sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return content;
	}

	public static String getContentByPost(String path, String params) {
		String content = null;
		Reader reader = null;
		try {
			url = new URL(path);

			httpURLConnection = (HttpURLConnection) url.openConnection();
			if (JSESSIONID != null) {
				System.out.println(JSESSIONID);
				httpURLConnection.setRequestProperty("Cookie", JSESSIONID);
			}
			httpURLConnection.setConnectTimeout(3 * 1000);
			httpURLConnection.setReadTimeout(3 * 1000);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.getOutputStream().write(params.getBytes());//
			if (httpURLConnection.getResponseCode() == 200) {
				reader = new InputStreamReader(new BufferedInputStream(
						httpURLConnection.getInputStream()), "UTF-8");
				int c;
				StringBuffer sb = new StringBuffer();
				while ((c = reader.read()) != -1) {
					sb.append((char) c);
				}
				content = sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return content;
	}

}

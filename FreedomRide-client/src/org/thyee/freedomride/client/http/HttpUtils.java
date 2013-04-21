package org.thyee.freedomride.client.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	private HttpURLConnection httpURLConnection;
	private URL url;

	public String getContent(String path) {
		String content = null;
		Reader reader = null;
		try {
			url = new URL(path);

			httpURLConnection = (HttpURLConnection) url.openConnection();

			httpURLConnection.setConnectTimeout(3 * 1000);
			httpURLConnection.setReadTimeout(3 * 1000);

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

package org.thyee.freedomride.client.view.base;

import org.thyee.freedomride.client.view.IndexActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class PageActivity extends Activity {

	public void onclick(View view) {
		Intent intent = new Intent(this, IndexActivity.class);
		this.startActivity(intent);
	}

	public void refreshFinish(Object object) {

	}
}
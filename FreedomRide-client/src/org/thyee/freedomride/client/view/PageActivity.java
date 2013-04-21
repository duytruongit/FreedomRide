package org.thyee.freedomride.client.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class PageActivity extends Activity {

	public void onclick(View view) {
		Intent intent = new Intent(this, IndexActivity.class);
		this.startActivity(intent);
	}
}
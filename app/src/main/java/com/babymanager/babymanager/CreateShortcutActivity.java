package com.babymanager.babymanager;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;


public class CreateShortcutActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShortcutIconResource icon = ShortcutIconResource.fromContext(this, R.drawable.ic_action_alarm);

		Intent intent = new Intent();

		Intent launchIntent = new Intent(this, ServiceStartActivity.class);
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.shortcut_label));
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		setResult(RESULT_OK, intent);
		finish();
	}
}

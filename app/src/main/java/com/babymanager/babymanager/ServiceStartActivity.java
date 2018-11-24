package com.babymanager.babymanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.babymanager.babymanager.Service.TimeService;

public class ServiceStartActivity extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, TimeService.class);
        if(TimeService.TimeContainer.getInstance().isServiceRunning.get()) {
        	stopService(intent);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            }else {
                startService(intent);
            }
        }
        finish();

    }
}

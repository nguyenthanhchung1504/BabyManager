package com.babymanager.babymanager;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.zer.android.newsdk.ZAndroidSDK;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZAndroidSDK.initApplication(this, this.getApplicationContext().getPackageName());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
package com.babymanager.babymanager.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


import androidx.annotation.Nullable;

import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Constant;
import com.babymanager.babymanager.Utils.SharedPreferenceUtils;

public class MusicAlarmService extends Service {
    boolean check;
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String key = intent.getStringExtra(Constant.MUSIC_ON_OF);
        if (key.equals("on")){
            check =true;
            mediaPlayer = MediaPlayer.create(this, R.raw.perfectstrangers);
            mediaPlayer.start();
        }else {
            check = false;
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        SharedPreferenceUtils utils = new SharedPreferenceUtils(getApplicationContext());
        utils.setValue(Constant.CHECK,check);
        return START_NOT_STICKY;
    }
}

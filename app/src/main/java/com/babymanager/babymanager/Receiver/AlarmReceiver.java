package com.babymanager.babymanager.Receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.babymanager.babymanager.Activity.AlarmActivity;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Service.MusicAlarmService;
import com.babymanager.babymanager.Utils.Constant;
import com.babymanager.babymanager.Utils.SharedPreferenceUtils;

public class AlarmReceiver extends BroadcastReceiver {
    String channelId = "default_channel_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = "chanel name";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(channelId, name, importance);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(mChannel);
        }
        Intent notificationIntent = new Intent(context, AlarmActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        SharedPreferenceUtils sharedPreferenceUtils = new SharedPreferenceUtils(context);
        String title = sharedPreferenceUtils.getStringValue(Constant.SAVE_TITLE_ALARM, "Baby manager");
        String content = sharedPreferenceUtils.getStringValue(Constant.SAVE_CONTENT_ALARM, context.getString(R.string.you_have_timer));

        sharedPreferenceUtils.setValue(Constant.BUTTON_CANCEL,true);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_alarm)
                .setContentTitle(title)
                .setContentText(content)
                .setChannelId(channelId)
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.notify(0, builder.build());
        String music_string = intent.getStringExtra(Constant.MUSIC_ON_OF);
        Intent myIntent = new Intent(context, MusicAlarmService.class);
        myIntent.putExtra(Constant.MUSIC_ON_OF, music_string);
        context.startService(myIntent);


    }
}

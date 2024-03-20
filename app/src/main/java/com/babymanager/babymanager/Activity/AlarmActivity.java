package com.babymanager.babymanager.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Receiver.AlarmReceiver;
import com.babymanager.babymanager.Utils.Constant;
import com.babymanager.babymanager.Utils.SharedPreferenceUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    @BindView(R.id.edt_title)
    EditText edtTitle;
    @BindView(R.id.edt_body)
    EditText edtBody;
    @BindView(R.id.btn_accept)
    Button btnAccept;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private Calendar calendar;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Intent intent;
    private boolean buttonEnable;
    public static final String inputFormat = "HH:mm";
    private Date dateCurrent, dateCompare;
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
    Date CurrentTime;
    Date endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ButterKnife.bind(this);
        btnCancel.setEnabled(false);
        SharedPreferenceUtils utils = new SharedPreferenceUtils(this);
        buttonEnable = utils.getBoolanValue(Constant.CHECK, false);
        btnCancel.setEnabled(buttonEnable);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        intent = new Intent(this, AlarmReceiver.class);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("dd MMM yyyy");

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                int hourCurrent = calendar.get(Calendar.HOUR);
                int minCurrent = calendar.get(Calendar.MINUTE);
                dateCurrent = parseDate(hourCurrent + ":" + minCurrent);
                dateCompare = parseDate(hour + ":" + min);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                try {
                    CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    endTime = dateFormat.parse(hour + ":" + min);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (CurrentTime.after(endTime)) {
                    Toast.makeText(AlarmActivity.this, R.string.timer_limit, Toast.LENGTH_SHORT).show();
                } else if (edtTitle.getText().toString().isEmpty()) {
                    Toast.makeText(AlarmActivity.this, getString(R.string.fill_title), Toast.LENGTH_SHORT).show();
                } else if (edtBody.getText().toString().isEmpty()) {
                    Toast.makeText(AlarmActivity.this, getString(R.string.fill_body), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra(Constant.MUSIC_ON_OF, "on");
                    pendingIntent = PendingIntent.getBroadcast(
                            AlarmActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE
                    );
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    BabyMangerDatabase database = new BabyMangerDatabase(AlarmActivity.this);
                    database.addTimer(
                            edtTitle.getText().toString(),
                            edtBody.getText().toString(),
                            hour + " " + getString(R.string.hours) + " " + min + " " + getString(R.string.minutes),
                            getString(R.string.date) + " " + df.format(Calendar.getInstance().getTime()),
                            1
                    );
                    SharedPreferenceUtils preferenceUtils = new SharedPreferenceUtils(AlarmActivity.this);
                    preferenceUtils.setValue(Constant.SAVE_TITLE_ALARM, edtTitle.getText().toString());
                    preferenceUtils.setValue(Constant.SAVE_CONTENT_ALARM, edtBody.getText().toString());
//                    buttonEnable = true;
//                    SharedPreferences sharedPreferences = getSharedPreferences(Constant.BUTTON_CANCEL, MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean(Constant.SAVE_CANCEL, buttonEnable);
//                    editor.commit();
                    startActivity(new Intent(AlarmActivity.this, AlarmHistoryActivity.class));
                }
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(sender);
                intent.putExtra(Constant.MUSIC_ON_OF, "off");
                sendBroadcast(intent);
                buttonEnable = false;
//                SharedPreferenceUtils preferenceUtils = new SharedPreferenceUtils(AlarmActivity.this);
//                preferenceUtils.setValue(Constant.BUTTON_CANCEL, buttonEnable);
                btnCancel.setEnabled(buttonEnable);
            }
        });

    }


    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        startActivity(new Intent(this, AlarmHistoryActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, AlarmHistoryActivity.class));
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}

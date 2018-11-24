package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Service.TimeService;
import com.babymanager.babymanager.Utils.Ads;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;

public class SleepActivity extends AppCompatActivity implements PropertyChangeListener {

    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.txt_timer)
    TextView txtTimer;
    @BindView(R.id.btn_sleep)
    FButton btnSleep;
    @BindView(R.id.btn_wake_up)
    FButton btnWakeUp;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    @BindView(R.id.imageView3)
    ImageView imageView3;

    private String startDate;
    private Handler h;
    private Timer t;
    private final Runnable updateTextRunnable = new Runnable() {
        @Override
        public void run() {
            updateTimeText();
        }
    };

    private void updateTimeText() {
        txtTimer.setText(getTimeString(TimeService.TimeContainer.getInstance().getElapsedTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_sleep);
        ButterKnife.bind(this);
        h = new Handler();
        btnWakeUp.setEnabled(false);
        Ads.b(this, layoutAds, new Ads.OnAdsListener() {
            @Override
            public void onError() {
                layoutAds.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                layoutAds.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdOpened() {
                layoutAds.setVisibility(View.VISIBLE);
            }
        });

        Ads.f(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        checkServiceRunning();
        TimeService.TimeContainer t = TimeService.TimeContainer.getInstance();
        if (t.getCurrentState() == TimeService.TimeContainer.STATE_RUNNING) {
            btnSleep.setText(R.string.stop);
            startUpdateTimer();
        } else {
            btnSleep.setText(R.string.start);
            updateTimeText();
        }
        TimeService.TimeContainer.getInstance().addObserver(this);
        super.onResume();
    }

    @OnClick({R.id.btn_sleep, R.id.btn_wake_up})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_sleep:
                DateFormat df = new SimpleDateFormat("HH:mm");
                startDate = df.format(Calendar.getInstance().getTime());
                TimeService.TimeContainer tc = TimeService.TimeContainer.getInstance();
                if (tc.getCurrentState() == TimeService.TimeContainer.STATE_RUNNING) {
                    TimeService.TimeContainer.getInstance().pause();
                    btnSleep.setText(R.string.continues_sleep);
                    btnWakeUp.setEnabled(true);
                } else {
                    TimeService.TimeContainer.getInstance().start();
                    startUpdateTimer();
                    btnSleep.setText(R.string.stop);
                    btnWakeUp.setEnabled(false);

                }
                break;
            case R.id.btn_wake_up:
                addSleepBaby();
                break;
        }
    }

    private void addSleepBaby() {
        DateFormat df = new SimpleDateFormat("HH:mm , dd MMM yyyy");
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        database.addSleep(
                "" + txtTimer.getText().toString(),
                1,
                "" + startDate,
                "" + df.format(Calendar.getInstance().getTime())
        );
        startActivity(new Intent(this, SleepHistoryActivity.class));
        TimeService.TimeContainer.getInstance().stopAndReset();
    }

    private String getTimeString(long ms) {
        if (ms == 0) {
            return "00:00:00";
        } else {
            long millis = (ms % 1000) / 10;
            long seconds = (ms / 1000) % 60;
            long minutes = (ms / 1000) / 60;
            long hours = minutes / 60;

            StringBuilder sb = new StringBuilder();
            if (hours > 0) {
                sb.append(hours);
                sb.append(':');
            }
            if (minutes > 0) {
                minutes = minutes % 60;
                if (minutes >= 10) {
                    sb.append(minutes);
                } else {
                    sb.append(0);
                    sb.append(minutes);
                }
            } else {
                sb.append('0');
                sb.append('0');
            }
            sb.append(':');
            if (seconds > 0) {
                if (seconds >= 10) {
                    sb.append(seconds);
                } else {
                    sb.append(0);
                    sb.append(seconds);
                }
            } else {
                sb.append('0');
                sb.append('0');
            }
            sb.append(':');
            if (millis > 0) {
                if (millis >= 10) {
                    sb.append(millis);
                } else {
                    sb.append(0);
                    sb.append(millis);
                }
            } else {
                sb.append('0');
                sb.append('0');
            }
            return sb.toString();
        }
    }

    public void startUpdateTimer() {
        if (t != null) {
            t.cancel();
            t = null;
        }
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                h.post(updateTextRunnable);
            }
        }, 0, 16);

    }

    private void checkServiceRunning() {
        if (!TimeService.TimeContainer.getInstance().isServiceRunning.get()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(this, TimeService.class));
            } else {
                startService(new Intent(this, TimeService.class));
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == TimeService.TimeContainer.STATE_CHANGED) {
            TimeService.TimeContainer t = TimeService.TimeContainer.getInstance();
            if (t.getCurrentState() == TimeService.TimeContainer.STATE_RUNNING) {
                btnSleep.setText(R.string.stop);
                startUpdateTimer();

            } else {
                btnSleep.setText(R.string.start);
                updateTimeText();
            }
            checkServiceRunning();
        }
    }

    @Override
    protected void onPause() {
        if (t != null) {
            t.cancel();
            t = null;
        }
        TimeService.TimeContainer.getInstance().removeObserver(this);
        super.onPause();
    }


    @OnClick(R.id.img_back)
    public void onClicked() {
        finish();
    }
}

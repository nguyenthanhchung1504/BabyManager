package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeasureActivity extends AppCompatActivity {

    @BindView(R.id.txt_height_weight)
    TextView txtHeightWeight;
    @BindView(R.id.edt_height)
    EditText edtHeight;
    @BindView(R.id.edt_weight)
    EditText edtWeight;
    @BindView(R.id.layout_height_weight)
    LinearLayout layoutHeightWeight;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private BabyMangerDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_measure);
        ButterKnife.bind(this);
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

    @OnClick(R.id.btn_height_weight)
    public void onViewClicked() {
        if (edtWeight.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_weight), Toast.LENGTH_SHORT).show();
        } else if (edtHeight.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_height), Toast.LENGTH_SHORT).show();
        } else {
            DateFormat df = new SimpleDateFormat("HH:mm ,dd MMM yyyy");
            DateFormat dateFormat = new SimpleDateFormat("MM");
            database = new BabyMangerDatabase(this);
            database.addWeightAndHeightBaby(
                    edtWeight.getText().toString(),
                    edtHeight.getText().toString(),
                    df.format(Calendar.getInstance().getTime()),
                    Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime())),
                    1
            );
            database.updateBaby(edtWeight.getText().toString(),
                    edtHeight.getText().toString(),
                    1);
            startActivity(new Intent(this, MeasureHistoryActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.img_back)
    public void onClicked() {
        finish();
    }
}

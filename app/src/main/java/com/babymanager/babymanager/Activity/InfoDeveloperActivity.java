package com.babymanager.babymanager.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.babymanager.babymanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoDeveloperActivity extends AppCompatActivity {


    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_info_developer);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}

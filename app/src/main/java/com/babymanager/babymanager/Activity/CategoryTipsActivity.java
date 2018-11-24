package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.babymanager.babymanager.Adapter.CategoryAdapter;
import com.babymanager.babymanager.Database.TipsBabyDatabase;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryTipsActivity extends AppCompatActivity {

    @BindView(R.id.lst_category)
    RecyclerView lstCategory;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private TipsBabyDatabase database;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tips);
        ButterKnife.bind(this);
        database = new TipsBabyDatabase(this);
        lstCategory.setLayoutManager(new LinearLayoutManager(this));
        lstCategory.setHasFixedSize(true);
        adapter = new CategoryAdapter(this, database.getAllCategory());
        lstCategory.setAdapter(adapter);
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

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(this, HomeActivity.class));
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

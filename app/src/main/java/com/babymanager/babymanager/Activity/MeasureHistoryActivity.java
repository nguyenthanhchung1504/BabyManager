package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.Adapter.MeasureAdapter;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Helper.RecyclerItemTouchHelper;
import com.babymanager.babymanager.Interface.RecyclerItemTouchHelperListener;
import com.babymanager.babymanager.Models.Measure;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeasureHistoryActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    @BindView(R.id.lst_history_feed)
    RecyclerView lstHistoryFeed;
    @BindView(R.id.txt_data)
    TextView txtData;
    @BindView(R.id.root_layout)
    RelativeLayout rootLayout;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private MeasureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_measure_history);
        ButterKnife.bind(this);
        lstHistoryFeed.setLayoutManager(new LinearLayoutManager(this));
        lstHistoryFeed.setHasFixedSize(true);
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        adapter = new MeasureAdapter(this, database.getWeightAndHeightBaby());
        lstHistoryFeed.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() != 0) {
            lstHistoryFeed.setVisibility(View.VISIBLE);
            txtData.setVisibility(View.GONE);
        }
        ItemTouchHelper.SimpleCallback itemTouchHSimpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHSimpleCallback).attachToRecyclerView(lstHistoryFeed);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(lstHistoryFeed.getContext()
                , R.anim.layout_fall_down);
        lstHistoryFeed.setLayoutAnimation(controller);
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

    @OnClick(R.id.fab_add)
    public void onViewClicked() {
        startActivity(new Intent(this, MeasureActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            startActivity(new Intent(this, HomeActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Measure deleteItem = ((MeasureAdapter) lstHistoryFeed.getAdapter()).getItem(viewHolder.getAdapterPosition());
        adapter.removeItem(viewHolder.getAdapterPosition());
        new BabyMangerDatabase(getBaseContext()).removeFromMeasure(String.valueOf(deleteItem.getId()));
        Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.deleted), Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    @OnClick(R.id.img_back)
    public void onClicked() {
        startActivity(new Intent(this, HomeActivity.class));
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

package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.Adapter.TimerAdapter;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Helper.RecyclerItemTouchHelper;
import com.babymanager.babymanager.Interface.RecyclerItemTouchHelperListener;
import com.babymanager.babymanager.Models.Timer;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmHistoryActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.lst_history_feed)
    RecyclerView lstHistoryFeed;
    @BindView(R.id.txt_data)
    TextView txtData;
    @BindView(R.id.fab_add)
    ImageView fabAdd;
    @BindView(R.id.root_layout)
    RelativeLayout rootLayout;
    TimerAdapter adapter;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_history);
        ButterKnife.bind(this);
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        lstHistoryFeed.setLayoutManager(new LinearLayoutManager(this));
        lstHistoryFeed.setHasFixedSize(true);
        adapter = new TimerAdapter(this, database.getAllTimer());
        lstHistoryFeed.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() != 0) {
            lstHistoryFeed.setVisibility(View.VISIBLE);
            txtData.setVisibility(View.GONE);
        }
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(lstHistoryFeed.getContext()
                , R.anim.layout_fall_down);
        lstHistoryFeed.setLayoutAnimation(controller);
        ItemTouchHelper.SimpleCallback itemTouchHSimpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHSimpleCallback).attachToRecyclerView(lstHistoryFeed);
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


    @OnClick({R.id.img_back, R.id.fab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.fab_add:
                startActivity(new Intent(this, AlarmActivity.class));
                break;
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Timer deleteItem = ((TimerAdapter) lstHistoryFeed.getAdapter()).getItem(viewHolder.getAdapterPosition());
        adapter.removeItem(viewHolder.getAdapterPosition());
        new BabyMangerDatabase(getBaseContext()).removeFromTimer(String.valueOf(deleteItem.getId()));
        Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.deleted), Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
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

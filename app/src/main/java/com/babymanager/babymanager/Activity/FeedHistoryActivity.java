package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.babymanager.babymanager.Adapter.FeedAdapter;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Helper.RecyclerItemTouchHelper;
import com.babymanager.babymanager.Interface.RecyclerItemTouchHelperListener;
import com.babymanager.babymanager.Models.Feed;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedHistoryActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    @BindView(R.id.lst_history_feed)
    RecyclerView lstHistoryFeed;
    @BindView(R.id.txt_data)
    TextView txtData;
    @BindView(R.id.root_layout)
    ConstraintLayout rootLayout;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_feed_history);
        ButterKnife.bind(this);
        swipeLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark
        );

        lstHistoryFeed.setLayoutManager(new LinearLayoutManager(this));
        lstHistoryFeed.setHasFixedSize(true);

        showHistoryFeed();

        if (adapter.getItemCount() != 0) {
            lstHistoryFeed.setVisibility(View.VISIBLE);
            txtData.setVisibility(View.GONE);
        }
        ItemTouchHelper.SimpleCallback itemTouchHSimpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHSimpleCallback).attachToRecyclerView(lstHistoryFeed);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(lstHistoryFeed.getContext()
                , R.anim.layout_fall_down);
        lstHistoryFeed.setLayoutAnimation(controller);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showHistoryFeed();
                lstHistoryFeed.setAdapter(adapter);
                lstHistoryFeed.getAdapter().notifyDataSetChanged();
                lstHistoryFeed.scheduleLayoutAnimation();
                swipeLayout.setRefreshing(false);
                recreate();
            }
        });
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

    private void showHistoryFeed() {
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        adapter = new FeedAdapter(this, database.getAllFeed());
        lstHistoryFeed.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @OnClick(R.id.fab_add)
    public void onViewClicked() {
        startActivity(new Intent(this, FeedActivity.class));
        recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            startActivity(new Intent(this, HomeActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Feed deleteItem = ((FeedAdapter) lstHistoryFeed.getAdapter()).getItem(viewHolder.getAdapterPosition());
        adapter.removeItem(viewHolder.getAdapterPosition());
        new BabyMangerDatabase(getBaseContext()).removeFromFeed(String.valueOf(deleteItem.getId()));
        Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.deleted), Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showHistoryFeed();
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

package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.Adapter.DiaperAdapter;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Helper.RecyclerItemTouchHelper;
import com.babymanager.babymanager.Interface.RecyclerItemTouchHelperListener;
import com.babymanager.babymanager.Models.Diaper;
import com.babymanager.babymanager.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiaperHistoryActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    @BindView(R.id.lst_history_feed)
    RecyclerView lstHistoryFeed;
    @BindView(R.id.txt_data)
    TextView txtData;
    @BindView(R.id.root_layout)
    RelativeLayout rootLayout;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private DiaperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_diaper_history);

        ButterKnife.bind(this);
        lstHistoryFeed.setLayoutManager(new LinearLayoutManager(this));
        lstHistoryFeed.setHasFixedSize(true);
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        adapter = new DiaperAdapter(this, database.getAllDiaper());
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

    }

    @OnClick(R.id.fab_add)
    public void onViewClicked() {
        startActivity(new Intent(this, DiaperActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            startActivity(new Intent(this, HomeActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        final Diaper deleteItem = ((DiaperAdapter) lstHistoryFeed.getAdapter()).getItem(viewHolder.getAdapterPosition());
        adapter.removeItem(viewHolder.getAdapterPosition());
        new BabyMangerDatabase(getBaseContext()).removeFromDiaper(String.valueOf(deleteItem.getId()));
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

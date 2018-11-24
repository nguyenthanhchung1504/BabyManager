package com.babymanager.babymanager.Activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.Adapter.TimerAdapter;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Models.User;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;
import com.babymanager.babymanager.Utils.Constant;
import com.babymanager.babymanager.Utils.LanguageUtils;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;
    @BindView(R.id.img_feed)
    ImageView imgFeed;
    @BindView(R.id.img_sleep)
    ImageView imgSleep;
    @BindView(R.id.img_diaper)
    ImageView imgDiaper;
    @BindView(R.id.img_measure)
    ImageView imgMeasure;
    @BindView(R.id.img_chart)
    ImageView imgChart;
    @BindView(R.id.img_tips)
    ImageView imgTips;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.item_feed)
    LinearLayout itemFeed;
    @BindView(R.id.item_sleep)
    LinearLayout itemSleep;
    @BindView(R.id.item_diaper)
    LinearLayout itemDiaper;
    @BindView(R.id.item_measure)
    LinearLayout itemMeasure;
    @BindView(R.id.textView26)
    ImageView textView26;
    @BindView(R.id.textView30)
    TextView textView30;
    @BindView(R.id.item_chart)
    LinearLayout itemChart;
    @BindView(R.id.item_info_baby)
    LinearLayout itemInfoBaby;
    @BindView(R.id.item_tips)
    LinearLayout itemTips;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    @BindView(R.id.icon_feed)
    ImageView iconFeed;
    @BindView(R.id.icon_sleep)
    ImageView iconSleep;
    @BindView(R.id.icon_diaper)
    ImageView iconDiaper;
    @BindView(R.id.icon_measure)
    ImageView iconMeasure;
    @BindView(R.id.icon_chart)
    ImageView iconChart;
    @BindView(R.id.icon_baby)
    ImageView iconBaby;
    @BindView(R.id.icon_tips)
    ImageView iconTips;
    @BindView(R.id.img_drawer)
    ImageView imgDrawer;
    private TimerAdapter adapter;
    private LanguageUtils languageUtils;
    DrawerLayout drawer;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        Glide.with(this).load(R.drawable.ic_chart_baby).into(imgChart);
        Glide.with(this).load(R.drawable.ic_feed_baby).into(imgFeed);
        Glide.with(this).load(R.drawable.ic_measure_baby).into(imgMeasure);
        Glide.with(this).load(R.drawable.ic_sleep_baby).into(imgSleep);
        Glide.with(this).load(R.drawable.ic_diaper_baby).into(imgDiaper);
        Glide.with(this).load(R.drawable.ic_tips_baby).into(imgTips);

        Glide.with(this).load(R.mipmap.ic_feed).into(iconFeed);
        Glide.with(this).load(R.mipmap.ic_sleep).into(iconSleep);
        Glide.with(this).load(R.mipmap.ic_diaper).into(iconDiaper);
        Glide.with(this).load(R.mipmap.ic_measure).into(iconMeasure);
        Glide.with(this).load(R.mipmap.ic_chart).into(iconChart);
        Glide.with(this).load(R.mipmap.ic_info_baby).into(iconBaby);
        Glide.with(this).load(R.mipmap.ic_tips).into(iconTips);
        Glide.with(this).load(R.drawable.dotted).into(textView26);
        Glide.with(this).load(R.drawable.menu).into(imgDrawer);
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        User user = database.getUser(1);
        adapter = new TimerAdapter(this, database.getAllTimer());
        txtName.setText(user.getNameBaby());
        txtBirthday.setText(user.getBirthday());
        byte[] anh = user.getPhotoBaby();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(anh, 0, anh.length, options);
        options.inSampleSize = 3;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
//        imgAvatar.setImageBitmap(bitmap);
        Glide.with(this).load(bitmap).into(imgAvatar);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            showChangeLanguageDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showChangeLanguageDialog() {
        String[] listItems = {getString(R.string.vietnamese), getString(R.string.english), getString(R.string.japanese)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_language);
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    languageUtils.setLocal(HomeActivity.this, "vi");
                    recreate();
                } else if (i == 1) {
                    languageUtils.setLocal(HomeActivity.this, "en");
                    recreate();
                } else if (i == 2) {
                    languageUtils.setLocal(HomeActivity.this, "ja");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feed) {
            startActivity(new Intent(this, FeedHistoryActivity.class));
        } else if (id == R.id.nav_sleep) {
            startActivity(new Intent(this, SleepHistoryActivity.class));
        } else if (id == R.id.nav_diaper) {
            startActivity(new Intent(this, DiaperHistoryActivity.class));
        } else if (id == R.id.nav_measure) {
            startActivity(new Intent(this, MeasureHistoryActivity.class));
        } else if (id == R.id.nav_chart) {
            startActivity(new Intent(this, ChartActivity.class));
        } else if (id == R.id.nav_baby) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.nav_tips) {
            startActivity(new Intent(this, CategoryTipsActivity.class));
        } else if (id == R.id.nav_info) {
            startActivity(new Intent(this, InfoDeveloperActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @OnClick({R.id.img_feed, R.id.img_sleep, R.id.img_diaper, R.id.img_measure, R.id.img_chart, R.id.img_tips})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_feed:
                startActivity(new Intent(this, FeedHistoryActivity.class));
                break;
            case R.id.img_sleep:
                startActivity(new Intent(this, SleepHistoryActivity.class));
                break;
            case R.id.img_diaper:
                startActivity(new Intent(this, DiaperHistoryActivity.class));
                break;
            case R.id.img_measure:
                startActivity(new Intent(this, MeasureHistoryActivity.class));
                break;
            case R.id.img_chart:
                startActivity(new Intent(this, ChartActivity.class));
                break;
            case R.id.img_tips:
                startActivity(new Intent(this, CategoryTipsActivity.class));
                break;
        }
    }

    @OnClick(R.id.img_drawer)
    public void onViewClicked() {
        drawer.openDrawer(GravityCompat.START);
    }


    @OnClick(R.id.fab_add)
    public void onClickedAlarm() {
        startActivity(new Intent(this, AlarmHistoryActivity.class));
    }

    @OnClick({R.id.img_tips, R.id.item_feed, R.id.item_sleep, R.id.item_diaper, R.id.item_measure, R.id.item_chart, R.id.item_info_baby, R.id.item_tips})
    public void onClickedItem(View view) {
        switch (view.getId()) {
            case R.id.img_tips:
                startActivity(new Intent(this, CategoryTipsActivity.class));
                break;
            case R.id.item_feed:
                startActivity(new Intent(this, FeedHistoryActivity.class));
                break;
            case R.id.item_sleep:
                startActivity(new Intent(this, SleepHistoryActivity.class));
                break;
            case R.id.item_diaper:
                startActivity(new Intent(this, DiaperHistoryActivity.class));
                break;
            case R.id.item_measure:
                startActivity(new Intent(this, MeasureHistoryActivity.class));
                break;
            case R.id.item_chart:
                startActivity(new Intent(this, ChartActivity.class));
                break;
            case R.id.item_info_baby:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.item_tips:
                startActivity(new Intent(this, CategoryTipsActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else{
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.img_avatar)
    public void onViewClickedAvater() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        try {
            intent.putExtra("return-data", true);
            startActivityForResult(intent, Constant.PICK_IMAGE);

        } catch (ActivityNotFoundException e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.PICK_IMAGE) {
                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    bitmap = extras2.getParcelable("data");
                    imgAvatar.setImageBitmap(bitmap);
                    BabyMangerDatabase database = new BabyMangerDatabase(this);
                    database.updatePhoto(getByteArrayFromImageView(imgAvatar), 1);
                    recreate();
                } else {
                    Uri imageUri = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAvatar.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 120, 120, false));
                        BabyMangerDatabase database = new BabyMangerDatabase(this);
                        database.updatePhoto(getByteArrayFromImageView(imgAvatar), 1);
                        recreate();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected byte[] getByteArrayFromImageView(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}

package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.Database.TipsBabyDatabase;
import com.babymanager.babymanager.Models.DetailTips;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;
import com.babymanager.babymanager.Utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTipsActivity extends AppCompatActivity {


    @BindView(R.id.txt_content)
    TextView txtContent;
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.txt_cover_photo)
    TextView txtCoverPhoto;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tips);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String categoryId = intent.getStringExtra(Constant.TIPS_ID);
        String content = intent.getStringExtra(Constant.TIPS_NAME);

        TipsBabyDatabase database = new TipsBabyDatabase(this);
        DetailTips tipData = database.getDetailTips(Integer.parseInt(categoryId));
        int id = Integer.parseInt(categoryId);
        if (id == 1){
            txtTitle.setText(R.string.number_one);
            txtContent.setText(R.string.number_ele);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }
        }
        if (id == 2){
            txtTitle.setText(R.string.number_two);
            txtContent.setText(R.string.number_twe);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }

        }
        if (id == 3){
            txtTitle.setText(R.string.number_three);
            txtContent.setText(R.string.number_thir);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }

        }
        if (id == 4){
            txtTitle.setText(R.string.number_four);
            txtContent.setText(R.string.number_fourteen);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }
        }
        if (id == 5){
            txtTitle.setText(R.string.number_five);
            txtContent.setText(R.string.number_fiveteen);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }

        }
        if (id == 6){
            txtTitle.setText(R.string.number_six);
            txtContent.setText(R.string.number_sixteen);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }

        }
        if (id == 7){
            txtTitle.setText(R.string.number_seven);
            txtContent.setText(R.string.number_seventeen);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }
        }
        if (id == 8){
            txtTitle.setText(R.string.number_eight);
            txtContent.setText(R.string.number_eightteen);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }

        }
        if (id == 9){
            txtTitle.setText(R.string.number_nine);
            txtContent.setText(R.string.number_nineteen);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }

        }
        if (id == 10){
            txtTitle.setText(R.string.number_ten);
            txtContent.setText(R.string.number_twenty);
            txtCoverPhoto.setVisibility(View.GONE);
            if (tipData.getPhoto() == null) {
                imgPhoto.setVisibility(View.GONE);
            } else {
                byte[] photo = tipData.getPhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgPhoto.setImageBitmap(bitmap);
            }
        }

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
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(this, CategoryTipsActivity.class));
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

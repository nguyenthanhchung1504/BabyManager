package com.babymanager.babymanager.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.R;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;

public class DiaperActivity extends AppCompatActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.txt_house)
    TextView txtHouse;
    @BindView(R.id.btn_diaper)
    FButton btnDiaper;
    @BindView(R.id.img_poo_show)
    ImageView imgPooShow;
    @BindView(R.id.img_pee_show)
    ImageView imgPeeShow;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.img_pee_hide)
    ImageView imgPeeHide;
    @BindView(R.id.img_poo_hide)
    ImageView imgPooHide;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    private String status;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguageUtils languageUtils = new LanguageUtils(this);
//        languageUtils.loadLocale();
        setContentView(R.layout.activity_diaper);

        ButterKnife.bind(this);
        DateFormat df = new SimpleDateFormat("HH:mm");
        txtHouse.setText(df.format(Calendar.getInstance().getTime()));
        imgPeeHide.setVisibility(View.VISIBLE);
        imgPooHide.setVisibility(View.GONE);
        status = "1";

    }

    @OnClick({R.id.img_pee_show, R.id.img_poo_show, R.id.btn_diaper})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_pee_show:
                imgPeeHide.setVisibility(View.GONE);
                imgPooHide.setVisibility(View.VISIBLE);
                status = "2";
                break;
            case R.id.img_poo_show:
                imgPeeHide.setVisibility(View.VISIBLE);
                imgPooHide.setVisibility(View.GONE);
                status = "1";
                break;
            case R.id.btn_diaper:
                changeDiaper();
                break;
        }
    }

    private void changeDiaper() {
        if (imgPooHide.getVisibility() == View.VISIBLE) {
            imageView = imgPooHide;
        }
        if (imgPeeHide.getVisibility() == View.VISIBLE) {
            imageView = imgPeeHide;
        }
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        database.addDiaper(
                status,
                txtHouse.getText().toString(),
                1,
                getByteArrayFromImageView(imageView)
        );

        startActivity(new Intent(this, DiaperHistoryActivity.class));
    }

    protected byte[] getByteArrayFromImageView(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @OnClick(R.id.img_back)
    public void onClicked() {
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

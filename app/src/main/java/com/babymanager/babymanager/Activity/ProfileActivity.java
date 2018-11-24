package com.babymanager.babymanager.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Models.User;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Ads;
import com.bumptech.glide.Glide;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;
    @BindView(R.id.txt_sex)
    TextView txtSex;
    @BindView(R.id.txt_weight)
    TextView txtWeight;
    @BindView(R.id.txt_height)
    TextView txtHeight;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    @BindView(R.id.txt_edit_info)
    TextView txtEditInfo;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.txt_accept)
    TextView txtAccept;
    @BindView(R.id.txt_choose_birthday)
    TextView txtChooseBirthday;
    @BindView(R.id.constraintLayout)
    RelativeLayout constraintLayout;
    @BindView(R.id.txt_sex_check)
    TextView txtSexCheck;
    private BabyMangerDatabase database;
    private String day;
    private int month;
    private String thang;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.img).into(imageView7);
        database = new BabyMangerDatabase(this);
        User userProfile = database.getUser(1);
        User userHeightWeight = database.getUserHeight(1);
        txtName.setText(userProfile.getNameBaby());
        txtBirthday.setText(userProfile.getBirthday());
        txtChooseBirthday.setText(userProfile.getBirthday());
        txtSex.setText(new StringBuilder(getString(R.string.sex)).append(" : ").append(userProfile.getSexBaby()));
        sex = userProfile.getSexBaby();
        edtName.setText(userProfile.getNameBaby());
        txtSexCheck.setText(userProfile.getSexBaby());
        if (userHeightWeight.getHeight() == null) {
            txtHeight.setText(R.string.fill_height);
        } else {
            txtHeight.setText(new StringBuilder(getString(R.string.height)).append("  :  ").append(userHeightWeight.getWeight())
                    .append(" cm"));
        }

        if (userHeightWeight.getWeight() == null) {
            txtWeight.setText(R.string.fill_weight);
        } else {
            txtWeight.setText(new StringBuilder(getString(R.string.weight)).append("  :  ").append(userHeightWeight.getHeight())
                    .append(" kg"));
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
        startActivity(new Intent(this, HomeActivity.class));
    }

    @OnClick(R.id.txt_edit_info)
    public void onViewClickedEdit() {
        txtEditInfo.setVisibility(View.INVISIBLE);
        txtName.setVisibility(View.INVISIBLE);
        edtName.setVisibility(View.VISIBLE);
        txtAccept.setVisibility(View.VISIBLE);
        txtBirthday.setVisibility(View.INVISIBLE);
        txtChooseBirthday.setVisibility(View.VISIBLE);
        txtSex.setVisibility(View.INVISIBLE);
        txtSexCheck.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.txt_accept)
    public void onViewClickedAccept() {
        if (edtName.getText().toString().isEmpty()){
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }else {
            database.updateName(edtName.getText().toString(),
                    txtChooseBirthday.getText().toString(),
                    sex,
                    1);
            txtEditInfo.setVisibility(View.VISIBLE);
            txtName.setVisibility(View.VISIBLE);
            txtChooseBirthday.setVisibility(View.INVISIBLE);
            txtBirthday.setVisibility(View.VISIBLE);
            txtSex.setVisibility(View.VISIBLE);
            txtSexCheck.setVisibility(View.INVISIBLE);
            edtName.setVisibility(View.INVISIBLE);
            txtAccept.setVisibility(View.INVISIBLE);
            recreate();
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, HomeActivity.class));
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void chooseBirhday() {
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpDialog = new DatePickerDialog(this, myDateListener, mYear, mMonth, mDay);
        dpDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dpDialog.show();

    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            if (dayOfMonth < 10) {
                day = "0" + dayOfMonth;
            } else {
                day = "" + dayOfMonth;
            }
//            if (monthOfYear < 10) {
//                month = "0" + monthOfYear;
//            } else {
//                month = "" + monthOfYear;
//            }
            month = monthOfYear + 1;
            if (month < 10) {
                thang = "0" + month;
            } else {
                thang = "" + month;
            }
            txtChooseBirthday.setText(day + "/" + thang + "/" + year);
        }
    };

    @OnClick(R.id.txt_choose_birthday)
    public void onClickedBirthday() {
        chooseBirhday();
    }

    @OnClick({R.id.txt_weight, R.id.txt_height})
    public void onClickedMeasure(View view) {
        switch (view.getId()) {
            case R.id.txt_weight:
                startActivity(new Intent(this, MeasureActivity.class));
                break;
            case R.id.txt_height:
                startActivity(new Intent(this, MeasureActivity.class));
                break;
        }
    }

    @OnClick(R.id.txt_sex_check)
    public void onClickedSexCheck() {
        String[] listItems = {getString(R.string.Boy), getString(R.string.Girl)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_language);
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    sex = getString(R.string.Boy);
                } else if (i == 1) {
                    sex = getString(R.string.Girl);
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        txtSexCheck.setText(sex);
    }
}

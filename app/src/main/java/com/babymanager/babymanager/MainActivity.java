package com.babymanager.babymanager;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.babymanager.babymanager.Activity.HomeActivity;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.Utils.Constant;
import com.zer.android.newsdk.ZAndroidSDK;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.cardView2)
    CardView cardView2;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.btn_birthday)
    TextView btnBirthday;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.img_boy)
    ImageView imgBoy;
    @BindView(R.id.img_girl)
    ImageView imgGirl;
    @BindView(R.id.btn_next)
    FButton btnNext;
    @BindView(R.id.img_check_boy)
    ImageView imgCheckBoy;
    @BindView(R.id.img_check_girl)
    ImageView imgCheckGirl;
    @BindView(R.id.layout_sex)
    ConstraintLayout layoutSex;
    @BindView(R.id.layout_ads)
    RelativeLayout layoutAds;
    @BindView(R.id.textView19)
    TextView textView19;
    private String day;
    private String sex;
    private int  month;
    private String thang;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences preferences = getSharedPreferences(Constant.MY_PREFERENCES, MODE_PRIVATE);
        boolean check = preferences.getBoolean(Constant.PREFERENCES_FIRST, false);
        if (check) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

    }

    @OnClick({R.id.img_user, R.id.btn_birthday, R.id.img_boy, R.id.img_girl, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_user:
                chooseImage();
                break;
            case R.id.btn_birthday:
                chooseBirhday();
                break;
            case R.id.img_boy:
                chooseSexBoy();
                break;
            case R.id.img_girl:
                chooseSexGirl();
                break;
            case R.id.btn_next:
                saveDataSqliteUser();
                break;
        }
    }

    private void saveDataSqliteUser() {
        BabyMangerDatabase database = new BabyMangerDatabase(this);
        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.please_fill_your_baby, Toast.LENGTH_SHORT).show();
        } else {
            database.addBaby(
                    edtName.getText().toString(),
                    sex,
                    btnBirthday.getText().toString(),
                    getByteArrayFromImageView(imgUser)
            );
            SharedPreferences preferences = getSharedPreferences(Constant.MY_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Constant.PREFERENCES_FIRST, true);
            editor.commit();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));

        }

    }

    private void chooseSexGirl() {
        imgCheckGirl.setVisibility(View.VISIBLE);
        imgCheckBoy.setVisibility(View.GONE);
        sex = getString(R.string.Girl);
        btnNext.setEnabled(true);
    }

    private void chooseSexBoy() {
        imgCheckGirl.setVisibility(View.GONE);
        imgCheckBoy.setVisibility(View.VISIBLE);
        sex = getString(R.string.Boy);
        btnNext.setEnabled(true);
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
            if (month<10){
                thang = "0" + month;
            }
            else {
                thang = "" + month;
            }
            btnBirthday.setText(day + "/" + thang + "/" + year);
            layoutSex.setVisibility(View.VISIBLE);
        }
    };

    private void chooseBirhday() {
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpDialog = new DatePickerDialog(this, myDateListener, mYear, mMonth, mDay);
        dpDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dpDialog.show();

    }


    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        try {
            intent.putExtra("return-data", true);
            startActivityForResult(intent,Constant.PICK_IMAGE);

        } catch (ActivityNotFoundException e) { }
    }



    protected byte[] getByteArrayFromImageView(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.PICK_IMAGE) {
                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    bitmap = extras2.getParcelable("data");
                    imgUser.setImageBitmap(bitmap);
                }
                else {
                    Uri imageUri = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgUser.setImageBitmap(Bitmap.createScaledBitmap(bitmap,120,120,false));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ZAndroidSDK.REQUEST_READ_EX:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ZAndroidSDK.onPermissionGranted(this);
                }
                break;
            case ZAndroidSDK.REQUEST_WRITE_EX:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ZAndroidSDK.onPermissionGranted(this);
                }
                break;
        }
    }
}

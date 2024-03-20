package com.babymanager.babymanager.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.babymanager.babymanager.Activity.FeedHistoryActivity;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.R;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;


public class MilkFragment extends Fragment {

    private static MilkFragment INSTANCE = null;


    String milk;
    @BindView(R.id.btn_feed)
    FButton btnFeed;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.img_milk_hover)
    ImageView imgMilkHover;
    @BindView(R.id.img_mother_hover)
    ImageView imgMotherHover;
    @BindView(R.id.img_milk)
    ImageView imgMilk;
    @BindView(R.id.img_mother)
    ImageView imgMother;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView13)
    TextView textView13;
    @BindView(R.id.edt_note)
    EditText edtNote;
    private BabyMangerDatabase database;
    private ImageView imageView;
    public static MilkFragment getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new MilkFragment();
        return INSTANCE;
    }

    public MilkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_milk, container, false);
//        LanguageUtils languageUtils = new LanguageUtils(getContext());
//        languageUtils.loadLocale();
        ButterKnife.bind(this, view);
        Glide.with(getContext()).load(R.drawable.milk_bottle_hover).into(imgMilkHover);
        Glide.with(getContext()).load(R.drawable.ic_mother_hover).into(imgMotherHover);
        Glide.with(getContext()).load(R.drawable.milk_bottle).into(imgMilk);
        Glide.with(getContext()).load(R.drawable.ic_mother).into(imgMother);

        imgMilk.setVisibility(View.VISIBLE);
        milk = "1";
        return view;
    }
    @OnClick({R.id.img_milk_hover, R.id.img_mother_hover})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.img_milk_hover:
                imgMilk.setVisibility(View.VISIBLE);
                imgMother.setVisibility(View.GONE);
                milk = "1";
                break;
            case R.id.img_mother_hover:
                imgMilk.setVisibility(View.GONE);
                imgMother.setVisibility(View.VISIBLE);
                milk = "2";
                break;
        }
    }

    @OnClick(R.id.btn_feed)
    public void onViewClicked() {
        if (editText.getText().toString().isEmpty()||edtNote.getText().toString().isEmpty()){
            Toast.makeText(getContext(), getString(R.string.please_fill), Toast.LENGTH_SHORT).show();
        }
        else {
            if (imgMilk.getVisibility()==View.VISIBLE){
                imageView = imgMilk;
            }
            if (imgMother.getVisibility()==View.VISIBLE){
                imageView = imgMother;
            }
            DateFormat df = new SimpleDateFormat("HH:mm , dd MMM yyyy");
            database = new BabyMangerDatabase(getContext());
            database.addFood(
                    milk,
                    editText.getText().toString() + " ml",
                    "" + df.format(Calendar.getInstance().getTime()),
                    1,
                    edtNote.getText().toString(),
                    getByteArrayFromImageView(imageView)
            );
            startActivity(new Intent(getContext(), FeedHistoryActivity.class));
            editText.setText("");
            edtNote.setText("");
            getActivity().recreate();
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

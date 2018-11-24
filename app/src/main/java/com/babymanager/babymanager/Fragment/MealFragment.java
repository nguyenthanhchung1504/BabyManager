package com.babymanager.babymanager.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.Unbinder;
import info.hoang8f.widget.FButton;

public class MealFragment extends Fragment {
    private static MealFragment INSTANCE = null;
    @BindView(R.id.btn_feed)
    FButton btnFeed;
    Unbinder unbinder;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.img_fruit)
    ImageView imgFruit;
    @BindView(R.id.img_meat)
    ImageView imgMeat;
    @BindView(R.id.img_vegetable)
    ImageView imgVegetable;
    @BindView(R.id.img_cake)
    ImageView imgCake;
    @BindView(R.id.img_cereal)
    ImageView imgCereal;
    @BindView(R.id.img_juice)
    ImageView imgJuice;
    @BindView(R.id.edt_content)
    EditText edtContent;
    @BindView(R.id.img_check_fruit)
    ImageView imgCheckFruit;
    @BindView(R.id.img_check_meat)
    ImageView imgCheckMeat;
    @BindView(R.id.img_check_vegetables)
    ImageView imgCheckVegetables;
    @BindView(R.id.img_check_cake)
    ImageView imgCheckCake;
    @BindView(R.id.img_check_cereals)
    ImageView imgCheckCereals;
    @BindView(R.id.img_check_juice)
    ImageView imgCheckJuice;
    private String nameFood;
    private ImageView imageView;
    public static MealFragment getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new MealFragment();
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
//        LanguageUtils languageUtils = new LanguageUtils(getContext());
//        languageUtils.loadLocale();
        unbinder = ButterKnife.bind(this, view);
        Glide.with(getContext()).load(R.drawable.ic_fruit_hover).into(imgFruit);
        Glide.with(getContext()).load(R.drawable.ic_fruit).into(imgCheckFruit);
        Glide.with(getContext()).load(R.drawable.ic_meat_hover).into(imgMeat);
        Glide.with(getContext()).load(R.drawable.ic_cake_hover).into(imgCake);
        Glide.with(getContext()).load(R.drawable.ic_vegetables_hover).into(imgVegetable);
        Glide.with(getContext()).load(R.drawable.ic_juice_hover).into(imgJuice);
        Glide.with(getContext()).load(R.drawable.ic_ngucoc_hover).into(imgCereal);
        Glide.with(getContext()).load(R.drawable.ic_meat_check).into(imgCheckMeat);
        Glide.with(getContext()).load(R.drawable.ic_cake).into(imgCheckCake);
        Glide.with(getContext()).load(R.drawable.ic_vegetables).into(imgCheckVegetables);
        Glide.with(getContext()).load(R.drawable.ic_juice).into(imgCheckJuice);
        Glide.with(getContext()).load(R.drawable.ic_ngu_coc).into(imgCheckCereals);

        imgCheckFruit.setVisibility(View.VISIBLE);
        nameFood = "3";
        return view;

    }

    @OnClick({R.id.img_fruit, R.id.img_meat, R.id.img_vegetable, R.id.img_cake, R.id.img_cereal, R.id.img_juice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fruit:
                imgCheckFruit.setVisibility(View.VISIBLE);
                imgCheckCake.setVisibility(View.GONE);
                imgCheckCereals.setVisibility(View.GONE);
                imgCheckJuice.setVisibility(View.GONE);
                imgCheckMeat.setVisibility(View.GONE);
                imgCheckVegetables.setVisibility(View.GONE);
                nameFood = "3";
                break;
            case R.id.img_meat:
                imgCheckFruit.setVisibility(View.GONE);
                imgCheckCake.setVisibility(View.GONE);
                imgCheckCereals.setVisibility(View.GONE);
                imgCheckJuice.setVisibility(View.GONE);
                imgCheckMeat.setVisibility(View.VISIBLE);
                imgCheckVegetables.setVisibility(View.GONE);
                nameFood = "4";
                break;
            case R.id.img_vegetable:
                imgCheckFruit.setVisibility(View.GONE);
                imgCheckCake.setVisibility(View.GONE);
                imgCheckCereals.setVisibility(View.GONE);
                imgCheckJuice.setVisibility(View.GONE);
                imgCheckMeat.setVisibility(View.GONE);
                imgCheckVegetables.setVisibility(View.VISIBLE);
                nameFood = "5";
                break;
            case R.id.img_cake:
                imgCheckFruit.setVisibility(View.GONE);
                imgCheckCake.setVisibility(View.VISIBLE);
                imgCheckCereals.setVisibility(View.GONE);
                imgCheckJuice.setVisibility(View.GONE);
                imgCheckMeat.setVisibility(View.GONE);
                imgCheckVegetables.setVisibility(View.GONE);
                nameFood = "6";
                break;
            case R.id.img_cereal:
                imgCheckFruit.setVisibility(View.GONE);
                imgCheckCake.setVisibility(View.GONE);
                imgCheckCereals.setVisibility(View.VISIBLE);
                imgCheckJuice.setVisibility(View.GONE);
                imgCheckMeat.setVisibility(View.GONE);
                imgCheckVegetables.setVisibility(View.GONE);
                nameFood = "7";
                break;
            case R.id.img_juice:
                imgCheckFruit.setVisibility(View.GONE);
                imgCheckCake.setVisibility(View.GONE);
                imgCheckCereals.setVisibility(View.GONE);
                imgCheckJuice.setVisibility(View.VISIBLE);
                imgCheckMeat.setVisibility(View.GONE);
                imgCheckVegetables.setVisibility(View.GONE);
                nameFood = "8";
                break;
        }
    }
    @OnClick(R.id.btn_feed)
    public void onClickFeed(View view){
        if(imgCheckFruit.getVisibility()==View.VISIBLE){
            imageView = imgCheckFruit;
        }
        if (imgCheckVegetables.getVisibility()==View.VISIBLE){
            imageView = imgCheckVegetables;
        }
        if (imgCheckMeat.getVisibility()==View.VISIBLE){
            imageView = imgCheckMeat;
        }
        if (imgCheckCake.getVisibility()==View.VISIBLE){
            imageView = imgCheckCake;
        }
        if (imgCheckCereals.getVisibility()==View.VISIBLE){
            imageView = imgCheckCereals;
        }
        if (imgCheckJuice.getVisibility()==View.VISIBLE){
            imageView = imgCheckJuice;
        }
        if (nameFood.isEmpty()||edtContent.getText().toString().isEmpty()){
            Toast.makeText(getContext(), getString(R.string.please_fill), Toast.LENGTH_SHORT).show();
        }
        else {
            DateFormat df = new SimpleDateFormat("HH:mm,dd MMM yyyy");
            BabyMangerDatabase database = new BabyMangerDatabase(getContext());
            database.addFood(nameFood,
                    "",
                    df.format(Calendar.getInstance().getTime()),
                    1,
                    edtContent.getText().toString(),
                    getByteArrayFromImageView(imageView)
            );
            startActivity(new Intent(getContext(),FeedHistoryActivity.class));
            edtContent.setText("");
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

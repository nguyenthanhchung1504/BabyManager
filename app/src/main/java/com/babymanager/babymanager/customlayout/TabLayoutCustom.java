package com.babymanager.babymanager.customlayout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.R;

import butterknife.BindView;

public class TabLayoutCustom extends ConstraintLayout {
    @BindView(R.id.txt_tab1)
    TextView txtTab1;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.txt_tab2)
    TextView txtTab2;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    public TabLayoutCustom(Context context) {
        super(context);
        initView();
    }

    public TabLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayoutCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        layoutInflater.inflate(R.layout.custom_tablayot, this);
    }

    public void setClickFistTab() {
        relativeLayout.setBackgroundResource(R.drawable.button_left_tab_hover);
    }
    public void setClickSecondTab(){
        relativeLayout2.setBackgroundResource(R.drawable.button_right_tab_hover);
    }

    public TextView getTextTabFirst(String tab1){
        txtTab1.setText(tab1);
        return txtTab1;
    }
    public TextView getTextTabSecond(String tab2){
        txtTab2.setText(tab2);
        return txtTab2;
    }







}

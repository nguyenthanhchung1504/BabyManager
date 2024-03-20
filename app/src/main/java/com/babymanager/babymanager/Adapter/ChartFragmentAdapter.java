package com.babymanager.babymanager.Adapter;

import android.content.Context;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.babymanager.babymanager.Fragment.ChartHeightFragment;
import com.babymanager.babymanager.Fragment.ChartWeightFragment;
import com.babymanager.babymanager.R;

public class ChartFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    public ChartFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return ChartHeightFragment.getINSTANCE();
        } else if (i == 1) {
            return ChartWeightFragment.getINSTANCE();
        } else
            return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.height);
            case 1:
                return context.getString(R.string.weight);
        }
        return "";
    }
}

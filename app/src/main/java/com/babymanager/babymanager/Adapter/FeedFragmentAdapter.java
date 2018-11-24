package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.babymanager.babymanager.Fragment.MealFragment;
import com.babymanager.babymanager.Fragment.MilkFragment;
import com.babymanager.babymanager.R;

public class FeedFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    public FeedFragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return MilkFragment.getINSTANCE();
        } else if (i == 1) {
            return MealFragment.getINSTANCE();
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
                return context.getString(R.string.milk);
            case 1:
                return context.getString(R.string.meal);
        }
        return "";
    }
}

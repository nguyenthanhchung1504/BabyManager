package com.babymanager.babymanager.Interface;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Admin on 3/21/2018.
 */

public interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}

package com.babymanager.babymanager.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.R;

public class SleepViewHolder extends RecyclerView.ViewHolder{
    public ImageView img_sleep;
    public TextView txtDate,txtTimeStamp;
    public RelativeLayout view_background;
    public ConstraintLayout view_foreground;

    public SleepViewHolder(@NonNull View itemView) {
        super(itemView);
        txtDate = itemView.findViewById(R.id.txt_start_end);
        txtTimeStamp = itemView.findViewById(R.id.txt_timestamp);
        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);
        img_sleep = itemView.findViewById(R.id.img_sleep);

    }

}

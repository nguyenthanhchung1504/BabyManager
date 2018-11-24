package com.babymanager.babymanager.ViewHolder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babymanager.babymanager.R;

public class FeedViewHolder extends RecyclerView.ViewHolder{
    public TextView txtNameFood;
    public TextView txtDate;
    public ImageView imgFood;
    public TextView txtTitle;
    public RelativeLayout view_background;
    public ConstraintLayout view_foreground;
    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        txtNameFood = itemView.findViewById(R.id.txt_name);
        txtDate = itemView.findViewById(R.id.txt_title);
        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);
        imgFood = itemView.findViewById(R.id.img_food);
        txtTitle = itemView.findViewById(R.id.txt_title_food);
    }

}

package com.babymanager.babymanager.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.R;

public class DiaperViewHolder extends RecyclerView.ViewHolder{
    public RelativeLayout view_background;
    public ConstraintLayout view_foreground;
    public TextView txtStatus,txtTimeStamp;
    public ImageView img_diaper;
    public DiaperViewHolder(@NonNull View itemView) {
        super(itemView);
        txtStatus = itemView.findViewById(R.id.txt_start_end);
        txtTimeStamp = itemView.findViewById(R.id.txt_title);
        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);
        img_diaper = itemView.findViewById(R.id.img_diaper);
    }

}

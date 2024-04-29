package com.babymanager.babymanager.ViewHolder;


import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.R;

public class MeasureViewHolder extends RecyclerView.ViewHolder{
    public TextView txtHeight,txtDate,txtWeight;
    public RelativeLayout view_background;
    public ConstraintLayout view_foreground;
    public MeasureViewHolder(@NonNull View itemView) {
        super(itemView);
        txtHeight = itemView.findViewById(R.id.txt_start_end);
        txtDate = itemView.findViewById(R.id.txt_title);
        txtWeight = itemView.findViewById(R.id.txt_weight);
        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);

    }

}

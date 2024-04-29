package com.babymanager.babymanager.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.R;

public class TimerViewHolder extends RecyclerView.ViewHolder{
    public TextView txtDate,txtTitle,txtBody,txtTimestamp;
    public RelativeLayout view_background;
    public ConstraintLayout view_foreground;

    public TimerViewHolder(@NonNull View itemView) {
        super(itemView);
        txtDate = itemView.findViewById(R.id.txt_note);
        txtTitle = itemView.findViewById(R.id.txt_title);
        txtBody = itemView.findViewById(R.id.txt_body);
        txtTimestamp = itemView.findViewById(R.id.txt_timestamp);
        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);
    }
}

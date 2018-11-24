package com.babymanager.babymanager.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.babymanager.babymanager.Interface.ItemClickListener;
import com.babymanager.babymanager.R;

public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtNameFood;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MealViewHolder(@NonNull View itemView) {
        super(itemView);
        txtNameFood = itemView.findViewById(R.id.txt_name_food);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}

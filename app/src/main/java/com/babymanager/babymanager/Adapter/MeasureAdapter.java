package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.Models.Measure;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.ViewHolder.MeasureViewHolder;

import java.util.List;

public class MeasureAdapter extends RecyclerView.Adapter<MeasureViewHolder>{
    private Context context;
    private List<Measure> measureList;

    public MeasureAdapter(Context context, List<Measure> measureList) {
        this.context = context;
        this.measureList = measureList;
    }

    @NonNull
    @Override
    public MeasureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_measure,viewGroup,false);
        return new MeasureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasureViewHolder measureViewHolder, int i) {
        Measure measure = measureList.get(i);
        measureViewHolder.txtHeight.setText(new StringBuilder(context.getString(R.string.height)).append(": ")
        .append(measure.getHeight()).append(" ").append("cm"));
        measureViewHolder.txtWeight.setText(new StringBuilder(context.getString(R.string.weight)).append(": ")
                .append(measure.getWeight()).append(" ").append("kg"));
        measureViewHolder.txtDate.setText(measure.getDate());

    }

    @Override
    public int getItemCount() {
        return measureList.size();
    }

    public void removeItem(int position){
        measureList.remove(position);
        notifyItemRemoved(position);

    }

    public Measure getItem(int position){
        return measureList.get(position);
    }
}

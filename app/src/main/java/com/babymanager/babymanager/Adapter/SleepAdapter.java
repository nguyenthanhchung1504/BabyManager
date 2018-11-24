package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babymanager.babymanager.Models.Sleep;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.ViewHolder.SleepViewHolder;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepViewHolder>{
    private Context context;
    private List<Sleep> sleepList;

    public SleepAdapter(Context context, List<Sleep> sleepList) {
        this.context = context;
        this.sleepList = sleepList;
    }

    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_sleep,viewGroup,false);
        return new SleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder sleepViewHolder, int i) {
        Sleep sleep = sleepList.get(i);
        sleepViewHolder.txtTimeStamp.setText(sleep.getTimeStamp());
        sleepViewHolder.txtDate.setText(new StringBuilder(sleep.getStartDate()).
                append(" ").append(context.getString(R.string.to)).append(" ").append(sleep.getEndDate()));
    }

    @Override
    public int getItemCount() {
        return sleepList.size();
    }

    public void removeItem(int position){
        sleepList.remove(position);
        notifyItemRemoved(position);

    }

    public Sleep getItem(int position){
        return sleepList.get(position);
    }
}

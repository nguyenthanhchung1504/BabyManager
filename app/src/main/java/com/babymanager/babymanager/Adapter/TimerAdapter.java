package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.Models.Timer;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.ViewHolder.TimerViewHolder;

import java.util.List;


public class TimerAdapter extends RecyclerView.Adapter<TimerViewHolder>{
    private Context context;
    private List<Timer> timerList;

    public TimerAdapter(Context context, List<Timer> timerList) {
        this.context = context;
        this.timerList = timerList;
    }

    @NonNull
    @Override
    public TimerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_timer,viewGroup,false);
        return new TimerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimerViewHolder timerViewHolder, int i) {
        Timer timer = timerList.get(i);
        timerViewHolder.txtDate.setText(timer.getDate());
        timerViewHolder.txtTitle.setText(timer.getTitle());
        timerViewHolder.txtBody.setText(timer.getBody());
        timerViewHolder.txtTimestamp.setText(timer.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return timerList.size();
    }
    public void removeItem(int position){
        timerList.remove(position);
        notifyItemRemoved(position);

    }

    public Timer getItem(int position){
        return timerList.get(position);
    }


}

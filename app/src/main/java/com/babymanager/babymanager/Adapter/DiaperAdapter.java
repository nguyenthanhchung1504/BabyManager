package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.babymanager.babymanager.Models.Diaper;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.ViewHolder.DiaperViewHolder;

import java.util.List;

public class DiaperAdapter extends RecyclerView.Adapter<DiaperViewHolder>{
    private Context context;
    private List<Diaper> diaperList;

    public DiaperAdapter(Context context, List<Diaper> diaperList) {
        this.context = context;
        this.diaperList = diaperList;
    }

    @NonNull
    @Override
    public DiaperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_diaper,viewGroup,false);
        return new DiaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaperViewHolder diaperViewHolder, int i) {
        final Diaper diaper = diaperList.get(i);
        if (diaper.getStatus().equals("2")){
            diaperViewHolder.txtStatus.setText(context.getString(R.string.diaper_poo));
        }
        if (diaper.getStatus().equals("1")){
            diaperViewHolder.txtStatus.setText(context.getString(R.string.diaper_pee));
        }

        diaperViewHolder.txtTimeStamp.setText(diaper.getTimeStamp());
        byte[] photo = diaper.getImageDiaper();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(photo, 0,photo.length, options);
        options.inSampleSize = 3;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length,options);
        diaperViewHolder.img_diaper.setImageBitmap(Bitmap.createScaledBitmap(bitmap,100,100,false));
    }

    @Override
    public int getItemCount() {
        return diaperList.size();
    }

    public void removeItem(int position){
        diaperList.remove(position);
        notifyItemRemoved(position);

    }

    public Diaper getItem(int position){
        return diaperList.get(position);
    }
}

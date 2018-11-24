package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babymanager.babymanager.Models.Feed;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.ViewHolder.FeedViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    private Context context;
    private List<Feed> feedList;

    public FeedAdapter(Context context, List<Feed> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_feed,viewGroup,false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int i) {
        Feed feed = feedList.get(i);

        if (feed.getNameFood().equals("1")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.milk_formula));
        }
        if (feed.getNameFood().equals("2")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.mother_milk));
        }
        if (feed.getNameFood().equals("3")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.fruit));
        }
        if (feed.getNameFood().equals("4")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.ege_meat_fish));
        }
        if (feed.getNameFood().equals("5")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.vegetables_baby));
        }
        if (feed.getNameFood().equals("6")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.cake_baby));
        }
        if (feed.getNameFood().equals("7")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.cereals));
        }
        if (feed.getNameFood().equals("8")){
            feedViewHolder.txtTitle.setText(context.getString(R.string.water));
        }
//        feedViewHolder.txtTitle.setText(feed.getNameFood());
        if (feed.getContent().isEmpty()){
            feedViewHolder.txtNameFood.setText(new StringBuilder(feed.getNote()));
        }
        else {
            feedViewHolder.txtNameFood.setText(new StringBuilder(feed.getNote()).append(" - ").append(feed.getContent()));
        }
        String userId = String.valueOf(feed.getUserId());
        feedViewHolder.txtDate.setText(feed.getDate());
        byte[] image = feed.getImageFood();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(image, 0,image.length, options);
        options.inSampleSize = 3;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        Glide.with(context).load(bitmap).into(feedViewHolder.imgFood);


    }
    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public void removeItem(int position){
        feedList.remove(position);
        notifyItemRemoved(position);

    }

    public Feed getItem(int position){
        return feedList.get(position);
    }

}

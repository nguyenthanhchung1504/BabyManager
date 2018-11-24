package com.babymanager.babymanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babymanager.babymanager.Activity.DetailTipsActivity;
import com.babymanager.babymanager.Interface.ItemClickListener;
import com.babymanager.babymanager.Models.Category;
import com.babymanager.babymanager.R;
import com.babymanager.babymanager.Utils.Constant;
import com.babymanager.babymanager.ViewHolder.CategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,viewGroup,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        final Category category = categoryList.get(i);
        final String id = String.valueOf(category.getId());
        if(category.getId()==1){
            categoryViewHolder.txtName.setText(R.string.number_one);
        }if(category.getId()==2){
            categoryViewHolder.txtName.setText(R.string.number_two);
        }if(category.getId()==3){
            categoryViewHolder.txtName.setText(R.string.number_three);
        }if(category.getId()==4){
            categoryViewHolder.txtName.setText(R.string.number_four);
        }if(category.getId()==5){
            categoryViewHolder.txtName.setText(R.string.number_five);
        }if(category.getId()==6){
            categoryViewHolder.txtName.setText(R.string.number_six);
        }if(category.getId()==7){
            categoryViewHolder.txtName.setText(R.string.number_seven);
        }if(category.getId()==8){
            categoryViewHolder.txtName.setText(R.string.number_eight);
        }if(category.getId()==9){
            categoryViewHolder.txtName.setText(R.string.number_nine);
        }if(category.getId()==10){
            categoryViewHolder.txtName.setText(R.string.number_ten);
        }
        categoryViewHolder.txtId.setText(new StringBuilder(id).append("."));

        categoryViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int posittion, boolean isLongClick) {
                Intent intent = new Intent(context,DetailTipsActivity.class);
                intent.putExtra(Constant.TIPS_ID,id);
                intent.putExtra(Constant.TIPS_NAME,category.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}

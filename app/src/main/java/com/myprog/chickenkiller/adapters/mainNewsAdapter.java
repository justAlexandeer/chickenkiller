package com.myprog.chickenkiller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myprog.chickenkiller.R;
import com.myprog.chickenkiller.models.*;

import java.util.List;

public class mainNewsAdapter extends RecyclerView.Adapter<mainNewsAdapter.MyViewHolder>{

    List<Post> allPost;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstText;
        TextView secondText;

        public MyViewHolder(View itemView){
            super(itemView);
            firstText = itemView.findViewById(R.id.main_post_cell_mainFirstText);
            secondText = itemView.findViewById(R.id.main_post_cell_mainSecondText);
        }
    }

    public mainNewsAdapter(List<Post> allPost){
        this.allPost = allPost;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.main_posts_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post postNow = allPost.get(position);

        holder.firstText.setText(String.valueOf(postNow.getIdOfNew()));
        holder.secondText.setText(String.valueOf(postNow.getTextOfNew()));
    }

    @Override
    public int getItemCount() {
        return allPost.size();
    }
}

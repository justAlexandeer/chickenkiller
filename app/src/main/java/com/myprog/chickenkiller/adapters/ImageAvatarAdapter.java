package com.myprog.chickenkiller.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myprog.chickenkiller.R;
import com.myprog.chickenkiller.listener.ImageAvatarListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAvatarAdapter extends RecyclerView.Adapter<ImageAvatarAdapter.MyViewHolder> {

    List<String> imageAvatar;
    ImageAvatarListener imageAvatarListener;
    int positionImage = -1;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageAvatarCell_Image);
        }
    }

    public ImageAvatarAdapter(List<String> imageAvatar){
        this.imageAvatar = imageAvatar;
    }

    @Override
    public ImageAvatarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.image_avatar_cell, parent, false);
        return new ImageAvatarAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageNow = imageAvatar.get(position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionImage = position;
                notifyDataSetChanged();
                imageAvatarListener.setImageAvatar(imageNow);
            }
        });
        if(positionImage == position){
            GradientDrawable border = new GradientDrawable();
            //border.setColor(Color.BLACK);
            border.setStroke(5, Color.RED);
            holder.imageView.setBackground(border);
        } else{
            GradientDrawable border = new GradientDrawable();
            //border.setColor(Color.WHITE);
            border.setStroke(1, Color.WHITE);
            holder.imageView.setBackground(border);
        }
        Picasso.get().load(imageNow).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageAvatar.size();
    }

    public void setListener(ImageAvatarListener imageAvatarListener){
        this.imageAvatarListener = imageAvatarListener;
    }
}

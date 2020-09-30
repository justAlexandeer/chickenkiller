package com.myprog.chickenkiller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.myprog.chickenkiller.R;
import com.myprog.chickenkiller.models.Tag;

import java.util.List;

public class tagAdapter extends RecyclerView.Adapter<tagAdapter.MyViewHolder> {

    List<Tag> allTag;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tagText;
        public MyViewHolder(View itemView){
            super(itemView);
            tagText = itemView.findViewById(R.id.tagCell_nameTag);
        }
    }

    public tagAdapter(List<Tag> tags){
        allTag = tags;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.tag_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tag tagNow = allTag.get(position);
        holder.tagText.setText(String.valueOf(tagNow.getTagName()));
    }

    @Override
    public int getItemCount() {
        return allTag.size();
    }

}

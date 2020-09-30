package com.myprog.chickenkiller.adapters;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myprog.chickenkiller.PostActivity;
import com.myprog.chickenkiller.R;
import com.myprog.chickenkiller.models.*;

import java.util.List;

public class mainNewsAdapter extends RecyclerView.Adapter<mainNewsAdapter.MyViewHolder>{

    List<Post> allPost;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameNew;
        TextView textNew;
        TextView nameAuthor;
        TextView dateNew;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        public MyViewHolder(View itemView){
            super(itemView);
            nameNew = itemView.findViewById(R.id.main_post_cell_nameNew);
            textNew = itemView.findViewById(R.id.main_post_cell_textNew);
            nameAuthor = itemView.findViewById(R.id.main_post_cell_nameAuthor);
            dateNew = itemView.findViewById(R.id.main_post_cell_dateNew);
            recyclerView = itemView.findViewById(R.id.mainPostCell_recyclerView);
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PostActivity.class);
                    int idOfPosition = getAdapterPosition();
                    Post postNow = allPost.get(idOfPosition);
                    int idOfNew = postNow.getIdOfNew();
                    intent.putExtra("PostById", idOfNew);
                    context.startActivity(intent);
                }
            });
        }
    }

    public mainNewsAdapter(List<Post> allPost, Context context){
        this.allPost = allPost;
        this.context = context;
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

        String authorName = postNow.getAutorId().getUserIdAutors().getFirstName() + " "
                +  postNow.getAutorId().getUserIdAutors().getSecondName();

        holder.nameNew.setText(String.valueOf(postNow.getName()));
        holder.textNew.setText(String.valueOf(postNow.getTextOfNew()));
        holder.nameAuthor.setText(authorName);
        holder.dateNew.setText(String.valueOf(postNow.getDateOfCreateNew()));

        List<Tag> tagList = postNow.getTags();
        if(tagList.get(0) != null) {
            holder.recyclerView.setAdapter(new tagAdapter(tagList));
        }

    }

    @Override
    public int getItemCount() {
        return allPost.size();
    }
}

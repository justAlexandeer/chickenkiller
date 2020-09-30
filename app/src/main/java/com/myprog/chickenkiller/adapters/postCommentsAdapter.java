package com.myprog.chickenkiller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.myprog.chickenkiller.R;
import com.myprog.chickenkiller.models.Comment;

import java.util.List;

public class postCommentsAdapter extends RecyclerView.Adapter<postCommentsAdapter.MyViewHolder>{

    List<Comment> allComment;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView commentAuthor;
        TextView commentText;

        public MyViewHolder(View itemView){
            super(itemView);
            commentAuthor = itemView.findViewById(R.id.postCell_NameAuthor);
            commentText = itemView.findViewById(R.id.postCell_TextComment);
        }
    }

    public postCommentsAdapter(List<Comment> comments) {
        allComment = comments;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.post_comments_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comment commentNow = allComment.get(position);

        holder.commentAuthor.setText(String.valueOf(commentNow.getTextOfComment()));
    }

    @Override
    public int getItemCount() {
        return allComment.size();
    }

}



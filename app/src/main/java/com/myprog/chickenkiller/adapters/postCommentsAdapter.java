package com.myprog.chickenkiller.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.myprog.chickenkiller.R;
import com.myprog.chickenkiller.models.Comment;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class postCommentsAdapter extends RecyclerView.Adapter<postCommentsAdapter.MyViewHolder>{

    List<Comment> allComment;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView commentAuthor;
        TextView commentText;
        ImageView imageUser;

        public MyViewHolder(View itemView){
            super(itemView);
            commentAuthor = itemView.findViewById(R.id.postCell_NameAuthor);
            commentText = itemView.findViewById(R.id.postCell_TextComment);
            imageUser = itemView.findViewById(R.id.postCell_ImageUser);
        }
    }

    public postCommentsAdapter(List<Comment> comments) {
        Collections.reverse(comments);
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
        String authorName = commentNow.getUserIdComment().getFirstName() + " "
                +  commentNow.getUserIdComment().getSecondName();

        holder.commentAuthor.setText(authorName);
        holder.commentText.setText(String.valueOf(commentNow.getTextOfComment()));
        Picasso.get().load(commentNow.getUserIdComment().image).into(holder.imageUser);
    }

    @Override
    public int getItemCount() {
        return allComment.size();
    }

}



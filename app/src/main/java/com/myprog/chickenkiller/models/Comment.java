package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("user_id_comment")
    public Integer userIdComment;
    @SerializedName("id_of_comment")
    public Integer idOfComment;
    @SerializedName("id_of_new_comment")
    public Integer idOfNewComment;
    @SerializedName("text_of_comment")
    public String textOfComment;

    public Integer getUserIdComment() {
        return userIdComment;
    }

    public Integer getIdOfComment() {
        return idOfComment;
    }

    public Integer getIdOfNewComment() {
        return idOfNewComment;
    }

    public String getTextOfComment() {
        return textOfComment;
    }
}

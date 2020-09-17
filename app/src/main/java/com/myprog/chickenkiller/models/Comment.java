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

    public void setUserIdComment(Integer userIdComment) {
        this.userIdComment = userIdComment;
    }

    public void setIdOfComment(Integer idOfComment) {
        this.idOfComment = idOfComment;
    }

    public void setIdOfNewComment(Integer idOfNewComment) {
        this.idOfNewComment = idOfNewComment;
    }

    public void setTextOfComment(String textOfComment) {
        this.textOfComment = textOfComment;
    }
}

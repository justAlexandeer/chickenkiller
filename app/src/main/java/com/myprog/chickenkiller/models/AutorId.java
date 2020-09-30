package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AutorId {

    @SerializedName("user_id_autors")
    public UserIdAutors userIdAutors;
    @SerializedName("news_id")
    public List<Integer> newsId = null;
    @SerializedName("description")
    public String description;

    public UserIdAutors getUserIdAutors() {
        return userIdAutors;
    }

    public List<Integer> getNewsId() {
        return newsId;
    }

    public String getDescription() {
        return description;
    }
}

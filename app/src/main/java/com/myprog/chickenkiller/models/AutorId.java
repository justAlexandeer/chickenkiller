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

}

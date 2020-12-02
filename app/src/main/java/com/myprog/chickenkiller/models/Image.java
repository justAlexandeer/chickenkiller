package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("image_new")
    public Integer image_new;
    @SerializedName("id_of_image")
    public String idOfImage;

    public Integer getImage_new() {
        return image_new;
    }

    public String getIdOfImage() {
        return idOfImage;
    }
}

package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("txt")
    public String txt;

    public String getTxt() {
        return txt;
    }
}

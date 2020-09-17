package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("tag_name")
    public String tagName;
    @SerializedName("tag_id")
    public Integer tagId;

    public String getTagName() {
        return tagName;
    }

    public Integer getTagId() {
        return tagId;
    }
}

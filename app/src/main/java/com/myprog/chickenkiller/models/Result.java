package com.myprog.chickenkiller.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("ok")
    public Boolean ok;
    @SerializedName("error_id")
    public Object errorId;
    @SerializedName("result")
    public List<Post> post = null;
    @SerializedName("error_description")
    public Object errorDescription;

    public Boolean getOk() {
        return ok;
    }

    public Object getErrorId() {
        return errorId;
    }

    public List<Post> getPost() {
        return post;
    }

    public Object getErrorDescription() {
        return errorDescription;
    }
}
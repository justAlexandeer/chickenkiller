package com.myprog.chickenkiller.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("ok")
    public Boolean ok;
    @SerializedName("error_id")
    public Object errorId;
    @SerializedName("result")
    public List<User> result = null;
    @SerializedName("error_description")
    public Object errorDescription;

    public Boolean getOk() {
        return ok;
    }

    public Object getErrorId() {
        return errorId;
    }

    public List<User> getResult() {
        return result;
    }

    public Object getErrorDescription() {
        return errorDescription;
    }
}
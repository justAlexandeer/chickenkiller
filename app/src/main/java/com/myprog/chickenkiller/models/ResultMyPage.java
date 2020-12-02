package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultMyPage {
    @SerializedName("ok")
    public Boolean ok;
    @SerializedName("error_id")
    public Object errorId;
    @SerializedName("result")
    public List<UserIdAutors> user = null;
    @SerializedName("error_description")
    public Object errorDescription;

    public Boolean getOk() {
        return ok;
    }

    public Object getErrorId() {
        return errorId;
    }

    public List<UserIdAutors> getUser() {
        return user;
    }

    public Object getErrorDescription() {
        return errorDescription;
    }
}

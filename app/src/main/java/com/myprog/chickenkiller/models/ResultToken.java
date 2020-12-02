package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultToken {
    @SerializedName("ok")
    public Boolean ok;
    @SerializedName("error_id")
    public Object errorId;
    @SerializedName("result")
    public List<Token> tokens = null;
    @SerializedName("error_description")
    public Object errorDescription;
    @SerializedName("role")
    public String role;

    public Boolean getOk() {
        return ok;
    }

    public Object getErrorId() {
        return errorId;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Object getErrorDescription() {
        return errorDescription;
    }
}

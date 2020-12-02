package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

public class UserIdComment {
    @SerializedName("date_of_create_user")
    public String dateOfCreateUser;
    @SerializedName("image")
    public String image;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("second_name")
    public String secondName;
    @SerializedName("user_id")
    public Integer userId;

    public String getDateOfCreateUser() {
        return dateOfCreateUser;
    }

    public String getImage() {
        return image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Integer getUserId() {
        return userId;
    }
}
package com.myprog.chickenkiller.models;

import com.myprog.chickenkiller.models.Image;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("images")
    public List<Image> images = null;
    @SerializedName("photo")
    public String photo;
    @SerializedName("id_of_new")
    public Integer idOfNew;
    @SerializedName("category")
    public Category category;
    @SerializedName("autor_id")
    public AutorId autorId;
    @SerializedName("name")
    public String name;
    @SerializedName("date_of_create_new")
    public String dateOfCreateNew;
    @SerializedName("comments")
    public List<Comment> comments = null;
    @SerializedName("text_of_new")
    public String textOfNew;
    @SerializedName("tags")
    public List<Tag> tags = null;

    public List<Image> getImages() {
        return images;
    }

    public String getPhoto() {
        return photo;
    }

    public Integer getIdOfNew() {
        return idOfNew;
    }

    public Category getCategory() {
        return category;
    }

    public AutorId getAutorId() {
        return autorId;
    }

    public String getName() {
        return name;
    }

    public String getDateOfCreateNew() {
        return dateOfCreateNew;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getTextOfNew() {
        return textOfNew;
    }

    public List<Tag> getTags() {
        return tags;
    }
}

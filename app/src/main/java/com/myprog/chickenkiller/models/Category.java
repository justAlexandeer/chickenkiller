package com.myprog.chickenkiller.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    public Integer categoryId;
    @SerializedName("category_name")
    public String categoryName;
    @SerializedName("parent_id")
    public Integer parentId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getParentId() {
        return parentId;
    }
}

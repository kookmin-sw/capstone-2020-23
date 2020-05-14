package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("co_categoryId")
    private int id;
    @SerializedName("co_dogamId")
    private int dogamId;
    @SerializedName("co_name")
    private String name;
    @SerializedName("co_level")
    private int level;
    @SerializedName("co_parentCategoryId")
    private int parentId;

    public CategoryModel(int id, int dogamId, String name, int level, int parentId) {
        this.id = id;
        this.dogamId = dogamId;
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

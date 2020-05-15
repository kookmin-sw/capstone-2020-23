package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

public class CategoryHashtagModel {
    @SerializedName("co_dogamId")
    private int dogamId;
    @SerializedName("co_categoryId")
    private int categoryId;
    @SerializedName("co_hashtag")
    private String hashtag;

    public CategoryHashtagModel(int dogamId, int categoryId, String hashtag) {
        this.dogamId = dogamId;
        this.categoryId = categoryId;
        this.hashtag = hashtag;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append("\t").append("dogamId : ").append(dogamId).append("\n");
        buffer.append("\t").append("categoryId : ").append(categoryId).append("\n");
        buffer.append("\t").append("hashtag : ").append(hashtag).append("\n");
        buffer.append("}");
        return buffer.toString();
    }
}

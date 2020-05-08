package com.moayo.server.model;

public class CategoryHashModel {
    private int dogamId;
    private int categoryId;
    private String hashtag;

    public CategoryHashModel(int dogamId, int categoryId, String hashtag) {
        this.dogamId = dogamId;
        this.categoryId = categoryId;
        this.hashtag = hashtag;
    }

    public CategoryHashModel() {
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public int getDogamId() {
        return dogamId;
    }
    @Override
    public String toString(){
        return "{" + dogamId + "," + categoryId + "," + hashtag + "}";
    }
}

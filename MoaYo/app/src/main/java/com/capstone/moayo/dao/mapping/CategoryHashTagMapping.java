package com.capstone.moayo.dao.mapping;

public class CategoryHashTagMapping {
    private int dogamId;
    private int categoryId;
    private String hashtag;

    public CategoryHashTagMapping(int dogamId,int categoryId, String hashtag){
        this.dogamId = dogamId;
        this.categoryId = categoryId;
        this.hashtag = hashtag;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public int getDogamId() {
        return dogamId;
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
}

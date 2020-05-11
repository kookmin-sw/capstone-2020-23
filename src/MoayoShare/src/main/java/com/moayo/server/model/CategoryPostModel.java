package com.moayo.server.model;

public class CategoryPostModel {
    private int dogamId;
    private int categoryId;
    private int postId;


    public CategoryPostModel(int dogamId, int categoryId, int postId) {
        this.dogamId = dogamId;
        this.categoryId = categoryId;
        this.postId = postId;
    }

    public CategoryPostModel() {
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getPostId() {
        return postId;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString(){
        return "{" + dogamId + "," + categoryId + "," + postId + "}";
    }
}

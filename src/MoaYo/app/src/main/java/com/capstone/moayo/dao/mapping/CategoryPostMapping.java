package com.capstone.moayo.dao.mapping;

public class CategoryPostMapping {
    private int dogamId;
    private int categoryId;
    private int postId;

    public CategoryPostMapping(int dogamId,int categoryId, int postId){
        this.categoryId= categoryId;
        this.dogamId = dogamId;
        this.postId=postId;
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

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }
}

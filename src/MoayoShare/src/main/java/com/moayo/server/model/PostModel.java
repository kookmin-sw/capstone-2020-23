package com.moayo.server.model;

public class PostModel {
    private int co_postId;
    private String co_postUrl;
    private String co_imageUrl;
    private String co_info;
    private String co_hashtag;

    public PostModel(int co_postId, String co_postUrl, String co_imageUrl, String co_info, String co_hashtag) {
        this.co_postId = co_postId;
        this.co_postUrl = co_postUrl;
        this.co_imageUrl = co_imageUrl;
        this.co_info = co_info;
        this.co_hashtag = co_hashtag;
    }

    public PostModel(String co_postUrl, String co_imageUrl, String co_info, String co_hashtag) {
        this.co_postUrl = co_postUrl;
        this.co_imageUrl = co_imageUrl;
        this.co_info = co_info;
        this.co_hashtag = co_hashtag;
    }

    public PostModel() {
    }

    public String getCo_hashtag() {
        return co_hashtag;
    }

    public void setCo_hashtag(String co_hashtag) {
        this.co_hashtag = co_hashtag;
    }

    public int getCo_postId() {
        return co_postId;
    }

    public void setCo_postId(int co_postId) {
        this.co_postId = co_postId;
    }

    public String getCo_imageUrl() {
        return co_imageUrl;
    }

    public void setCo_imageUrl(String co_imageUrl) {
        this.co_imageUrl = co_imageUrl;
    }

    public String getCo_info() {
        return co_info;
    }

    public void setCo_info(String co_info) {
        this.co_info = co_info;
    }

    public String getCo_postUrl() {
        return co_postUrl;
    }

    public void setCo_postUrl(String co_postUrl) {
        this.co_postUrl = co_postUrl;
    }
    @Override
    public String toString(){
        return "{"+getCo_postId() + "," + getCo_info() + "}";
    }
}

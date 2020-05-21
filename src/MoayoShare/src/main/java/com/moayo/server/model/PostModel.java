package com.moayo.server.model;

public class PostModel {
    private int co_postId;
    private String co_postUrl;
    private String co_imageUrl;
    private String co_hashtag;

    public PostModel(int co_postId, String co_postUrl, String co_imageUrl, String co_hashtag) {
        this.co_postId = co_postId;
        this.co_postUrl = co_postUrl;
        this.co_imageUrl = co_imageUrl;
        this.co_hashtag = co_hashtag;
    }

    public PostModel(String co_postUrl, String co_imageUrl, String co_hashtag) {
        this.co_postUrl = co_postUrl;
        this.co_imageUrl = co_imageUrl;
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

    public String getCo_postUrl() {
        return co_postUrl;
    }

    public void setCo_postUrl(String co_postUrl) {
        this.co_postUrl = co_postUrl;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PostModel{" +
                "co_postId=" + co_postId +
                ", co_postUrl='" + co_postUrl + '\'' +
                ", co_imageUrl='" + co_imageUrl + '\'' +
                ", co_hashtag='" + co_hashtag + '\'' +
                '}';
    }
}

package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

public class PostModel {
    @SerializedName("co_postId")
    private int id;
    @SerializedName("co_postUrl")
    private String url;
    @SerializedName("co_imageUrl")
    private String imgUrl;
    @SerializedName("co_hashtag")
    private  String hashtag;
    @SerializedName("co_like")
    private int like;

    public PostModel(int id, String url, String imgUrl, String hashtag, int like) {
        this.id = id;
        this.url = url;
        this.imgUrl = imgUrl;
        this.hashtag = hashtag;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", hashtag='" + hashtag + '\'' +
                '}';
    }
}

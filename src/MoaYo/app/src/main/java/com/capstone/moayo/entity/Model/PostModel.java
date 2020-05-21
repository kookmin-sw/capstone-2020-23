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

    public PostModel(int id, String url, String imgUrl, String hashtag) {
        this.id = id;
        this.url = url;
        this.imgUrl = imgUrl;
        this.hashtag = hashtag;
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

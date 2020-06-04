package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Post;

import java.io.Serializable;

public class PostDto implements Serializable {
    private int id;
    private String imgUrl;
    private String url;
    private String hashtag;
    private int like;

    private int categoryNodeId;
    private int dogamId;

    public PostDto(int categoryNodeId, int dogamId) {
        this.id = 0;
        this.categoryNodeId = categoryNodeId;
        this.dogamId = dogamId;
    }
    public PostDto(String imgUrl, String url, String hashtag, int like, int categoryNodeId, int dogamId) {
        this(categoryNodeId, dogamId);
        this.imgUrl = imgUrl;
        this.url = url;
        this.hashtag = hashtag;
        this.like = like;
    }

    public Post toPost() {
        Post post = new Post(imgUrl, url, hashtag, like, categoryNodeId, dogamId);
        post.setId(id);

        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getCategoryNodeId() {
        return categoryNodeId;
    }

    public void setCategoryNodeId(int categoryNodeId) {
        this.categoryNodeId = categoryNodeId;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", like=" + like +
                ", categoryNodeId=" + categoryNodeId +
                ", dogamId=" + dogamId +
                "}\n";
    }
}

package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.PostDto;

public class Post {
    // private Image img;
    private String url;
    private String info;
    private String hashtag;
    private int like;

    private int categoryNodeId;

    public Post(String url, String info, String hashtag, int like) {
        this.url = url;
        this.info = info;
        this.hashtag = hashtag;
        this.like = like;
    }

    public PostDto toContentDto() {
        PostDto contentDto = new PostDto(url, info, hashtag, like);

        return contentDto;
    }


    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getCategoryNodeId() {
        return categoryNodeId;
    }

    public void setCategoryNodeId(int categoryNodeId) {
        this.categoryNodeId = categoryNodeId;
    }
}

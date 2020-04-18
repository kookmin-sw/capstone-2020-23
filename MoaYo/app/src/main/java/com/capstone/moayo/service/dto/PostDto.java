package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Post;

public class PostDto {
    private String url;
    private String info;
    private String hashtag;
    private int like;
    private int categoryNodeId;

    public PostDto(String url, String info, String hashtag, int like) {
        this.url = url;
        this.info = info;
        this.hashtag = hashtag;
        this.like = like;
    }

    public Post toContent() {
        Post content = new Post(url, info, hashtag, like);
        content.setCategoryNodeId(categoryNodeId);


        return content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
}

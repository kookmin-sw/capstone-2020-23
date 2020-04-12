package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Content;

public class ContentDto {
    private String url;
    private String info;
    private String hashtag;
    private int like;
    private String categoryInfo;

    public ContentDto(String url, String info, String hashtag, int like) {
        this.url = url;
        this.info = info;
        this.hashtag = hashtag;
        this.like = like;
    }

    public Content toContent() {
        Content content = new Content(url, info, hashtag, like);
        content.setCategoryInfo(categoryInfo);

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

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}

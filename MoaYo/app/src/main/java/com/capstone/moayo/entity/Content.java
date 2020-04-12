package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.ContentDto;

public class Content {
    // private Image img;
    private String url;
    private String info;
    private String hashtag;
    private int like;
    private String categoryInfo;

    public Content(String url, String info, String hashtag, int like) {
        this.url = url;
        this.info = info;
        this.hashtag = hashtag;
        this.like = like;
    }

    public ContentDto toContentDto() {
        ContentDto contentDto = new ContentDto(url, info, hashtag, like);
        contentDto.setCategoryInfo(categoryInfo);
        return contentDto;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String getCategoryInfo() {
        return categoryInfo;
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
}

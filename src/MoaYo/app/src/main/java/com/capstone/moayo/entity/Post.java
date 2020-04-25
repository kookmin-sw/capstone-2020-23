package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.PostDto;

public class Post {
    private int id;
    private String info;
    private String hashtag;
    private int like;

    private String imgUrl;
    private String url;


    private int categoryNodeId;

    public Post() {
        this.id = 0;
    }

    public Post(String imgUrl, String url, String info, String hashtag, int like) {
        this();
        this.imgUrl = imgUrl;
        this.url = url;
        this.info = info;
        this.hashtag = hashtag;
        this.like = like;
    }

    public PostDto toPostDto() {
        PostDto postDto = new PostDto(imgUrl, url, info, hashtag, like);
        postDto.setCategoryNodeId(categoryNodeId);
        postDto.setId(id);

        return postDto;
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

//    public String toString() {
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("\"{id\" : \"").append(id).append("\"").
//                append("\"{\" : \"").append(id).append("\"").
//    }
}

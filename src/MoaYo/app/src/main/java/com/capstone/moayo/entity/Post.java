package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.PostDto;

public class Post {
    private int id;
    private String imgUrl;
    private String url;
    private String hashtag;
    private int like;


    private int categoryNodeId;
    private  int dogamId;

    public Post(int categoryNodeId, int dogamId) {
        this.id = 0;
        this.categoryNodeId = categoryNodeId;
        this.dogamId = dogamId;
    }

    public Post(String imgUrl, String url, String hashtag, int like, int categoryNodeId, int dogamId) {
        this(categoryNodeId, dogamId);
        this.imgUrl = imgUrl;
        this.url = url;
        this.hashtag = hashtag;
        this.like = like;
    }

    public PostDto toPostDto() {
        PostDto postDto = new PostDto(imgUrl, url, hashtag, like, categoryNodeId, dogamId);
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

    public int getDogamId() {
        return dogamId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"id\": ").append("\"").append(id).append("\",")
                .append("\"img_url\": ").append("\"").append(imgUrl).append("\",")
                .append("\"url\": ").append("\"").append(url).append("\",")
                .append("\"hashtag\": ").append("\"").append(hashtag).append("\",")
                .append("\"like\": ").append(like).append(",")
                .append("\"categoryNodeId\": ").append("\"").append(categoryNodeId).append("\",")
                .append("\"dogamId\": ").append("\"").append(dogamId).append("\"}");

        return buffer.toString();
    }
}

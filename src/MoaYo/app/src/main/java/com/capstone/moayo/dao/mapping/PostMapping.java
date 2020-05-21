package com.capstone.moayo.dao.mapping;

public class PostMapping {
    private int id;
    private String url;
    private String imgUrl;
    private String hashTag;
    private int like;

    public PostMapping(){

    }
    public PostMapping(int id, String url, String imgUrl, String hashTag, int like){
        this.id = id;
        this.url = url;
        this.imgUrl = imgUrl;
        this.hashTag = hashTag;
        this.like = like;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}

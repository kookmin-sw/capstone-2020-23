package com.capstone.moayo.dao.mapping;

public class PostMapping {
    private int id;
    private String url;
    private String imgUrl;
    private String info;
    private String hashTag;

    public PostMapping(){

    }
    public PostMapping(int id, String url, String imgUrl, String info, String hashTag){
        this.id = id;
        this.url = url;
        this.imgUrl = imgUrl;
        this.info = info;
        this.hashTag = hashTag;
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

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

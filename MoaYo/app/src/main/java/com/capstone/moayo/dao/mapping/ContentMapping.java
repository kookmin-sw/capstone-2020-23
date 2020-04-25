package com.capstone.moayo.dao.mapping;

public class ContentMapping {
    private int id;
    private int parent;
    private int level;
    private String url;
    private String imgUrl;
    private String info;
    private String hashTag;

    public ContentMapping(){

    }
    public ContentMapping(int id,int parent,int level,String url,String imgUrl,String info,String hashTag){
        this.id = id;
        this.parent = parent;
        this.level = level;
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

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
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

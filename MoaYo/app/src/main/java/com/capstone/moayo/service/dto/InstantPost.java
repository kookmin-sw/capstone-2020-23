package com.capstone.moayo.service.dto;

public class InstantPost {
    private String text;
    private String url;
    private String src;
    private String like;

    public InstantPost(String text, String url, String src, String like) {
        this.text = text;
        setUrl(url);
        this.src = src;
        this.like = like;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = "https://instagram.com/p/" + url;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}

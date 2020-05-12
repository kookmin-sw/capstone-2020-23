package com.capstone.moayo.service.dto;

import com.google.gson.annotations.SerializedName;

public class InstantPost {
    @SerializedName("text")
    private String text;
    @SerializedName("url")
    private String url = "https://instagram.com/p/";
    @SerializedName("src")
    private String src;
    @SerializedName("like")
    private int like;

    public InstantPost(String text, String url, String src, int like) {
        this.text = text;
        this.url += url;
        this.src = src;
        this.like = like;
    }
    public PostDto toPostDto() {
        PostDto postDto = new PostDto(src, url, text, like, 0, 0);
        return postDto;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}

package com.capstone.moayo.model;

public class SharedBook {

    private String url;
    private String nickName;
    private String comment;

    public SharedBook(String url, String nickName, String comment) {

        this.url = url;
        this.nickName = nickName;
        this.comment = comment;

    }

    public String getUrl() {
        return url;
    }

    public String getNickName() {
        return nickName;
    }

    public String getComment() {
        return comment;
    }
}


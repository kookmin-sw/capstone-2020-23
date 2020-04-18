package com.capstone.moayo.model;

public class SavedPost {
    private String url;
    private String tag;

    public SavedPost(String url, String tag) {

        this.url = url;
        this.tag = tag;


    }

    public String getUrl() {
        return url;
    }

    public String getTag() {
        return tag;
    }

}

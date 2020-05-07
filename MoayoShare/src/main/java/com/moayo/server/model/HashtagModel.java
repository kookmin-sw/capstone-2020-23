package com.moayo.server.model;

public class HashtagModel {
    private String co_hashtag;

    public void setCo_hashtag(String co_hashtag) {
        this.co_hashtag = co_hashtag;
    }

    public String getCo_hashtag() {
        return co_hashtag;
    }
    @Override
    public String toString(){
        return getCo_hashtag();
    }
}

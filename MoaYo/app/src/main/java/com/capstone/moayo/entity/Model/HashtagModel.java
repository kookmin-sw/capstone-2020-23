package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

public class HashtagModel {
    @SerializedName("co_hashtag")
    private String hashtag;

    public HashtagModel(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append("\t").append("hashtag : ").append(hashtag).append("\n");
        buffer.append("}");
        return buffer.toString();
    }
}

package com.capstone.moayo.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Keyword implements Serializable {
    private String keyword;
    private ArrayList<String> hashtags;
    private ArrayList<String> candidateTags;

    public Keyword() {
        this.keyword = null;
        this.hashtags = null;
        this.candidateTags = null;
    }

    public Keyword(String keyword) {
        this.keyword = keyword;
        this.hashtags = null;
        this.candidateTags = null;
    }

    public Keyword(String keyword, ArrayList<String> tags) {
        this.keyword = keyword;
        this.hashtags = tags;
        this.candidateTags = null;
    }

    public String getKeyword() {
        return this.keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getNumOfTags() {
        if(this.hashtags == null) {
            return 0;
        } else {
            return this.hashtags.size();
        }
    }

}

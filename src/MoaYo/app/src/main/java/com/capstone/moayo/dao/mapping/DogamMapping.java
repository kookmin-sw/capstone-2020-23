package com.capstone.moayo.dao.mapping;

import com.capstone.moayo.util.DogamStatus;

public class DogamMapping {
    private int id;
    private String title;
    private String description;
    private String password;
    private String url;
    private String writer;
    private DogamStatus status;
    private boolean isLiked;
    private int sharedDogamId;

    public void setDescription(String desription) {
        this.description = desription;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DogamStatus getStatus() {
        return status;
    }

    public void setStatus(DogamStatus status) {
        this.status = status;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getSharedDogamId() {
        return sharedDogamId;
    }

    public void setSharedDogamId(int sharedDogamId) {
        this.sharedDogamId = sharedDogamId;
    }
}

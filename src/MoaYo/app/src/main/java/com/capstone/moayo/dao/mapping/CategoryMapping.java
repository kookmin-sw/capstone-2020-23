package com.capstone.moayo.dao.mapping;


public class CategoryMapping {
    private int id;
    private int dogamId;
    private String title;
    private int parent;
    private int parentDogam;
    private int level;

    public CategoryMapping(int id,int dogamId,String title,int parent,int parentDogam,int level){
        this.id = id;
        this.dogamId = dogamId;
        this.title = title;
        this.parent = parent;
        this.parentDogam = parentDogam;
        this.level = level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setParentDogam(int parentDogam) {
        this.parentDogam = parentDogam;
    }

    public int getParentDogam() {
        return parentDogam;
    }
}

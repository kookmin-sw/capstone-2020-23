package com.capstone.moayo.Entity;

import java.util.List;

public class CategoryNode {
    private String title;
    private CategoryNode parent;
    private List<Category> lowLevel;
    private int level;

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLowLevel(List<Category> lowLevel) {
        this.lowLevel = lowLevel;
    }

    public void setParent(CategoryNode parent) {
        this.parent = parent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryNode getParent() {
        return parent;
    }

    public int getLevel() {
        return level;
    }

    public List<Category> getLowLevel() {
        return lowLevel;
    }

    public String getTitle() {
        return title;
    }
}

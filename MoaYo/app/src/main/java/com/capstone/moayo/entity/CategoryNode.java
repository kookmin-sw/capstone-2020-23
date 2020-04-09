package com.capstone.moayo.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode {
    private String title;
    private CategoryNode parent;
    private List<CategoryNode> lowLevel;
    private int level;

    public CategoryNode(String title, CategoryNode parent, int level) {
        this.title = title;
        this.parent = parent;
        this.level = level;

        lowLevel = new ArrayList<CategoryNode>();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLowLevel(List<CategoryNode> lowLevel) {
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

    public List<CategoryNode> getLowLevel() {
        return lowLevel;
    }

    public String getTitle() {
        return title;
    }
}

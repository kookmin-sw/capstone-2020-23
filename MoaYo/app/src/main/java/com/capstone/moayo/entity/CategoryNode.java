package com.capstone.moayo.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode {
    private String title;
    private CategoryNode parent;
    private List<CategoryNode> lowLayer;
    private int level;

    private List<Content> contents;

    public CategoryNode() {
        this.title = null;
        this.parent = null;
        this.lowLayer = null;
        this.level = 0;
    }

    public CategoryNode(String title, CategoryNode parent, int level) {
        this.title = title;
        this.parent = parent;
        this.level = level;

        lowLayer = new ArrayList<CategoryNode>();
        contents = new ArrayList<>();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<CategoryNode> getLowLayer() {
        return lowLayer;
    }

    public void setLowLayer(List<CategoryNode> lowLayer) {
        this.lowLayer = lowLayer;
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

    public String getTitle() {
        return title;
    }

    public List<Content> getContents() { return contents; }

    public void setContents(List<Content> contents) { this.contents = contents; }
}

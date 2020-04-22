package com.capstone.moayo.model;

public class Category {
    private int id;
    private CategoryNode selectCategoryNode;
    private CategoryNode rootNode;

    public Category(CategoryNode rootNode) {
        this.rootNode = rootNode;
        this.selectCategoryNode = null;
    }

    public Category(CategoryNode rootNode, CategoryNode selectNode) {
        this.rootNode = rootNode;
        this.selectCategoryNode = selectNode;
    }

}

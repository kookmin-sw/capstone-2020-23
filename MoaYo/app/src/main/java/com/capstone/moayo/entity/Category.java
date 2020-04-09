package com.capstone.moayo.entity;

import java.util.ArrayList;
import java.util.List;

public class Category{
    private CategoryNode currentCategory;
    private CategoryNode root;

    public Category(CategoryNode root) {
        currentCategory = root;

        this.root = root;
    }

    public void setCurrentCategory(CategoryNode currentCategory) {
        this.currentCategory = currentCategory;
    }

    public void setRoot(CategoryNode root) {
        this.root = root;
    }

    public CategoryNode getCurrentCategory() {
        return currentCategory;
    }

    public CategoryNode getRoot() {
        return root;
    }
}
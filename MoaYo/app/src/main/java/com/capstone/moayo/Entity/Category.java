package com.capstone.moayo.Entity;

import java.util.ArrayList;
import java.util.List;

public class Category{
    private CategoryNode currentCategory;
    private List<CategoryNode> hierarchy;

    public Category() {
        currentCategory = null;

        hierarchy = new ArrayList<CategoryNode>();
    }

    public void setCurrentCategory(CategoryNode currentCategory) {
        this.currentCategory = currentCategory;
    }

    public void setHierarchy(List<CategoryNode> hierarchy) {
        this.hierarchy = hierarchy;
    }

    public CategoryNode getCurrentCategory() {
        return currentCategory;
    }

    public List<CategoryNode> getHierarchy() {
        return hierarchy;
    }
}
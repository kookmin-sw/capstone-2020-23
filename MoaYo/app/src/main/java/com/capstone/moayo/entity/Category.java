package com.capstone.moayo.entity;

import java.util.ArrayList;
import java.util.List;

public class Category{
    private CategoryNode currentCategory;
    private List<CategoryNode> categoryList;

    public Category() {
        this.categoryList = new ArrayList<>();
        this.currentCategory = null;
    }

    public CategoryNode getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(CategoryNode currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<CategoryNode> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryNode> categoryList) {
        this.categoryList = categoryList;
    }
}

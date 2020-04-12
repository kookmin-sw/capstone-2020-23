package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private CategoryNodeDto currentCategory;
    private List<CategoryNodeDto> root;

    public CategoryDto() {
        this.root = new ArrayList<>();
        this.currentCategory = null;
    }

    public Category toCategory() throws NotRootException {
        Category category = new Category();
        for(CategoryNodeDto node : root) {
            category.getCategoryList().add(node.toCategoryNode());
        }
        return category;
    }

    public CategoryNodeDto getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(CategoryNodeDto currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<CategoryNodeDto> getRoot() {
        return root;
    }

    public void setRoot(List<CategoryNodeDto> root) {
        this.root = root;
    }
}

package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private String title;
    private String description;
    private String password;
    private CategoryNodeDto selectCategory;
    private CategoryNodeDto rootNode;

    public CategoryDto(String title, String description, String password, CategoryNodeDto rootNode) {
        this.title = title;
        this.description = description;
        this.password = password;
        this.rootNode = rootNode;
        this.selectCategory = null;
    }

    public Category toCategory() throws NotRootException {
        Category category = new Category(title, description, password, rootNode.toCategoryNode());
        category.setSelectCategoryNode(selectCategory.toCategoryNode());

        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CategoryNodeDto getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(CategoryNodeDto selectCategory) {
        this.selectCategory = selectCategory;
    }

    public CategoryNodeDto getRootNode() {
        return rootNode;
    }

    public void setRootNode(CategoryNodeDto rootNode) {
        this.rootNode = rootNode;
    }
}

package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private int id;
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
        this.selectCategory = rootNode;
    }

    public Category toCategory() {
        Category category = new Category(title, description, password, rootNode.toCategoryNode());
        category.setId(id);
        return category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String toString() {
        String result = String.format("{\"title\" : \"%s\"," +
                                " \"description\" : \"%s\"," +
                                " \"password\" : \"%s\"," +
                                " \"rootNode\" : \"%s\"}", title, description, password, rootNode.toString());

        return result;
    }
}

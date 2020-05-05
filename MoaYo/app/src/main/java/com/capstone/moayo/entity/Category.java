package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Category{
    private int id;
    private String title;
    private String description;
    private String password;

    private CategoryNode selectCategoryNode;
    private CategoryNode rootNode;

    public Category(String title, String description, String password,CategoryNode rootNode) {
        this.id = 0;
        this.title = title;
        this.description = description;
        this.password = password;
        this.rootNode = rootNode;
        this.selectCategoryNode = rootNode;
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto(title, description, password, rootNode.toCategoryNodeDto());
        categoryDto.setId(id);
        return categoryDto;
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

    public CategoryNode getSelectCategoryNode() {
        return selectCategoryNode;
    }

    public void setSelectCategoryNode(CategoryNode selectCategoryNode) {
        this.selectCategoryNode = selectCategoryNode;
    }

    public CategoryNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(CategoryNode rootNode) {
        this.rootNode = rootNode;
    }

    public String toString() {
        String result = String.format("{\"id\" : \"%s\"," +
                "\"title\" : \"%s\"," +
                " \"description\" : \"%s\"," +
                " \"password\" : \"%s\"," +
                " \"rootNode\" : \"%s\"}", id, title, description, password, rootNode.toString());

        return result;
    }
}

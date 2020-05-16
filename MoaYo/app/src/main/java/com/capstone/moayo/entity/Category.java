package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.util.DogamStatus;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/*
    status
    0 : non-share category
    1 : share category
 */
public class Category{
    private int id;
    private String title;
    private String description;
    private String password;
    private DogamStatus status;

    private CategoryNode selectCategoryNode;
    private CategoryNode rootNode;

    public Category() {
        id = 0;
        status = DogamStatus.NonShare;
    }
    public Category(String title, String description, String password,CategoryNode rootNode) {
        this();
        this.title = title;
        this.description = description;
        this.password = password;
        this.rootNode = rootNode;
        this.selectCategoryNode = rootNode;
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = null;
        if(rootNode != null)
            categoryDto = new CategoryDto(title, description, password, rootNode.toCategoryNodeDto());
        else categoryDto = new CategoryDto(title, description, password, null);

        categoryDto.setId(id);
        categoryDto.setStatus(status);
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

    public DogamStatus getStatus() {
        return status;
    }

    public void setStatus(DogamStatus status) {
        this.status = status;
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

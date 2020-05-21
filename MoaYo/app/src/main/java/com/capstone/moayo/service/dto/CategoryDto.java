package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.util.DogamStatus;
import com.capstone.moayo.util.Exception.NotRootException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private int id;
    private String title;
    private String description;
    private String password;
    private DogamStatus status;

    private CategoryNodeDto selectCategory;
    private CategoryNodeDto rootNode;

    public CategoryDto() {
        this.id = 0;
        this.status = DogamStatus.NonShare;
    }
    public CategoryDto(String title, String description, String password, CategoryNodeDto rootNode) {
        this();
        this.title = title;
        this.description = description;
        this.password = password;
        this.rootNode = rootNode;
        this.selectCategory = rootNode;
    }

    public Category toCategory() {
        Category category = null;
        if(rootNode != null)
            category = new Category(title, description, password, rootNode.toCategoryNode());
        else category = new Category(title, description, password, null);

        category.setId(id);
        category.setStatus(status);
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

    public DogamStatus getStatus() {
        return status;
    }

    public void setStatus(DogamStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", selectCategory=" + selectCategory +
                ", rootNode=" + rootNode +
                "}\n";
    }
}

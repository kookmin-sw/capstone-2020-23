package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.util.DogamStatus;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/*

    0 : mutable
    1 : immutable
 */
public class Category{
    private int id;
    private String title;
    private String description;
    private String password;
    private DogamStatus status;
    private String url;
    private String writer;
    private boolean isLiked;

    private int sharedDogamId;

    private CategoryNode selectCategoryNode;
    private CategoryNode rootNode;

    public Category() {
        id = 0;
        status = DogamStatus.NonShare;
        this.writer = "";
        this.url = "https://image.flaticon.com/icons/png/512/130/130304.png";
        this.isLiked = false;
        sharedDogamId = 0;
    }
    public Category(String title, String description, String password, CategoryNode rootNode) {
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
        categoryDto.setWriter(writer);
        categoryDto.setUrl(url);
        categoryDto.setLiked(isLiked);
        categoryDto.setSharedDogamId(sharedDogamId);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getSharedDogamId() {
        return sharedDogamId;
    }

    public void setSharedDogamId(int sharedDogamId) {
        this.sharedDogamId = sharedDogamId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", writer='" + writer + '\'' +
                ", rootNode=" + rootNode +
                '}';
    }
}

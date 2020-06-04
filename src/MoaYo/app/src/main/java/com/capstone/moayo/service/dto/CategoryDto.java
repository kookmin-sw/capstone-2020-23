package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.util.DogamStatus;

import java.io.Serializable;

public class CategoryDto implements Serializable {
    private int id;
    private String title;
    private String description;
    private String password;
    private DogamStatus status;
    private String url;
    private String writer;
    private int like;
    private String time;

    private CategoryNodeDto selectCategory;
    private CategoryNodeDto rootNode;

    public CategoryDto() {
        this.id = 0;
        this.status = DogamStatus.NonShare;
        this.writer = "";
        this.url = "https://image.flaticon.com/icons/png/512/130/130304.png";
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
        category.setWriter(writer);
        category.setUrl(url);
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", rootNode=" + rootNode +
                '}';
    }
}

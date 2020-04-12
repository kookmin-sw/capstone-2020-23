package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class Category{
    private CategoryNode selectCategoryNode;
    private CategoryNode rootNode;

    public Category(CategoryNode rootNode) {
        this.rootNode = rootNode;
        this.selectCategoryNode = null;
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto(rootNode.toCategoryNodeDto());
        return categoryDto;
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
}

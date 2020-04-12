package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private CategoryNodeDto selectCategory;
    private CategoryNodeDto rootNode;

    public CategoryDto(CategoryNodeDto rootNode) {
        this.rootNode = rootNode;
        this.selectCategory = null;
    }

    public Category toCategory() throws NotRootException {
        Category category = new Category(rootNode.toCategoryNode());
        category.setSelectCategoryNode(selectCategory.toCategoryNode());

        return category;
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

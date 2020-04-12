package com.capstone.moayo.service;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.List;

public interface CategoryService {
    public String createCategoryNode(CategoryNodeDto categoryNodeDto) throws NotRootException;
    public List<CategoryDto> findCategoryNodeByTitle(String title);
    public Category findCategoryNodeById(int id);
    public String modifyCategoryNode(CategoryNodeDto categoryNodeDto);
    public String deleteCategoryNode(int id);
}

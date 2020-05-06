package com.capstone.moayo.service;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.List;

public interface CategoryService {
    public String createCategory(CategoryDto categoryDto);
    public CategoryDto findCategoryByTitle(String title);
    public CategoryDto findCategoryById(int id);
    public String modifyCategory(CategoryDto categoryDto);
    public String deleteDogam(int id);
    public String deleteCategoryNode(int id);
}

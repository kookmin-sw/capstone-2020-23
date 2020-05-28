package com.capstone.moayo.service;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.List;

public interface CategoryService {
    public String createCategory(CategoryDto categoryDto);
    public List<CategoryDto> findAll();
    public CategoryDto findCategoryById(int id);
    public String modifyCategory(CategoryDto categoryDto);
    public boolean modifyDogam(CategoryDto categoryDto);
    public String deleteDogam(int id);
    public String deleteCategoryNode(int dogamId, int id);
}

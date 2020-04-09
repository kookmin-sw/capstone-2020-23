package com.capstone.moayo.service;

import com.capstone.moayo.entity.Category;

import java.util.List;

public interface CategoryService {
    public String createCategory(Category category);
    public List<Category> findCategoryByTitle(String title);
    public Category findCategoryById(int id);
    public String modifyCategory(Category category);
    public String deleteCategory(int id);
}

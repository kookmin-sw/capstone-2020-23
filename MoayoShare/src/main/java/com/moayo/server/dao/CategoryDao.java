package com.moayo.server.dao;

import com.moayo.server.model.CategoryModel;

import java.util.List;

public interface CategoryDao {
    // SET FOREIGN_KEY_CHECKS=1;
    long insertCategory(CategoryModel categoryModel);
    long updateCategory(CategoryModel categoryModel);
    long deleteCategory(CategoryModel categoryModel);
    long deleteCategoryById(int id);
    long deleteCategoryByDogamId(int dogamId);
    CategoryModel getCategoryById(int id);
    List<CategoryModel> getCategoryByDogamId(int dogamId);
}

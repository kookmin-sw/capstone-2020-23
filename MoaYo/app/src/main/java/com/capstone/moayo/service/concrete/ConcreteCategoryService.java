package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.ContentDto;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.ContentStorage;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCategoryService implements CategoryService {
    private CategoryStorage categoryStorage;

    public ConcreteCategoryService(Context context) {
        this.categoryStorage = StorageFactoryCreator.getInstance().requestCategoryStorage(context);
    }

    @Override
    public String createCategory(CategoryNodeDto categoryNodeDto) throws NotRootException {
        if(categoryNodeDto.getParent() != null) {
            throw new NotRootException();
        }
        CategoryNode newCategoryNode = categoryNodeDto.toCategoryNode();
        Category newCategory = new Category(newCategoryNode);

        String result = categoryStorage.create(newCategory);

        return result;
    }

    @Override
    public List<CategoryDto> findCategoryByTitle(String title) {
        List<Category> foundNodeList = categoryStorage.retrieveByTitle(title);
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(Category foundCategory : foundNodeList) {
            categoryDtoList.add(foundCategory.toCategoryDto());
        }

        return categoryDtoList;
    }

    @Override
    public Category findCategoryById(int id) {
        return null;
    }

    @Override
    public String modifyCategory(CategoryNodeDto categoryNodeDto) {
        return null;
    }

    @Override
    public String deleteCategory(int id) {
        return null;
    }
}

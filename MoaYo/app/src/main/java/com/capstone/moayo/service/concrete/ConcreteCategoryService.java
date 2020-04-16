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
import com.capstone.moayo.util.Exception.NullCategoryException;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCategoryService implements CategoryService {
    private CategoryStorage categoryStorage;

    public ConcreteCategoryService(Context context) {
        this.categoryStorage = StorageFactoryCreator.getInstance().requestCategoryStorage(context);
    }

    @Override
    public String createCategory(CategoryDto newCategoryDto){

        Category newCategory = newCategoryDto.toCategory();

        String result = categoryStorage.create(newCategory);

        return result;
    }

    @Override
    public CategoryDto findCategoryByTitle(String title) {
        CategoryDto foundCategoryDto = null;
        try {
            Category foundCategory = categoryStorage.retrieveByTitle(title);
            if(foundCategory == null)
                throw new NullCategoryException();

            foundCategoryDto = foundCategory.toCategoryDto();

        } catch (NullCategoryException e) {
            e.toString();
        }


        return foundCategoryDto;
    }

    @Override
    public CategoryDto findCategoryById(int id) {
        try {
            Category foundCategory = categoryStorage.retrieveById(id);
            if(foundCategory == null) {
                throw new Exception();
            }

            CategoryDto foundCategoryDto = foundCategory.toCategoryDto();
            return foundCategoryDto;
        } catch (Exception e) {
            e.toString();
        }

        return null;
    }

    @Override
    public String modifyCategory(CategoryDto categoryDto) {
        Category modifyCategory = categoryDto.toCategory();

        return null;
    }

    @Override
    public String deleteCategory(int id) {
        String result = "";
        try {
            Category foundCategory = categoryStorage.retrieveById(id);
            if(foundCategory == null)
                throw new NullCategoryException();
            result = categoryStorage.remove(id);
        } catch (NullCategoryException e) {
            e.toString();
        }

        return result;
    }
}

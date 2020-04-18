package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.Exception.NullCategoryException;

public class ConcreteCategoryService implements CategoryService {
    private CategoryStorage categoryStorage;
    private Context applicationContext;

    public ConcreteCategoryService(Context context) {
        this.categoryStorage = StorageFactoryCreator.getInstance().requestCategoryStorage(context);
        applicationContext = context;
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
        CategoryDto foundCategoryDto = null;
        try {
            Category foundCategory = categoryStorage.retrieveById(id);
            if(foundCategory == null) {
                throw new NullCategoryException();
            }

            foundCategoryDto = foundCategory.toCategoryDto();
        } catch (NullCategoryException e) {
            Log.d("error in service", e.toString());
        }

        return foundCategoryDto;
    }

    @Override
    public String modifyCategory(CategoryDto categoryDto) {
        Category modifyCategory = categoryDto.toCategory();
        //TODO implement code
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
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}

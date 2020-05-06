package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.Exception.NullCategoryException;

public class ConcreteCategoryService implements CategoryService {
    private CategoryStorage categoryStorage;
    private DogamStorage dogamStorage;
    private Context applicationContext;


    public ConcreteCategoryService(Context context) {
        this.categoryStorage = StorageFactoryCreator.getInstance().requestCategoryStorage(context);
        this.dogamStorage = StorageFactoryCreator.getInstance().requestDogamStorage(context);
        applicationContext = context;
    }

    @Override
    public String createCategory(CategoryDto newCategoryDto){

        Category newCategory = newCategoryDto.toCategory();

        int dogamId = dogamStorage.create(newCategory);
        newCategory.setId(dogamId);
        String result = categoryStorage.create(newCategory);

        return result;
    }

    @Override
    public CategoryDto findCategoryByTitle(String title) {
        return null;
    }

    @Override
    public CategoryDto findCategoryById(int id) {
        CategoryDto foundCategoryDto = null;
        try {
            DogamMapping foundDogam = dogamStorage.retrieveById(id);
            if(foundDogam == null) {

            }
            CategoryNode rootNode = categoryStorage.retrieveById(foundDogam.getId());
            Category foundCategory = new Category(foundDogam.getTitle(), foundDogam.getDesription(), foundDogam.getPassword(), rootNode);
            foundCategory.setId(foundDogam.getId());
            foundCategoryDto = foundCategory.toCategoryDto();
        } catch (Exception e) {
            Log.d("error in service", e.toString());
        }

        return foundCategoryDto;
    }

    @Override
    public String modifyCategory(CategoryDto categoryDto) {
        Category modifyCategory = categoryDto.toCategory();
        DogamMapping foundDogam = dogamStorage.retrieveById(modifyCategory.getId());
        if(foundDogam == null) {
            return "No Category";
        }

        dogamStorage.update(modifyCategory);
        String result = categoryStorage.update(modifyCategory);
        return result;
    }

    @Override
    public String deleteDogam(int id) {
        String result = "";
        try {
            DogamMapping foundDogam = dogamStorage.retrieveById(id);
            if(foundDogam == null)
                throw new NullCategoryException();
            boolean re = dogamStorage.remove(foundDogam.getId());
            if(re != true) {
                result = "fail to delete dogam";
            } else {
                result = "success to delete dogam";
            }
        } catch (NullCategoryException e) {
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    @Override
    public String deleteCategoryNode(int id) {
        String result = "";
        try {
//            CategoryNode foundNode = categoryStorage.retrieveById(id);
//            Log.d("found node", foundNode.toString());
//            if(foundNode == null) {
//                throw new Exception();
//            }
            result = categoryStorage.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

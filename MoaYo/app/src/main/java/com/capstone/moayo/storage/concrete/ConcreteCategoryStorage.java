package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.CategoryDaoImpl;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.concrete.DogamDaoImpl;
import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.util.Exception.DaoObjectNullException;
import com.capstone.moayo.util.Exception.DogamNullException;
import com.capstone.moayo.util.Exception.NullCategoryException;

public class ConcreteCategoryStorage implements CategoryStorage {
    private CategoryDao categoryDao;
    private DogamDao dogamDao;
    private DBHelper dbHelper;

    public ConcreteCategoryStorage(Context context) {
        dbHelper = StorageFactoryCreator.getInstance().initDao(context);
        categoryDao = DaoFactoryCreator.getInstance().requestCategoryDao();
        dogamDao = DaoFactoryCreator.getInstance().requestDogamDao();
    }

    @Override
    public String create(Category category) {
//        //Insert Category Info using dogamDao in DogamTable
//        int dogamId = (int) dogamDao.insert(dbHelper, category.getTitle(), category.getDescription(), category.getPassword());
//        CategoryNode rootNode = category.getRootNode();
//        //insert 1th layer
//        int rootId = (int) categoryDao.insert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), dogamId);
//        categoryDao.update(dbHelper,rootId,rootNode.getLevel(),rootId,rootNode.getTitle(),dogamId);
//        //insert 2nd layer
//        for(CategoryNode secondNode : rootNode.getLowLayer()) {
//            int secondId = (int) categoryDao.insert(dbHelper, secondNode.getLevel(), rootId, secondNode.getTitle(), dogamId);
//            //insery 3rd layer
//            for(CategoryNode thirdNode : secondNode.getLowLayer()) {
//                categoryDao.insert(dbHelper, thirdNode.getLevel(), secondId,thirdNode.getTitle(), dogamId);
//            }
//        }
//        Log.d("Category", category.toString());
        return category.toString();
    }

    @Override
    public Category retrieveByTitle(String title) {
        Category foundCategory = null;
        try {
            DogamMapping dogamMapping = dogamDao.selectByTitle(dbHelper, title);
            if(dogamMapping == null)
                throw new DogamNullException();

            CategoryNode foundCategoryNode = categoryDao.selectByDogamId(dbHelper, dogamMapping.getId());
            if(foundCategoryNode == null)
                throw new NullCategoryException();

            foundCategory = new Category(dogamMapping.getTitle(), dogamMapping.getDesription(), dogamMapping.getPassword(), foundCategoryNode);
        } catch (DogamNullException | NullCategoryException e) {
            Log.d("error in storage",e.toString());
        }

        return foundCategory;
    }

    @Override
    public Category retrieveById(int id) {
        Category foundCategory = null;
        try {
            DogamMapping mapping = dogamDao.selectById(dbHelper, id);
            Log.d("mapping", String.format("%d",mapping.getId()));
            if(mapping == null) {
                throw new DogamNullException();
            }
            CategoryNode foundNode = categoryDao.selectByDogamId(dbHelper, mapping.getId());
            if(foundNode == null) {
                throw new NullCategoryException();
            }
            foundCategory = new Category(mapping.getTitle(), mapping.getDesription(), mapping.getPassword(), foundNode);
            Log.d("found category", foundCategory.toString());
        } catch (DogamNullException | NullCategoryException e) {
            Log.d("error in storage", e.toString());
        }

        return foundCategory;
    }

    @Override
    public String update(Category category) {
//        boolean c_result = dogamDao.update(dbHelper, category.getId(), category.getTitle(), category.getDescription(), category.getPassword());
//        if(c_result) {
//            CategoryNode rootNode = category.getRootNode();
//            c_result = categoryDao.update(dbHelper, rootNode.getId(), rootNode.getLevel(), rootNode.getId(), rootNode.getTitle(), category.getId());
//            if(c_result) {
//                for(CategoryNode secondNode : rootNode.getLowLayer()) {
//                    c_result = categoryDao.update(dbHelper, secondNode.getId(), secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), category.getId());
//                    if(c_result) {
//                        for (CategoryNode thirdNode : secondNode.getLowLayer()) {
//                            c_result =categoryDao.update(dbHelper, thirdNode.getId(), thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), category.getId());
//                            if(!c_result) return "fail to update";
//                        }
//                    } else return "fail to update";
//                }
//            } else return "fail to update";
//
//        } else return "fail to update";
        return category.toString();
    }

    @Override
    public String remove(int id) {
        String result = dogamDao.delete(dbHelper, id) ? "success to delete" : "fail to delete";
        return result;
    }
}

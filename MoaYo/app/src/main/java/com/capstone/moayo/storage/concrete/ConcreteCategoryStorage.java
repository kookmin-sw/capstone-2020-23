package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capstone.moayo.MainActivity;
import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.DaoFactory;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcreteCategoryStorage implements CategoryStorage {
    private CategoryDao categoryDao;
    private DogamDao dogamDao;
    private DBHelper dbHelper;

    public ConcreteCategoryStorage(Context context) {
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
        categoryDao = DaoFactoryCreator.getInstance().requestCategoryDao();
        dogamDao = DaoFactoryCreator.getInstance().requestDogamDao();
    }

    @Override
    public String create(Category category) {
        AsyncTask<Category, Void, String> thread = new AsyncTask<Category, Void, String>() {
            @Override
            protected String doInBackground(Category... categories) {
                Category selectCategory = categories[0];
                int dogamId = selectCategory.getId();
                CategoryNode rootNode = selectCategory.getRootNode();
                int rootId = (int) categoryDao.rootInsert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), dogamId , dogamId);
                for(CategoryNode secondNode : rootNode.getLowLayer()) {
                    int secondId = (int) categoryDao.insert(dbHelper, secondNode.getLevel(), rootId, secondNode.getTitle(), dogamId, dogamId);
                    for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                        int thirdId = (int) categoryDao.insert(dbHelper, thirdNode.getLevel(), secondId, thirdNode.getTitle(), dogamId, dogamId);
                    }
                }
                return "success to create category";
            }
        };
        try {
            String result = thread.execute(category).get(3, TimeUnit.SECONDS);
            return result;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return "fail to create";
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
            foundCategory.setId(mapping.getId());
            Log.d("found category", foundCategory.toString());
        } catch (DogamNullException | NullCategoryException e) {
            Log.d("error in storage", e.toString());
        }

        return foundCategory;
    }

    @Override
    public String update(Category category) {
        AsyncTask<Category, Void, String> thread = new AsyncTask<Category, Void, String>() {
            @Override
            protected String doInBackground(Category... categories) {
                int dogamId = categories[0].getId();
                CategoryNode rootNode = categories[0].getRootNode();
                boolean result = categoryDao.rootUpdate(dbHelper, rootNode.getId(), rootNode.getLevel(), 0, rootNode.getTitle(), dogamId, dogamId);
                if(result != true) return "fail to update";
                for(CategoryNode secondNode : rootNode.getLowLayer()) {
                    result = categoryDao.update(dbHelper, secondNode.getId(), secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), dogamId, dogamId);
                    if(result != true) return "fail to update";
                    for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                        result = categoryDao.update(dbHelper, thirdNode.getId(), thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), dogamId, dogamId);
                        if(result != true) return "fail to update";
                    }
                }
                return "success to update";
            }
        };

        try {
            String result = thread.execute(category).get(3, TimeUnit.SECONDS);
            return result;
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
        return "fail to update";
    }

    @Override
    public String remove(int id) {
        String result = dogamDao.delete(dbHelper, id) ? "success to delete" : "fail to delete";
        return result;
    }
}

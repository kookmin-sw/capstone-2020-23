package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.os.AsyncTask;

import com.capstone.moayo.dao.DaoFactory;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.storage.DogamStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okio.Timeout;

public class ConcreteDogamStorage implements DogamStorage {
    private DogamDao dogamDao;
    private DBHelper dbHelper;

    public ConcreteDogamStorage(Context context) {
        dogamDao = DaoFactoryCreator.getInstance().requestDogamDao();
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
    }

    @Override
    public int create(Category category) {
        int dogamId = (int) dogamDao.insert(dbHelper, category.getTitle(), category.getDescription(), category.getPassword());
        return dogamId;
    }

    @Override
    public DogamMapping retrieveById(int id) {
        DogamMapping foundDogam = dogamDao.selectById(dbHelper, id);
        return foundDogam;
    }

    @Override
    public List<Category> retrieveAll() {
        DogamMapping[] dogams = dogamDao.selectAll(dbHelper);
        if(dogams == null) {
            return null;
        }
        List<Category> categories = new ArrayList<>();
        for(DogamMapping dogam : dogams) {
            Category category = new Category(dogam.getTitle(), dogam.getDesription(), dogam.getPassword(), null);
            category.setId(dogam.getId());
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void update(Category category) {
        try {
            boolean result = dogamDao.update(dbHelper, category.getId(), category.getTitle(), category.getDescription(), category.getPassword());
            if(result != true)
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(int id) {
        AsyncTask<Integer, Void, Boolean> thread = new AsyncTask<Integer, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Integer ... integers) {
                int dogamId = integers[0];
                boolean result = dogamDao.delete(dbHelper, dogamId);
                return result;
            }
        };

        try {
            boolean result = thread.execute(id).get(3, TimeUnit.SECONDS);
            return result;
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }
}

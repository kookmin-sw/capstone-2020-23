package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.ContentDao;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.ContentStorage;
import com.capstone.moayo.storage.DataBindingStorage;
import com.capstone.moayo.storage.StorageFactory;

public class StorageFactoryCreator implements StorageFactory{
    private volatile static StorageFactory instance;
    private CategoryStorage categoryStorage;
    private ContentStorage contentStorage;
    private DataBindingStorage dataBindingStorage;

    private CategoryDao categoryDao;
    private ContentDao contentDao;
    private static DBHelper mDBHelper;

    public static synchronized StorageFactory getInstance() {
        if(instance == null) {
            synchronized (StorageFactoryCreator.class) {
                if(instance == null) {
                    instance = new StorageFactoryCreator();
                }
            }
        }
        return instance;
    }

    @Override
    public CategoryStorage requestCategoryStorage(Context context) {
        if(categoryStorage == null) {
            categoryStorage = new ConcreteCategoryStorage(context);
        }
        return categoryStorage;
    }

    @Override
    public ContentStorage requestContentStorage(Context context) {
        if(contentStorage == null) {
            contentStorage = new ConcreteContentStorage(context);
        }
        return contentStorage;
    }

    @Override
    public DataBindingStorage requestDataBindingStorage(Context context) {
        if(dataBindingStorage == null) {
            dataBindingStorage = new ConcreteDataBindingStorage(context);
        }
        return dataBindingStorage;
    }

    @Override
    public DBHelper initDao(Context context) {
        mDBHelper = new DBHelper(context);
        mDBHelper.init();
        SQLiteDatabase db = mDBHelper.getWritableDB();
        mDBHelper.create(db);
        db.close();
        return mDBHelper;
    }
}
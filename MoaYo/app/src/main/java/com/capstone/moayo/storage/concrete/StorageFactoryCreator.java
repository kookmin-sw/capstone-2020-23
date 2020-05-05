package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.PostDao;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.HashtagStorage;
import com.capstone.moayo.storage.PostStorage;
import com.capstone.moayo.storage.StorageFactory;

public class StorageFactoryCreator implements StorageFactory{
    private volatile static StorageFactory instance;
    private CategoryStorage categoryStorage;
    private PostStorage contentStorage;
    private DogamStorage dogamStorage;
    private HashtagStorage hashtagStorage;

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
    public DogamStorage requestDogamStorage(Context context) {
        if(dogamStorage == null)
            dogamStorage = new ConcreteDogamStorage(context);
        return dogamStorage;
    }

    @Override
    public PostStorage requestPostStorage(Context context) {
        if(contentStorage == null) {
            contentStorage = new ConcretePostStorage(context);
        }
        return contentStorage;
    }

    @Override
    public HashtagStorage requestHashtagStorage(Context context) {
        if(hashtagStorage == null)
            hashtagStorage = new ConcreteHashtagStorage(context);
        return hashtagStorage;
    }
}

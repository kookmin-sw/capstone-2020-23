package com.capstone.moayo.storage.concrete;

import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.ContentStorage;
import com.capstone.moayo.storage.DataBindingStorage;
import com.capstone.moayo.storage.StorageFactory;

public class StorageFactoryCreator implements StorageFactory{
    private volatile static StorageFactory instance;
    private CategoryStorage categoryStorage;
    private ContentStorage contentStorage;
    private DataBindingStorage dataBindingStorage;

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
    public CategoryStorage requestCategoryStorage() {
        if(categoryStorage == null) {
            categoryStorage = new ConcreteCategoryStorage();
        }
        return categoryStorage;
    }

    @Override
    public ContentStorage requestContentStorage() {
        if(contentStorage == null) {
            contentStorage = new ConcreteContentStorage();
        }
        return contentStorage;
    }

    @Override
    public DataBindingStorage requestDataBindingStorage() {
        if(dataBindingStorage == null) {
            dataBindingStorage = new ConcreteDataBindingStorage();
        }
        return dataBindingStorage;
    }
}

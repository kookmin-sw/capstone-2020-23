package com.capstone.moayo.service.concrete;

import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.ContentService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.service.ServiceFactory;

public class ServiceFactoryCreator implements ServiceFactory{
    private volatile static ServiceFactory instance;
    private CategoryService categoryService;
    private DataBindingService dataBindingService;
    private ContentService contentService;

    public static synchronized ServiceFactory getInstance() {
        if(instance == null) {
            synchronized (ServiceFactoryCreator.class) {
                if(instance == null) {
                    instance = new ServiceFactoryCreator();
                }
            }
        }
        return instance;
    }

    @Override
    public CategoryService requestCategoryService() {
        if(categoryService == null) {
            categoryService = new ConcreteCategoryService();
        }
        return categoryService;
    }

    @Override
    public ContentService requestContentService() {
        if(contentService == null) {
            contentService = new ConcreteContentService();
        }
        return contentService;
    }

    @Override
    public DataBindingService requestDataBindingService() {
        if(dataBindingService == null) {
            dataBindingService = new ConcreteDataBindingService();
        }

        return dataBindingService;
    }
}

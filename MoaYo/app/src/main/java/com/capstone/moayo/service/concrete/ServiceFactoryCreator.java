package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.service.ServiceFactory;

public class ServiceFactoryCreator implements ServiceFactory{
    private volatile static ServiceFactory instance;
    private CategoryService categoryService;
    private DataBindingService dataBindingService;
    private PostService contentService;

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
    public CategoryService requestCategoryService(Context context) {
        if(categoryService == null) {
            categoryService = new ConcreteCategoryService(context);
        }
        return categoryService;
    }

    @Override
    public PostService requestPostService(Context context) {
        if(contentService == null) {
            contentService = new ConcretePostService(context);
        }
        return contentService;
    }

    @Override
    public DataBindingService requestDataBindingService(Context context) {
        if(dataBindingService == null) {
            dataBindingService = new ConcreteDataBindingService(context);
        }

        return dataBindingService;
    }
}

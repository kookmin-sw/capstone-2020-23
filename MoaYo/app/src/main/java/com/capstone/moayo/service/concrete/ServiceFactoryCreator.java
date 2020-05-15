package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.ServiceFactory;
import com.capstone.moayo.service.ShareService;

public class ServiceFactoryCreator implements ServiceFactory{
    private volatile static ServiceFactory instance;
    private CategoryService categoryService;
    private SearchService searchService;
    private PostService contentService;
    private ShareService shareService;

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
    public SearchService requestSearchService(Context context) {
        if(searchService == null)
            searchService = new ConcreteSearchService(context);
        return searchService;
    }

    @Override
    public ShareService requestShareService(Context context) {
        if(shareService == null)
            shareService = new ConcreteShareService();
        return shareService;
    }
}

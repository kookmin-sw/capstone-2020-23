package com.capstone.moayo.service;

import android.content.Context;

public interface ServiceFactory {
    public CategoryService requestCategoryService(Context context);
    public PostService requestContentService(Context context);
    public DataBindingService requestDataBindingService(Context context);
}

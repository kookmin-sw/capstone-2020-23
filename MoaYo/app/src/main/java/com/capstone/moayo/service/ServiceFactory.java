package com.capstone.moayo.service;

import android.content.Context;

public interface ServiceFactory {
    public CategoryService requestCategoryService(Context context);
    public PostService requestPostService(Context context);
    public DataBindingService requestDataBindingService(Context context);
}

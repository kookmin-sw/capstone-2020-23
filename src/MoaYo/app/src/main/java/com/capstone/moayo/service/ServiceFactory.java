package com.capstone.moayo.service;

import android.content.Context;

public interface ServiceFactory {
    public CategoryService requestCategoryService(Context context);
    public PostService requestPostService(Context context);
    public SearchService requestSearchService(Context context);
    public ShareService requestShareService(Context context);
}

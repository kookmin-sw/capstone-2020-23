package com.capstone.moayo.service;

public interface ServiceFactory {
    public CategoryService requestCategoryService();
    public ContentService requestContentService();
    public DataBindingService requestDataBindingService();
}

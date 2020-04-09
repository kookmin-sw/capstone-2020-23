package com.capstone.moayo.storage;

public interface StorageFactory {
    public CategoryStorage requestCategoryStorage();
    public ContentStorage requestContentStorage();
    public DataBindingStorage requestDataBindingStorage();
}

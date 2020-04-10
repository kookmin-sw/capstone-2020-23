package com.capstone.moayo.storage;

import android.content.Context;

public interface StorageFactory {
    public CategoryStorage requestCategoryStorage();
    public ContentStorage requestContentStorage();
    public DataBindingStorage requestDataBindingStorage();
    public void initDao(Context context);
}

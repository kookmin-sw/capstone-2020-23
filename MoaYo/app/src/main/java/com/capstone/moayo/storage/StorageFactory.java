package com.capstone.moayo.storage;

import android.content.Context;

import com.capstone.moayo.dao.sqlite.DBHelper;

public interface StorageFactory {
    public CategoryStorage requestCategoryStorage();
    public ContentStorage requestContentStorage();
    public DataBindingStorage requestDataBindingStorage();
    public DBHelper initDao(Context context);
}

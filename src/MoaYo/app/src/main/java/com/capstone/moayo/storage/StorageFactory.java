package com.capstone.moayo.storage;

import android.content.Context;

import com.capstone.moayo.dao.sqlite.DBHelper;

public interface StorageFactory {
    public CategoryStorage requestCategoryStorage(Context context);
    public PostStorage requestContentStorage(Context context);
    public DataBindingStorage requestDataBindingStorage(Context context);
    public DBHelper initDao(Context context);
}

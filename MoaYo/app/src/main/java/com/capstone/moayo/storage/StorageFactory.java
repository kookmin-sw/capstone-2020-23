package com.capstone.moayo.storage;

import android.content.Context;

import com.capstone.moayo.dao.sqlite.DBHelper;

public interface StorageFactory {
    public DogamStorage requestDogamStorage(Context context);
    public CategoryStorage requestCategoryStorage(Context context);
    public PostStorage requestPostStorage(Context context);
    public HashtagStorage requestHashtagStorage(Context context);
}

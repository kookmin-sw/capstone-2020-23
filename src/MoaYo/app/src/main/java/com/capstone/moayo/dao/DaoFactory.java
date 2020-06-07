package com.capstone.moayo.dao;

import android.content.Context;

import com.capstone.moayo.dao.sqlite.DBHelper;

public interface DaoFactory {
    public CategoryDao requestCategoryDao();
    public CategoryHashtagDao requestCategoryHashtagDao();
    public CategoryPostDao requestCategoryPostDao();
    public DogamDao requestDogamDao();
    public HashtagDao requestHashtagDao();
    public PostDao requsetPostDao();
    public DogamLikeDao requestDogamLikeDao();
    public DBHelper initDao(Context context);

}

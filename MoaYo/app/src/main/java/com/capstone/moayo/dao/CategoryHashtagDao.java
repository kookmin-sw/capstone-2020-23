package com.capstone.moayo.dao;

import com.capstone.moayo.dao.mapping.CategoryHashTagMapping;
import com.capstone.moayo.dao.mapping.CategoryMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;

import java.util.List;

public interface CategoryHashtagDao {
    long insert(DBHelper dbHelper,CategoryHashTagMapping categoryHashTagMapping);
    List<CategoryHashTagMapping> selectByDogamId(DBHelper dbHelper,int dogamId);
    List<CategoryHashTagMapping> selectByCategoryId(DBHelper dbHelper,int categoryId);
    List<CategoryHashTagMapping> selectByHashtag(DBHelper dbHelper,String hashtag);
    long replace(DBHelper dbHelper, CategoryHashTagMapping categoryHashTagMapping);
    boolean delete(DBHelper dbHelper, CategoryHashTagMapping categoryHashTagMapping);
}

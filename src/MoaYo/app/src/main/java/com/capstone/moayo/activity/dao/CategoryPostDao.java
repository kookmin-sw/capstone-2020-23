package com.capstone.moayo.dao;

import com.capstone.moayo.dao.mapping.CategoryPostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;

import java.util.List;

public interface CategoryPostDao {
    long insert(DBHelper dbHelper,CategoryPostMapping categoryPostMapping);
    List<CategoryPostMapping> selectByDogamId(DBHelper dbHelper,int dogamId);
    List<CategoryPostMapping> selectByCategoryId(DBHelper dbHelper,int categoryId);
    List<CategoryPostMapping> selectByPostId(DBHelper dbHelper,int postId);
    boolean delete(DBHelper dbHelper, int nodeId, int postId);
    boolean isExist(DBHelper dbHelper, int nodeId, int postId);
}

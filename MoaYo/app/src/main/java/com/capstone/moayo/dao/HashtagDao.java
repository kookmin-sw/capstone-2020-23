package com.capstone.moayo.dao;

import com.capstone.moayo.dao.mapping.HashTagMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;

import java.util.List;

public interface HashtagDao {
    long insert(DBHelper dbHelper,HashTagMapping hashTagMapping);
    boolean delete(DBHelper dbHelper,HashTagMapping hashTagMapping);
    boolean isExist(DBHelper dbHelper,String hashtag);
    List<HashTagMapping> selectAll(DBHelper dbHelper);
}

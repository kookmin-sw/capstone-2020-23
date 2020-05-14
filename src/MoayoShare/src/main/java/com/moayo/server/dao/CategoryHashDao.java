package com.moayo.server.dao;

import com.moayo.server.model.CategoryHashModel;

import java.util.List;

public interface CategoryHashDao {
    long insertCategoryHashtag(CategoryHashModel categoryHashModel);

    long deleteByDogamId(int dogamId);
    long deleteByCategoryId(int categoryId,int dogamId);
    long deleteByHashtag(String hashtag);

    CategoryHashModel[] getByDogamId(int dogamId);
    CategoryHashModel getByCategoryId(int categoryId,int dogamId);
    CategoryHashModel getByHashtag(String hashtag);
}

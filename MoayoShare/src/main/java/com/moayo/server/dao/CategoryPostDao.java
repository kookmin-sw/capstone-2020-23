package com.moayo.server.dao;

import com.moayo.server.model.CategoryHashModel;
import com.moayo.server.model.CategoryPostModel;

public interface CategoryPostDao {
    long insertCategoryPost(CategoryPostModel categoryPostModel);
    long updateCategoryPost(CategoryPostModel categoryPostModel);

    long deleteByDogamId(int dogamId);
    long deleteByCategoryId(int categoryId);
    long deleteByPostId(String postId);

    CategoryPostModel getByDogamId(int dogamId);
    CategoryPostModel getByCategoryId(int categoryId);
    CategoryPostModel getByPostId(int postId);
}

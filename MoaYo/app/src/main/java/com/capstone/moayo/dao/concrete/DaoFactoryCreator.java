package com.capstone.moayo.dao.concrete;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.CategoryHashtagDao;
import com.capstone.moayo.dao.CategoryPostDao;
import com.capstone.moayo.dao.DaoFactory;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.HashtagDao;
import com.capstone.moayo.dao.PostDao;
import com.capstone.moayo.entity.Post;

public class DaoFactoryCreator implements DaoFactory {
    private static volatile DaoFactory instance;
    private CategoryDao categoryDao;
    private CategoryHashtagDao categoryHashtagDao;
    private CategoryPostDao categoryPostDao;
    private DogamDao dogamDao;
    private HashtagDao hashtagDao;
    private PostDao postDao;

    public static synchronized DaoFactory getInstance() {
        if(instance == null) {
            synchronized (DaoFactoryCreator.class) {
                if(instance == null) {
                    instance = new DaoFactoryCreator();
                }
            }
        }

        return instance;
    }

    @Override
    public CategoryDao requestCategoryDao() {
        if(categoryDao == null)
            categoryDao = new CategoryDaoImpl();
        return categoryDao;
    }

    @Override
    public CategoryHashtagDao requestCategoryHashtagDao() {
        if(categoryHashtagDao == null)
            categoryHashtagDao = new CategoryHashtagDaoImpl();
        return categoryHashtagDao;
    }

    @Override
    public CategoryPostDao requestCategoryPostDao() {
        if(categoryPostDao == null)
            categoryPostDao = new CategoryPostDaoImpl();
        return categoryPostDao;
    }

    @Override
    public DogamDao requestDogamDao() {
        if(dogamDao == null)
            dogamDao = new DogamDaoImpl();
        return dogamDao;
    }

    @Override
    public HashtagDao requestHashtagDao() {
        if(hashtagDao == null)
            hashtagDao = new HashtagDaoImpl();
        return hashtagDao;
    }

    @Override
    public PostDao requsetPostDao() {
        if(postDao == null)
            postDao = new PostDaoImpl();
        return postDao;
    }
}

package com.capstone.moayo.dao;

public interface DaoFactory {
    public CategoryDao requestCategoryDao();
    public CategoryHashtagDao requestCategoryHashtagDao();
    public CategoryPostDao requestCategoryPostDao();
    public DogamDao requestDogamDao();
    public HashtagDao requestHashtagDao();
    public PostDao requsetPostDao();
}

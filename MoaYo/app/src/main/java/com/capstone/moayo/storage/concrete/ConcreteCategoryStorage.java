package com.capstone.moayo.storage.concrete;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.storage.CategoryStorage;

import java.util.List;

public class ConcreteCategoryStorage implements CategoryStorage {

    public ConcreteCategoryStorage() {

    }

    @Override
    public String create(Category category) {
        return null;
    }

    @Override
    public List<Category> retrieveByTitle(String title) {
        return null;
    }

    @Override
    public Category retrieveById(int id) {
        return null;
    }

    @Override
    public String update(Category category) {
        return null;
    }

    @Override
    public String remove(int id) {
        return null;
    }
}

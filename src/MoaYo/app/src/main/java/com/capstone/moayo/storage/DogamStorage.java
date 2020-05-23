package com.capstone.moayo.storage;

import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.entity.Category;

import java.util.Collection;
import java.util.List;

public interface DogamStorage {
    public int create(Category category);
    public DogamMapping retrieveById(int id);
    public void update(Category category);
    public boolean remove(int id);
    public Collection<Category> retrieveAll();
}

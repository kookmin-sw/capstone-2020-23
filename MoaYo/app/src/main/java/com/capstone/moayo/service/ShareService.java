package com.capstone.moayo.service;

import com.capstone.moayo.service.dto.CategoryDto;

import java.util.List;

public interface ShareService {
    public void registerDogam(CategoryDto categoryDto);
    public CategoryDto loadDogam(int dogamId);
    public List<CategoryDto> loadAll();
    public void deleteDogam(int dogamId);
}

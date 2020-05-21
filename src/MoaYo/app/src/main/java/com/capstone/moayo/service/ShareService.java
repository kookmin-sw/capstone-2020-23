package com.capstone.moayo.service;

import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.DogamStatus;

import java.util.List;

public interface ShareService {
    public void registerDogam(CategoryDto categoryDto, int status);
    public CategoryDto loadDogamFromServer(int dogamId);
    public List<CategoryDto> loadAllFromServer();
    public void deleteDogam(int dogamId);
}

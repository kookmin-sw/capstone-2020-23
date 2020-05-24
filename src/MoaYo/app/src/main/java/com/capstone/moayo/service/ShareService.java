package com.capstone.moayo.service;

import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.DogamStatus;

import java.util.List;

public interface ShareService {
    public String registerDogam(CategoryDto categoryDto, int status);
    public CategoryDto findDogamById(int dogamId);
    public List<CategoryDto> findAll();
    public List<CategoryDto> findDogamByWriter(String writer);
    public List<CategoryDto> findDogamByKeyword(String keyword);
    public void deleteDogam(int dogamId);
}

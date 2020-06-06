package com.capstone.moayo.service;

import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.DogamStatus;

import java.util.List;

public interface ShareService {
    String registerDogam(CategoryDto categoryDto, int status);
    CategoryDto findDogamById(int dogamId);
    List<CategoryDto> findAll();
    List<CategoryDto> findDogamByWriter(String writer);
    List<CategoryDto> findDogamByKeyword(String keyword);
    int updateLike(int dogamId, boolean isLiked);
    String deleteDogam(int dogamId);
    List<CategoryDto> sortByLike(List<CategoryDto> categoryDtos);
    List<CategoryDto> sortByTime(List<CategoryDto> categoryDtos);
}

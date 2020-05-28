package com.capstone.moayo.service;

import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;

import java.util.List;

public interface SearchService {
    public List<InstantPost> requestData(CategoryNodeDto firstNode, CategoryNodeDto secondNode);
    public void initCache();
}

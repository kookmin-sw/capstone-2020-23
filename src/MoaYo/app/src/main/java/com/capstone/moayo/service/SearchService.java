package com.capstone.moayo.service;

import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;

public interface SearchService {
    public RespondForm requestData(CategoryNodeDto firstNode, CategoryNodeDto secondNode);
    public void initCache();
}

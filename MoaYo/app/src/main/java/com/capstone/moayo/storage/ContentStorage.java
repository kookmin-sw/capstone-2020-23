package com.capstone.moayo.storage;

import com.capstone.moayo.service.dto.ContentDto;

import java.util.List;

public interface ContentStorage {
    public String createContent();
    public List<ContentDto> findContentByNodeId();
    public List<ContentDto> findContentByDogamId();
    public ContentDto findContentById();
    public void modifyContent();
    public void removeContent();
}

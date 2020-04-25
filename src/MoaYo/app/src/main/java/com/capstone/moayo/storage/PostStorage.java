package com.capstone.moayo.storage;

import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface PostStorage {
    public String createContent();
    public List<PostDto> findContentByNodeId();
    public List<PostDto> findContentByDogamId();
    public PostDto findContentById();
    public void modifyContent();
    public void removeContent();
}

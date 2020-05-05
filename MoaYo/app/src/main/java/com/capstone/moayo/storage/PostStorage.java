package com.capstone.moayo.storage;

import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface PostStorage {
    public String createPost();
    public List<PostDto> findPostByNodeId();
    public List<PostDto> findPostByDogamId();
    public PostDto findPostById();
    public void modifyPost();
    public void removePost();
}

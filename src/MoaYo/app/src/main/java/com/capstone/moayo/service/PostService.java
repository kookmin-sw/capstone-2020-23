package com.capstone.moayo.service;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface PostService {
    public String createPost(PostDto newPost);
    public List<PostDto> findPostByCategoryNodeId(int id);
    public PostDto findPostById(int id);
    public boolean modifyPost(PostDto postDto);
    public void deletePostById(int id);
}
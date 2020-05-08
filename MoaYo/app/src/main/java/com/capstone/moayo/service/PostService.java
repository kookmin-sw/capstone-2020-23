package com.capstone.moayo.service;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface PostService {
    public int createPost(InstantPost newPost, int nodeId, int dogamId);
    public List<PostDto> findPostByCategoryNodeId(int id);
    public PostDto findPostById(int id);
    public boolean modifyPost(PostDto postDto);
    public void deletePostById(int id);
}

package com.capstone.moayo.service;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(InstantPost newPost, int nodeId, int dogamId);
    String createPost(PostDto newPostDto, int nodeId, int dogamId);
    public List<PostDto> findPostByCategoryNodeId(int id);
    public PostDto findPostById(int nodeId, int postId);
    public boolean modifyPost(PostDto postDto);
    public void deletePostByNodeId(int nodeId);
    public String deletePostById(int nodeId, int postId);
    public void init();
}

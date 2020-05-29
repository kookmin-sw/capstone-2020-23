package com.capstone.moayo.storage;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface PostStorage {
    public int createPost(Post post);
    public List<Post> retrievePostByNodeId(int nodeId);
    public List<Post> retrievePostByDogamId(int dogamId);
    public Post retrievePostById(int nodeId, int postId);
    public void modifyPost(Post post);
    public String removePost(int nodeId, int postId);
}

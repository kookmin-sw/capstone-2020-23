package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.PostStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;

import java.util.List;

public class ConcretePostService implements PostService {
    private PostStorage postStorage;

    public ConcretePostService(Context context) {
        postStorage = StorageFactoryCreator.getInstance().requestPostStorage(context);
    }

    @Override
    public String createPost(InstantPost newPost) {
        Post post = new Post(newPost.getSrc(), newPost.getUrl(), newPost.getText(), newPost.getLike());
        return null;
    }

    @Override
    public List<PostDto> findPostByCategoryNodeId(int id) {
        return null;
    }

    @Override
    public PostDto findPostById(int id) {
        return null;
    }

    @Override
    public boolean modifyPost(PostDto postDto) {
        return false;
    }

    @Override
    public void deletePostById(int id) {

    }
}

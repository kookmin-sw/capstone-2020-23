package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.PostStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;

import java.util.ArrayList;
import java.util.List;

public class ConcretePostService implements PostService {
    private PostStorage postStorage;
    private DogamStorage dogamStorage;

    public ConcretePostService(Context context) {
        postStorage = StorageFactoryCreator.getInstance().requestPostStorage(context);
        dogamStorage = StorageFactoryCreator.getInstance().requestDogamStorage(context);
    }

    @Override
    public PostDto createPost(InstantPost newPost, int nodeId, int dogamId) {
        Post post = new Post(newPost.getSrc(), newPost.getUrl(), newPost.getText(), newPost.getLike(), nodeId, dogamId);
        int postId = postStorage.createPost(post);
        Category category = dogamStorage.retrieveById(dogamId);
        category.setUrl(post.getImgUrl());
        dogamStorage.update(category);
        post.setId(postId);
        PostDto postDto = post.toPostDto();
        return postDto;
    }

    @Override
    public String createPost(PostDto newPostDto, int nodeId, int dogamId) {
        Post post = newPostDto.toPost();
        post.setCategoryNodeId(nodeId);
        post.setDogamId(dogamId);
        int postId = postStorage.createPost(post);
        post.setId(postId);
        return String.valueOf(postId);
    }

    @Override
    public List<PostDto> findPostByCategoryNodeId(int nodeId) {
        List<PostDto> postDtoList = new ArrayList<>();
        List<Post> postList = postStorage.retrievePostByNodeId(nodeId);
        if(postList == null) {
            return null;
        }

        for(Post post : postList) {
            PostDto postDto = post.toPostDto();
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public PostDto findPostById(int nodeId, int postId) {
        PostDto foundPostDto = null;

        try {
            Post foundPost = postStorage.retrievePostById(nodeId, postId);
            if(foundPost == null) return null;

            foundPostDto = foundPost.toPostDto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundPostDto;
    }

    @Override
    public boolean modifyPost(PostDto postDto) {
        return false;
    }

    @Override
    public void deletePostByNodeId(int nodeId) {

    }

    @Override
    public String deletePostById(int nodeId, int postId) {
        try {
            Post foundPost = postStorage.retrievePostById(nodeId, postId);

            postStorage.removePost(nodeId, postId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void init() {
        postStorage.init();
    }
}

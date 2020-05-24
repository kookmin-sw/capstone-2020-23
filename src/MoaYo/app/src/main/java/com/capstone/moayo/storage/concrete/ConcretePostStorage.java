package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.os.AsyncTask;

import com.capstone.moayo.dao.CategoryPostDao;
import com.capstone.moayo.dao.PostDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.CategoryPostMapping;
import com.capstone.moayo.dao.mapping.PostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.PostStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcretePostStorage implements PostStorage {

    private PostDao postDao;
    private CategoryPostDao categoryPostDao;
    private DBHelper dbHelper;

    private Context context;

    public ConcretePostStorage(Context context) {
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
        postDao = DaoFactoryCreator.getInstance().requsetPostDao();
        categoryPostDao = DaoFactoryCreator.getInstance().requestCategoryPostDao();
        this.context = context;
    }

    @Override
    public int createPost(Post post) {
        if(postDao.isExist(dbHelper, new PostMapping(post.getId(), post.getUrl(), post.getImgUrl(), post.getHashtag(), post.getLike()))) {
            PostMapping postMapping = postDao.selectByUrl(dbHelper, post.getUrl());
            createCategoryPost(post.getCategoryNodeId(), post.getDogamId(), postMapping.getId());
            return postMapping.getId();
        } else {
            int postId = (int) postDao.insert(dbHelper, new PostMapping(post.getId(), post.getUrl(), post.getImgUrl(), post.getHashtag(), post.getLike()));
            createCategoryPost(post.getCategoryNodeId(), post.getDogamId(), postId);
            return postId;
        }

    }

    @Override
    public List<Post> retrievePostByNodeId(int nodeId) {
        List<CategoryPostMapping> categoryPostMappingList = categoryPostDao.selectByCategoryId(dbHelper, nodeId);
        if(categoryPostMappingList == null) return null;

        List<Post> postList = new ArrayList<>();
        for(CategoryPostMapping mapping : categoryPostMappingList) {
            PostMapping newMapping = postDao.selectById(dbHelper, mapping.getPostId());
            Post post = new Post(newMapping.getImgUrl(), newMapping.getUrl(), newMapping.getHashTag(), newMapping.getLike(), mapping.getCategoryId(), mapping.getDogamId());
            post.setId(newMapping.getId());
            postList.add(post);
        }
        return postList;
    }

    @Override
    public List<Post> retrievePostByDogamId(int dogamId) {
        return null;
    }

    @Override
    public Post retrievePostById(int nodeId, int postId) {
        Post foundPost = null;
        PostMapping postMapping = postDao.selectById(dbHelper, postId);
        List<CategoryPostMapping> mappingList = categoryPostDao.selectByPostId(dbHelper, postId);

        for(CategoryPostMapping mapping : mappingList) {
            if(mapping.getCategoryId() == nodeId) {
                foundPost = new Post(
                        postMapping.getImgUrl(), postMapping.getUrl(), postMapping.getHashTag(), postMapping.getLike(),
                        mapping.getCategoryId(), mapping.getDogamId());
                foundPost.setId(mapping.getPostId());
                break;
            }
        }
        return foundPost;
    }


    @Override
    public void modifyPost(Post post) {
        boolean result = postDao.update(dbHelper, new PostMapping(post.getId(), post.getUrl(), post.getImgUrl(), post.getHashtag(), post.getLike()));
    }

    @Override
    public void removePost(int nodeId, int postId) {
        boolean result = categoryPostDao.delete(dbHelper, nodeId, postId);
        if(result != true) return;

        List<CategoryPostMapping> mapping = categoryPostDao.selectByPostId(dbHelper, postId);
        if(mapping.size() == 0) postDao.delete(dbHelper, postId);
    }

    private void createCategoryPost(int nodeId, int dogamId, int id) {
        if(categoryPostDao.isExist(dbHelper, nodeId, id)) return;

        categoryPostDao.insert(dbHelper, new CategoryPostMapping(dogamId, nodeId, id));
    }
}

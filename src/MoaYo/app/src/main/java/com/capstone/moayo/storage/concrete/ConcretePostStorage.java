package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.os.AsyncTask;

import com.capstone.moayo.dao.CategoryPostDao;
import com.capstone.moayo.dao.PostDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.CategoryPostMapping;
import com.capstone.moayo.dao.mapping.PostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.PostStorage;
import com.capstone.moayo.storage.map.MemoryMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcretePostStorage implements PostStorage {

    private PostDao postDao;
    private CategoryPostDao categoryPostDao;
    private DBHelper dbHelper;
    private Map<Integer, Category> categoryMap;
    private Map<Integer, CategoryNode> categoryNodeMap;
    private Context context;

    public ConcretePostStorage(Context context) {
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
        postDao = DaoFactoryCreator.getInstance().requsetPostDao();
        categoryPostDao = DaoFactoryCreator.getInstance().requestCategoryPostDao();
        categoryMap = MemoryMap.getInstance().getCategoryMap();
        categoryNodeMap = MemoryMap.getInstance().getCategoryNodeMap();

        this.context = context;
    }

    @Override
    public int createPost(Post post) {
        int postId = (int) postDao.insert(dbHelper, new PostMapping(post.getId(), post.getUrl(), post.getImgUrl(), post.getHashtag(), post.getLike()));
        createCategoryPost(post.getCategoryNodeId(), post.getDogamId(), postId);
        categoryNodeMap.get(post.getCategoryNodeId()).getPosts().add(post);
        return postId;
    }

    @Override
    public List<Post> retrievePostByNodeId(int nodeId) {

        List<Post> postList = new ArrayList<>();
        if(!categoryNodeMap.keySet().contains(nodeId)) return postList;
        if(categoryNodeMap.get(nodeId).getPosts().isEmpty()) {
            List<CategoryPostMapping> categoryPostMappingList = categoryPostDao.selectByCategoryId(dbHelper, nodeId);
            if (categoryPostMappingList == null) return postList;

            for (CategoryPostMapping mapping : categoryPostMappingList) {
                PostMapping newMapping = postDao.selectById(dbHelper, mapping.getPostId());
                Post post = new Post(newMapping.getImgUrl(), newMapping.getUrl(), newMapping.getHashTag(), newMapping.getLike(), mapping.getCategoryId(), mapping.getDogamId());
                post.setId(newMapping.getId());
                postList.add(post);
            }
            categoryNodeMap.get(nodeId).getPosts().addAll(postList);
        } else {
            postList = categoryNodeMap.get(nodeId).getPosts();
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
    public String removePost(int nodeId, int postId) {
        boolean result = categoryPostDao.delete(dbHelper, nodeId, postId);
        if(result != true) return "fail to remove.";

        CategoryNode node = categoryNodeMap.get(nodeId);
        for(Post post : node.getPosts()) {
            if(post.getId() == postId) {
                categoryNodeMap.get(nodeId).getPosts().remove(post);
                break;
            }
        }

        List<CategoryPostMapping> mapping = categoryPostDao.selectByPostId(dbHelper, postId);
        if(mapping.size() == 0) postDao.delete(dbHelper, postId);

        return "success to remove.";
    }

    @Override
    public void init() {
        for (Category category : categoryMap.values()) {
            CategoryNode rootNode = category.getRootNode();
            categoryNodeMap.put(rootNode.getId(), rootNode);
            for (CategoryNode secondNode : rootNode.getLowLayer()) {
                List<CategoryPostMapping> mappingList = categoryPostDao.selectByCategoryId(dbHelper, secondNode.getId());
                if (mappingList == null) continue;

                for (CategoryPostMapping mapping : mappingList) {
                    PostMapping postMapping = postDao.selectById(dbHelper, mapping.getPostId());
                    Post post = new Post(postMapping.getImgUrl(), postMapping.getUrl(), postMapping.getHashTag(), postMapping.getLike(), mapping.getCategoryId(), mapping.getDogamId());
                    post.setId(postMapping.getId());
                    secondNode.getPosts().add(post);
                }
                categoryNodeMap.put(secondNode.getId(), secondNode);
                for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                    List<CategoryPostMapping> mappingList1 = categoryPostDao.selectByCategoryId(dbHelper, thirdNode.getId());
                    {
                        if (mappingList == null) continue;

                        for (CategoryPostMapping mapping : mappingList1) {
                            PostMapping postMapping = postDao.selectById(dbHelper, mapping.getPostId());
                            Post post = new Post(postMapping.getImgUrl(), postMapping.getUrl(), postMapping.getHashTag(), postMapping.getLike(), mapping.getCategoryId(), mapping.getDogamId());
                            post.setId(postMapping.getId());
                            thirdNode.getPosts().add(post);
                        }
                        categoryNodeMap.put(thirdNode.getId(), thirdNode);
                    }
                }
            }
        }
    }

    private void createCategoryPost(int nodeId, int dogamId, int id) {
        if(categoryPostDao.isExist(dbHelper, nodeId, id)) return;

        categoryPostDao.insert(dbHelper, new CategoryPostMapping(dogamId, nodeId, id));
    }
}

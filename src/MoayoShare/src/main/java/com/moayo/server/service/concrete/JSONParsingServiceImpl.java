package com.moayo.server.service.concrete;

import com.moayo.server.dao.*;
import com.moayo.server.model.*;
import com.moayo.server.service.JSONParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JSONParsingServiceImpl implements JSONParsingService {

    @Autowired
    DogamListDao dogamListDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    PostDao postDao;
    @Autowired
    HashtagDao hashtagDao;
    @Autowired
    CategoryPostDao categoryPostDao;
    @Autowired
    CategoryHashDao categoryHashDao;

    private Logger logger;

    public JSONParsingServiceImpl() {
        logger = LoggerFactory.getLogger(XMLParsingServiceImpl.class);
    }

    public void insertData(DogamModel dogamModel){
        DogamListModel dogamListModel = dogamModel.getDogamListModel();
        CategoryModel[] categoryModels = dogamModel.getCategoryModels();
        HashtagModel[] hashtagModels = dogamModel.getHashtagModels();
        PostModel[] postModels = dogamModel.getPostModels();
        CategoryHashModel[] categoryHashModels = dogamModel.getCategoryHashModels();
        CategoryPostModel[] categoryPostModels = dogamModel.getCategoryPostModels();

        dogamListDao.insertDogam(dogamListModel);
        Map<Integer,CategoryModel> categoryModelMap = categoryInsert(categoryModels,dogamListModel,categoryPostModels,categoryHashModels);
        postInsert(postModels,categoryPostModels);
        hashtagInsert(hashtagModels);
        categoryPostDao.insertAll(categoryPostModels);
        categoryHashDao.insertAll(categoryHashModels);
    }

    private void hashtagInsert(HashtagModel[] hashtagModels){
        for(HashtagModel hashtagModel : hashtagModels){
            try{
                hashtagDao.insertHashtag(hashtagModel);
            }catch (Exception e){
                logger.error(hashtagModel.getCo_hashtag() + " : duplicate.");
                continue;
            }
        }
    }
    private void postInsert(PostModel[] postModels,CategoryPostModel[] categoryPostModels){
        for(PostModel postModel : postModels){
            int origin = postModel.getCo_postId();
            postDao.insertPost(postModel);
            for(CategoryPostModel categoryPostModel : categoryPostModels){
                if(categoryPostModel.getCo_postId() == origin){
                    categoryPostModel.setCo_postId(postModel.getCo_postId());
                }
            }
        }
    }
    private Map<Integer,CategoryModel> categoryInsert(CategoryModel[] categoryModels,DogamListModel dogamListModel,
                                                     CategoryPostModel[] categoryPostModels, CategoryHashModel[] categoryHashModels){
        Map<Integer,CategoryModel> categoryModelMap = new HashMap<Integer,CategoryModel>();
        for(CategoryModel categoryModel : categoryModels){
            int origin = 0;
            if(categoryModel.getCo_categoryId() == categoryModel.getCo_parentCategoryId()){
                categoryModel.setCo_dogamId(dogamListModel.getCo_dogamId());
                origin = categoryModel.getCo_categoryId();
                categoryDao.foreignKeyOFF();
                categoryDao.insertCategory(categoryModel);
                categoryDao.foreignKeyON();
                categoryModel.setCo_parentCategoryId(categoryModel.getCo_categoryId());
                categoryDao.updateCategory(categoryModel);
                categoryModelMap.put(origin,categoryModel);
            }else{
                // 순서가 다를 경우도 고려해야함.
                categoryModel.setCo_dogamId(dogamListModel.getCo_dogamId());
                origin = categoryModel.getCo_parentCategoryId();
                categoryModel.setCo_parentCategoryId(categoryModelMap.get(origin).getCo_categoryId());
                categoryDao.insertCategory(categoryModel);
                categoryModelMap.put(origin,categoryModel);
            }

            for(CategoryPostModel categoryPostModel : categoryPostModels){
                if(categoryPostModel.getCo_categoryId() == origin){
                    categoryPostModel.setCo_categoryId(categoryModel.getCo_categoryId());
                    categoryPostModel.setCo_dogamId(categoryModel.getCo_dogamId());
                }
            }
            for(CategoryHashModel categoryHashModel : categoryHashModels){
                if(categoryHashModel.getco_categoryId() == origin){
                    categoryHashModel.setco_dogamId(categoryModel.getCo_dogamId());
                    categoryHashModel.setco_categoryId(categoryModel.getCo_categoryId());
                }
            }
        }
        return categoryModelMap;
    }
}

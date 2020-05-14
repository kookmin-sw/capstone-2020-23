package com.moayo.server.service.concrete;

import com.moayo.server.dao.*;
import com.moayo.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShareService {
    @Autowired
    DogamListDao dogamListDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryHashDao categoryHashDao;
    @Autowired
    CategoryPostDao categoryPostDao;
    @Autowired
    PostDao postDao;

    public DogamModel getDogam(int dogamId){
        DogamModel dogamModel = new DogamModel();
        dogamModel.setDogamListModel(dogamListDao.getDogamById(dogamId));
        dogamModel.setCategoryModels(categoryDao.getCategoryByDogamId(dogamId));

        CategoryHashModel[] categoryHashModels = categoryHashDao.getByDogamId(dogamId);
        dogamModel.setCategoryHashModels(categoryHashModels);

        CategoryPostModel[] categoryPostModels = categoryPostDao.getByDogamId(dogamId);
        dogamModel.setCategoryPostModels(categoryPostDao.getByDogamId(dogamId));

//        List<PostModel> postModelList = new ArrayList<PostModel>();
//        for(CategoryPostModel categoryPostModel : categoryPostModels){
//            postModelList.add(postDao.getPost(categoryPostModel.getCo_postId()));
//        }
//        dogamModel.setPostModels((PostModel[])postModelList.toArray());

        PostModel[] postModels = new PostModel[categoryPostModels.length];
        for(int i = 0;i<postModels.length;i++){
            postModels[i] = postDao.getPost(categoryPostModels[i].getCo_postId());
        }
        dogamModel.setPostModels(postModels);

//        List<HashtagModel> hashtagModelList = new ArrayList<HashtagModel>();
//        for(CategoryHashModel categoryHashModel : categoryHashModels){
//            hashtagModelList.add(new HashtagModel(categoryHashModel.getco_hashtag()));
//        }
//        dogamModel.setHashtagModels((HashtagModel[])hashtagModelList.toArray());

        HashtagModel[] hashtagModels = new HashtagModel[categoryHashModels.length];
        for(int i =0 ;i<hashtagModels.length;i++){
            hashtagModels[i] = new HashtagModel(categoryHashModels[i].getco_hashtag());
        }
        dogamModel.setHashtagModels(hashtagModels);

        return dogamModel;
    }

}

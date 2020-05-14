package com.capstone.moayo.util;

import com.capstone.moayo.entity.Model.CategoryHashtagModel;
import com.capstone.moayo.entity.Model.CategoryModel;
import com.capstone.moayo.entity.Model.CategoryPostModel;
import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.HashtagModel;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.entity.Model.PostModel;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

public class ShareUtil {
    public static ModelForm convertDogamToModelForm(CategoryDto categoryDto, int status) {
        DogamModel dogamModel = new DogamModel(categoryDto.getId(), categoryDto.getTitle(), categoryDto.getDescription(), status, categoryDto.getPassword());
        List<CategoryModel> categoryModels = new ArrayList<>();
        List<PostModel> postModels = new ArrayList<>();
        List<CategoryPostModel> categoryPostModels = new ArrayList<>();
        List<HashtagModel> hashtagModels = new ArrayList<>();
        List<CategoryHashtagModel> categoryHashtagModels = new ArrayList<>();

        CategoryNodeDto rootNode = categoryDto.getRootNode();
        CategoryModel rootModel = new CategoryModel(rootNode.getId(), categoryDto.getId(), rootNode.getTitle(), rootNode.getLevel(), rootNode.getId());
        categoryModels.add(rootModel);
        convertCategoryPost(postModels, categoryPostModels, categoryDto.getId(), rootNode);
        convertHash(hashtagModels, categoryHashtagModels, rootNode.getHashtags(), categoryDto.getId(), rootNode.getId());

        for(CategoryNodeDto secondNode : rootNode.getLowLayer()) {
            CategoryModel secondModel = new CategoryModel(secondNode.getId(), categoryDto.getId(), secondNode.getTitle(), secondNode.getLevel(), rootNode.getId());
            categoryModels.add(secondModel);
            convertCategoryPost(postModels, categoryPostModels, categoryDto.getId(), secondNode);
            convertHash(hashtagModels, categoryHashtagModels, secondNode.getHashtags(), categoryDto.getId(), secondNode.getId());

            for(CategoryNodeDto thirdNode : secondNode.getLowLayer()) {
                CategoryModel thirdModel = new CategoryModel(thirdNode.getId(), categoryDto.getId(), thirdNode.getTitle(), thirdNode.getLevel(), secondNode.getId());
                categoryModels.add(thirdModel);
                convertCategoryPost(postModels, categoryPostModels, categoryDto.getId(), thirdNode);
                convertHash(hashtagModels, categoryHashtagModels, thirdNode.getHashtags(), categoryDto.getId(), thirdNode.getId());
            }
        }

        ModelForm form = new ModelForm();
        form.setDogamModel(dogamModel);
        form.setCategoryModels(categoryModels.toArray(new CategoryModel[0]));
        form.setPostModels(postModels.toArray(new PostModel[0]));
        form.setCategoryPostModels(categoryPostModels.toArray(new CategoryPostModel[0]));
        form.setHashtagModels(hashtagModels.toArray(new HashtagModel[0]));
        form.setCategoryHashtagModels(categoryHashtagModels.toArray(new CategoryHashtagModel[0]));

        return form;
    }

    private static void convertCategoryPost(List<PostModel> postModels, List<CategoryPostModel> categoryPostModels, int dogamId, CategoryNodeDto categoryNodeDto) {
        for(PostDto postDto : categoryNodeDto.getPosts()) {
            postModels.add(new PostModel(postDto.getId(), postDto.getUrl(), postDto.getImgUrl(), postDto.getHashtag()));
            categoryPostModels.add(new CategoryPostModel(dogamId, categoryNodeDto.getId(), postDto.getId()));
        }
    }

    private static void convertHash(List<HashtagModel> hashtagModels, List<CategoryHashtagModel> categoryHashtagModels, List<String> hashtags, int dogamId, int categoryId) {
        for(String hash : hashtags)
            categoryHashtagModels.add(new CategoryHashtagModel(dogamId, categoryId, hash));

        for(HashtagModel hashtagModel : hashtagModels) {
            if(hashtags.contains(hashtagModel.getHashtag())) hashtags.remove(hashtagModel.getHashtag());
        }

        for(String hash : hashtags) {
            hashtagModels.add(new HashtagModel(hash));
        }
    }
}

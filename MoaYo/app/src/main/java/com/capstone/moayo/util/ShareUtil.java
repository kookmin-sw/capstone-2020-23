package com.capstone.moayo.util;

import android.util.Pair;

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShareUtil {
    public static ModelForm convertDogamToModelForm(CategoryDto categoryDto, DogamStatus status) {
        DogamModel dogamModel = new DogamModel(categoryDto.getId(), categoryDto.getTitle(), categoryDto.getDescription(), String.valueOf(status), categoryDto.getPassword());
        List<CategoryModel> categoryModels = new ArrayList<>();
        List<PostModel> postModels = new ArrayList<>();
        List<CategoryPostModel> categoryPostModels = new ArrayList<>();
        List<HashtagModel> hashtagModels = new ArrayList<>();
        List<CategoryHashtagModel> categoryHashtagModels = new ArrayList<>();

        CategoryNodeDto rootNode = categoryDto.getRootNode();
        CategoryModel rootModel = new CategoryModel(rootNode.getId(), categoryDto.getId(), rootNode.getTitle(), rootNode.getLevel(), rootNode.getId());
        categoryModels.add(rootModel);
        convertPost(postModels, categoryPostModels, categoryDto.getId(), rootNode);
        convertHash(hashtagModels, categoryHashtagModels, rootNode.getHashtags(), categoryDto.getId(), rootNode.getId());

        for(CategoryNodeDto secondNode : rootNode.getLowLayer()) {
            CategoryModel secondModel = new CategoryModel(secondNode.getId(), categoryDto.getId(), secondNode.getTitle(), secondNode.getLevel(), rootNode.getId());
            categoryModels.add(secondModel);
            convertPost(postModels, categoryPostModels, categoryDto.getId(), secondNode);
            convertHash(hashtagModels, categoryHashtagModels, secondNode.getHashtags(), categoryDto.getId(), secondNode.getId());

            for(CategoryNodeDto thirdNode : secondNode.getLowLayer()) {
                CategoryModel thirdModel = new CategoryModel(thirdNode.getId(), categoryDto.getId(), thirdNode.getTitle(), thirdNode.getLevel(), secondNode.getId());
                categoryModels.add(thirdModel);
                convertPost(postModels, categoryPostModels, categoryDto.getId(), thirdNode);
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

    public static CategoryDto convertFormToDogam(ModelForm form) {
        DogamModel dogamModel = form.getDogamModel();
        CategoryModel[] categoryModels = form.getCategoryModels();
        PostModel[] postModels = form.getPostModels();
        CategoryPostModel[] categoryPostModels = form.getCategoryPostModels();
        CategoryHashtagModel[] categoryHashtagModels = form.getCategoryHashtagModels();

        List<CategoryNodeDto> categoryNodeDtos = new ArrayList<>();
        for(int i = 0; i < categoryModels.length; i++) {
            CategoryNodeDto nodeDto = new CategoryNodeDto(categoryModels[i].getName(), null, categoryModels[i].getLevel());
            nodeDto.setId(categoryModels[i].getId());
            convertPost(nodeDto.getPosts(), postModels, categoryPostModels, nodeDto.getId());
            convertHash(nodeDto.getHashtags(), categoryHashtagModels, nodeDto.getId());
            categoryNodeDtos.add(nodeDto);
        }
        CategoryNodeDto rootNode = convertModelToNode(categoryModels, categoryNodeDtos);
        CategoryDto dogam = new CategoryDto(dogamModel.getTitle(), dogamModel.getDescription(), dogamModel.getPassword(), rootNode);
        dogam.setId(dogamModel.getId());
        dogam.setStatus(DogamStatus.valueOf(dogamModel.getStatus()));

        return dogam;
    }

    // convert PostDto -> postModel & categoryPostModel
    private static void convertPost(List<PostModel> postModels, List<CategoryPostModel> categoryPostModels, int dogamId, CategoryNodeDto categoryNodeDto) {
        for(PostDto postDto : categoryNodeDto.getPosts()) {
            postModels.add(new PostModel(postDto.getId(), postDto.getUrl(), postDto.getImgUrl(), postDto.getHashtag()));
            categoryPostModels.add(new CategoryPostModel(dogamId, categoryNodeDto.getId(), postDto.getId()));

        }
    }

    // convert postModel & categoryPostModel -> PostDto
    private static void convertPost(List<PostDto> postDtos, PostModel[] postModels, CategoryPostModel[] categoryPostModels, int nodeId) {
        for(PostModel postModel : postModels) {
            for(CategoryPostModel categoryPostModel : categoryPostModels) {
                if(categoryPostModel.getCategoryId() == nodeId && categoryPostModel.getPostId() == postModel.getId()) {
                    postDtos.add(new PostDto(postModel.getImgUrl(), postModel.getUrl(), postModel.getHashtag(), 0, nodeId, categoryPostModel.getDogamId()));
                }
            }
        }
    }

    // convert hashtags -> hashtagModel & categoryHashtagModel
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

    //convert CategoryHashtagModel -> hashtags
    private static void convertHash(List<String> hashtags, CategoryHashtagModel[] categoryHashtagModels, int nodeId) {
        for(CategoryHashtagModel categoryHashtagModel : categoryHashtagModels) {
            if(categoryHashtagModel.getCategoryId() == nodeId) {
                hashtags.add(categoryHashtagModel.getHashtag());
            }
        }
    }

    private static CategoryNodeDto convertModelToNode(CategoryModel[] categoryModels, List<CategoryNodeDto> categoryNodeDtos) {
        Map<Integer, Pair<Integer, CategoryNodeDto>> nodeSet = new LinkedHashMap<>();
        CategoryNodeDto root = null;
        for(int i = 0; i < categoryNodeDtos.size(); i++) {
            nodeSet.put(categoryNodeDtos.get(i).getId(), new Pair<>(categoryModels[i].getParentId(), categoryNodeDtos.get(i)));
        }

        for(Pair<Integer, CategoryNodeDto> p : nodeSet.values()) {
            if(p.second.getId() == p.first) {
                root = p.second;
                continue;
            }

            CategoryNodeDto parent = nodeSet.get(p.first).second;
            p.second.setParent(parent);

            if(parent.getId() != p.second.getId())
                parent.getLowLayer().add(p.second);
        }

        return root;
    }
}

package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ModelForm {
    @SerializedName("dogamListModel")
    private DogamModel dogamModel;
    @SerializedName("categoryModels")
    private CategoryModel[] categoryModels;
    @SerializedName("postModels")
    private PostModel[] postModels;
    @SerializedName("hashtagModels")
    private HashtagModel[] hashtagModels;

    @SerializedName("categoryPostModels")
    private CategoryPostModel[] categoryPostModels;
    @SerializedName("categoryHashModels")
    private CategoryHashtagModel[] categoryHashtagModels;

    public DogamModel getDogamModel() {
        return dogamModel;
    }

    public void setDogamModel(DogamModel dogamModel) {
        this.dogamModel = dogamModel;
    }

    public CategoryModel[] getCategoryModels() {
        return categoryModels;
    }

    public void setCategoryModels(CategoryModel[] categoryModels) {
        this.categoryModels = categoryModels;
    }

    public PostModel[] getPostModels() {
        return postModels;
    }

    public void setPostModels(PostModel[] postModels) {
        this.postModels = postModels;
    }

    public HashtagModel[] getHashtagModels() {
        return hashtagModels;
    }

    public void setHashtagModels(HashtagModel[] hashtagModels) {
        this.hashtagModels = hashtagModels;
    }

    public CategoryPostModel[] getCategoryPostModels() {
        return categoryPostModels;
    }

    public void setCategoryPostModels(CategoryPostModel[] categoryPostModels) {
        this.categoryPostModels = categoryPostModels;
    }

    public CategoryHashtagModel[] getCategoryHashtagModels() {
        return categoryHashtagModels;
    }

    public void setCategoryHashtagModels(CategoryHashtagModel[] categoryHashtagModels) {
        this.categoryHashtagModels = categoryHashtagModels;
    }

    @Override
    public String toString() {
        return "ModelForm{" +
                "dogamModel=" + dogamModel +
                ", categoryModels=" + Arrays.toString(categoryModels) +
                ", postModels=" + Arrays.toString(postModels) +
                ", hashtagModels=" + Arrays.toString(hashtagModels) +
                ", categoryPostModels=" + Arrays.toString(categoryPostModels) +
                ", categoryHashtagModels=" + Arrays.toString(categoryHashtagModels) +
                '}';
    }
}

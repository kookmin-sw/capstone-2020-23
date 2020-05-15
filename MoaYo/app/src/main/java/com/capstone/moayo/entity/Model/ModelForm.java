package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

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

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("dogamModel : ").append(dogamModel.toString()).append("\n");
        for(CategoryModel categoryModel : categoryModels)
            buffer.append("categoryModel : ").append(categoryModel.toString()).append("\n");
        for(PostModel postModel : postModels)
            buffer.append("postModel : ").append(postModel.toString()).append("\n");
        for(HashtagModel hashtagModel : hashtagModels)
            buffer.append("hashModel : ").append(hashtagModel.toString()).append("\n");
        for(CategoryPostModel categoryPostModel : categoryPostModels)
            buffer.append("categoryPostModel : ").append(categoryPostModel.toString()).append("\n");
        for(CategoryHashtagModel categoryHashtagModel : categoryHashtagModels)
            buffer.append("categoryHashtagModel : ").append(categoryHashtagModel.toString()).append("\n");

        return buffer.toString();
    }
}

package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.PostStorage;
import com.capstone.moayo.storage.ShareStorage;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.DogamStatus;
import com.capstone.moayo.util.ShareUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ConcreteShareService implements ShareService {
    private ShareStorage shareStorage;
    private DogamStorage dogamStorage;
    private PostStorage postStorage;

    public ConcreteShareService(Context context) {
        this.dogamStorage = StorageFactoryCreator.getInstance().requestDogamStorage(context);
        this.postStorage = StorageFactoryCreator.getInstance().requestPostStorage(context);
        this.shareStorage = StorageFactoryCreator.getInstance().requestShareStoraget(context);
    }

    @Override
    public String registerDogam(CategoryDto categoryDto, int status) {
        categoryDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        for(CategoryNodeDto secondNode : categoryDto.getRootNode().getLowLayer()) {
            List<Post> secondPosts = postStorage.retrievePostByNodeId(secondNode.getId());
            for(Post post : secondPosts) {
                post.setHashtag(post.getHashtag().trim());
                secondNode.getPosts().add(post.toPostDto());
            }
            for(CategoryNodeDto thirdNode : secondNode.getLowLayer()) {
                List<Post> thirdPosts = postStorage.retrievePostByNodeId(thirdNode.getId());
                for(Post post : thirdPosts) {
                    post.setHashtag(post.getHashtag().trim());
                    thirdNode.getPosts().add(post.toPostDto());
                }
            }
        }

        ModelForm form = ShareUtil.convertDogamToModelForm(categoryDto, status);
        Log.d("convert category to form", form.toString());
        int result = shareStorage.create(form);
        categoryDto.setStatus(DogamStatus.Sharing);
        Category category = categoryDto.toCategory();
        dogamStorage.update(category);
        return Integer.toString(result);
    }

    @Override
    public CategoryDto findDogamById(int dogamId) {
        ModelForm foundForm = shareStorage.retrieveById(dogamId);
        CategoryDto foundCategory = ShareUtil.convertFormToDogam(foundForm);
        return foundCategory;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<DogamModel> dogamModels = shareStorage.retrieveAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        try {
            if(dogamModels == null) throw new Exception();
            for(DogamModel dogamModel : dogamModels) {
                String[] de_url = dogamModel.getDescription().split(";");
                CategoryDto categoryDto = new CategoryDto(dogamModel.getTitle(), de_url[0], dogamModel.getPassword(), null);
                categoryDto.setId(dogamModel.getId());
                categoryDtoList.add(categoryDto);
                if(de_url.length != 1)
                    categoryDto.setUrl(de_url[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryDtoList;
    }

    @Override
    public List<CategoryDto> findDogamByWriter(String writer) {
        return null;
    }

    @Override
    public List<CategoryDto> findDogamByKeyword(String keyword) {
        return null;
    }

    @Override
    public String deleteDogam(int dogamId) {
        int code = shareStorage.remove(dogamId);
        return Integer.toString(code);
    }

    @Override
    public List<CategoryDto> sortByLike(List<CategoryDto> categoryDtos) {
        categoryDtos.sort((o1, o2) -> {
            if(o1.getLike() > o2.getLike())
                return 1;
            else
                return -1;
        });

        return categoryDtos;
    }

    @Override
    public List<CategoryDto> sortByTime(List<CategoryDto> categoryDtos) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        categoryDtos.sort((o1, o2) -> {
            try {
                Date date1 = format.parse(o1.getTime());
                Date date2 = format.parse(o2.getTime());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return  0;
        });
        return categoryDtos;
    }
}

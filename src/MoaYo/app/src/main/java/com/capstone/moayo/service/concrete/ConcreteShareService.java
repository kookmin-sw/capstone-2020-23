package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.dao.mapping.DogamLikeMapping;
import com.capstone.moayo.dao.mapping.DogamMapping;
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
import com.capstone.moayo.storage.map.MemoryMap;
import com.capstone.moayo.util.DogamStatus;
import com.capstone.moayo.util.ShareUtil;
import com.capstone.moayo.util.retrofit.ShareResponse;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
        ShareResponse result = shareStorage.create(form);
        //update database
        categoryDto.setStatus(DogamStatus.Sharing);
        categoryDto.setSharedDogamId(result.getDogamId());
        Category category = categoryDto.toCategory();
        dogamStorage.update(category);

        return Integer.toString(result.getCode());
    }

    @Override
    public CategoryDto findDogamById(int dogamId) {
        CategoryDto foundCategory;
        ModelForm foundForm = shareStorage.retrieveById(dogamId);
        DogamLikeMapping mapping = shareStorage.retrieveLiked(dogamId);
        if(mapping != null)
            foundCategory = ShareUtil.convertFormToDogam(foundForm, mapping.isLiked());
        else
            foundCategory = ShareUtil.convertFormToDogam(foundForm,false);
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
                categoryDto.setWriter(dogamModel.getWriter());
                if(de_url.length != 1)
                    categoryDto.setUrl(de_url[1]);
                if(dogamModel.getStatus() == 0) categoryDto.setStatus(DogamStatus.Shared_Mutable);
                else categoryDto.setStatus(DogamStatus.Shared_Immutable);

                categoryDto.setLike(dogamModel.getLike());
                TimeZone.getTimeZone("Asia/Seoul");
                Timestamp ts = dogamModel.getDate();
//                ts.s
                if(ts != null)
                    categoryDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts));

                DogamLikeMapping mapping = shareStorage.retrieveLiked(dogamModel.getId());
                if(mapping != null) categoryDto.setLiked(mapping.isLiked());
                else categoryDto.setLiked(false);
                categoryDtoList.add(categoryDto);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryDtoList;
    }

    @Override
    public List<CategoryDto> findDogamByWriter(String writer) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        try {
            List<DogamModel> dogamModels = shareStorage.retrieveByWriter(writer);
            if(dogamModels.isEmpty()) throw new Exception();

            for(DogamModel dogamModel : dogamModels) {
                String[] de_url = dogamModel.getDescription().split(";");
                CategoryDto categoryDto = new CategoryDto(dogamModel.getTitle(), de_url[0], dogamModel.getPassword(), null);
                categoryDto.setWriter(dogamModel.getWriter());
                if(de_url.length != 1)
                    categoryDto.setUrl(de_url[1]);
                if(dogamModel.getStatus() == 0) categoryDto.setStatus(DogamStatus.Shared_Mutable);
                else categoryDto.setStatus(DogamStatus.Shared_Immutable);

                categoryDto.setLike(dogamModel.getLike());
                Timestamp ts = dogamModel.getDate();
                if(ts != null)
                    categoryDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts));

                DogamLikeMapping mapping = shareStorage.retrieveLiked(dogamModel.getId());
                if(mapping != null) categoryDto.setLiked(mapping.isLiked());
                else categoryDto.setLiked(false);

                categoryDtoList.add(categoryDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryDtoList;
    }

    @Override
    public List<CategoryDto> findDogamByKeyword(String keyword) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        try {
            List<DogamModel> dogamModels = shareStorage.retrieveByKeyword(keyword);
            if(dogamModels.isEmpty()) throw new Exception();

            for(DogamModel dogamModel : dogamModels) {
                String[] de_url = dogamModel.getDescription().split(";");
                CategoryDto categoryDto = new CategoryDto(dogamModel.getTitle(), de_url[0], dogamModel.getPassword(), null);
                categoryDto.setWriter(dogamModel.getWriter());
                if(de_url.length != 1)
                    categoryDto.setUrl(de_url[1]);
                if(dogamModel.getStatus() == 0) categoryDto.setStatus(DogamStatus.Shared_Mutable);
                else categoryDto.setStatus(DogamStatus.Shared_Immutable);

                categoryDto.setLike(dogamModel.getLike());
                Timestamp ts = dogamModel.getDate();
                if(ts != null)
                    categoryDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts));
                else
                    categoryDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                DogamLikeMapping mapping = shareStorage.retrieveLiked(dogamModel.getId());
                if(mapping != null) categoryDto.setLiked(mapping.isLiked());
                else categoryDto.setLiked(false);

                categoryDtoList.add(categoryDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryDtoList;
    }

    @Override
    public boolean findDogamLiked(int id) {
        DogamLikeMapping dogamLikeMapping = shareStorage.retrieveLiked(id);
        if(dogamLikeMapping == null) return false;
        return dogamLikeMapping.isLiked();
    }

    @Override
    public int updateLike(int dogamId, boolean isLiked) {
        int result = 0;
        if(isLiked) result = shareStorage.updateLike(dogamId);
        else result = shareStorage.updateDisLike(dogamId);
        if(result == 0) Log.d("success to update like", Integer.toString(result));
        else Log.e("fail to update like", Integer.toString(result));
        return result;
    }

    @Override
    public String deleteDogam(int dogamId, int sharedId) {
        int code = shareStorage.remove(sharedId);
        Category category = MemoryMap.getInstance().getCategoryMap().get(dogamId);
        assert category != null;
        category.setSharedDogamId(0);
        category.setStatus(DogamStatus.NonShare);
        dogamStorage.update(category);
        return Integer.toString(code);
    }

    @Override
    public List<CategoryDto> sortByLike(List<CategoryDto> categoryDtos) {
        categoryDtos.sort((o1, o2) -> {
            if(o1.getLike() > o2.getLike())
                return -1;
            else if (o1.getLike() < o2.getLike())
                return 1;
            else
                return 0;
        });

        return categoryDtos;
    }

    @Override
    public List<CategoryDto> sortByTime(List<CategoryDto> categoryDtos) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        categoryDtos.sort((o1, o2) -> {
            //                Date date1 = format.parse(o1.getTime());
//                Date date2 = format.parse(o2.getTime());
            if(o1.getTime() != null && o2.getTime() != null){
                String[] time = o1.getTime().split(" ");
                Date date1 = Timestamp.valueOf(time[0] + " " + time[1]);
                time = o2.getTime().split(" ");
                Date date2 = Timestamp.valueOf(time[0] + " " + time[1]);
                return date1.compareTo(date2)*-1;
            }else{
                if(o1.getTime() != null || o2.getTime() != null){
                    if(o1.getTime() != null){
                        return -1;
                    }else{
                        return 1;
                    }
                }else{
                    return 0;
                }
            }
        });
        return categoryDtos;
    }
}

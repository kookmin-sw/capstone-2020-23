package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.ShareStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.DogamStatus;
import com.capstone.moayo.util.ShareUtil;

import java.util.ArrayList;
import java.util.List;

public class ConcreteShareService implements ShareService {
    private ShareStorage shareStorage;
    private DogamStorage dogamStorage;

    public ConcreteShareService(Context context) {
        this.dogamStorage = StorageFactoryCreator.getInstance().requestDogamStorage(context);
        this.shareStorage = StorageFactoryCreator.getInstance().requestShareStoraget(context);
    }

    @Override
    public String registerDogam(CategoryDto categoryDto, int status) {
        ModelForm form = ShareUtil.convertDogamToModelForm(categoryDto, status);
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
                CategoryDto categoryDto = new CategoryDto(dogamModel.getTitle(), de_url[0], dogamModel.getPassword(), de_url[1], null);
                categoryDto.setId(dogamModel.getId());
                categoryDtoList.add(categoryDto);
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
}

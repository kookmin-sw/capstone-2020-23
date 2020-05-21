package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.ShareStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.DogamStatus;
import com.capstone.moayo.util.ShareUtil;

import java.util.List;

public class ConcreteShareService implements ShareService {
    private ShareStorage shareStorage;
    private DogamStorage dogamStorage;

    public ConcreteShareService(Context context) {
        this.dogamStorage = StorageFactoryCreator.getInstance().requestDogamStorage(context);
        this.shareStorage = StorageFactoryCreator.getInstance().requestShareStoraget(context);
    }

    @Override
    public void registerDogam(CategoryDto categoryDto, int status) {
        ModelForm form = ShareUtil.convertDogamToModelForm(categoryDto, status);
        shareStorage.create(form);
        categoryDto.setStatus(DogamStatus.Sharing);
        Category category = categoryDto.toCategory();
        dogamStorage.update(category);
    }

    @Override
    public CategoryDto loadDogamFromServer(int dogamId) {
        ModelForm foundForm = shareStorage.retrieveById(dogamId);
        CategoryDto foundCategory = ShareUtil.convertFormToDogam(foundForm);
        return foundCategory;
    }

    @Override
    public List<CategoryDto> loadAllFromServer() {
        return null;
    }

    @Override
    public void deleteDogam(int dogamId) {

    }
}

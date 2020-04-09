package com.capstone.moayo.service.concrete;

import com.capstone.moayo.entity.Content;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.storage.DataBindingStorage;

import java.util.List;

public class ConcreteDataBindingService implements DataBindingService {
    private DataBindingStorage dataBindingStorage;

    public ConcreteDataBindingService() {

    }

    @Override
    public List<Content> requestData(List<String> data) {
        return null;
    }
}

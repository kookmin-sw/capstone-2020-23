package com.capstone.moayo.storage.map;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryMap {
    private static volatile MemoryMap instance;
    private Map<Integer, Category> categoryMap;
    private Map<Integer, CategoryNode> categoryNodeMap;

    public static synchronized MemoryMap getInstance() {
        if(instance == null) {
            synchronized (MemoryMap.class) {
                if(instance == null) {
                    instance = new MemoryMap();
                }
            }
        }

        return instance;
    }

    public Map<Integer, Category> getCategoryMap() {
        if(categoryMap == null)
            categoryMap = new LinkedHashMap<>();
        return categoryMap;
    }

    public Map<Integer, CategoryNode> getCategoryNodeMap() {
        if(categoryNodeMap == null)
            categoryNodeMap = new LinkedHashMap<>();
        return categoryNodeMap;
    }

}

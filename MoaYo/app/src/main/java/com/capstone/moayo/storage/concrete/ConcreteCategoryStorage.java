package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;


import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.CategoryHashtagDao;
import com.capstone.moayo.dao.HashtagDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.CategoryHashTagMapping;
import com.capstone.moayo.dao.mapping.HashTagMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.map.MemoryMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ConcreteCategoryStorage implements CategoryStorage {
    private CategoryDao categoryDao;
    private HashtagDao hashtagDao;
    private CategoryHashtagDao categoryHashtagDao;
    private DBHelper dbHelper;

    private Map<Integer, Category> categoryMap;

    public ConcreteCategoryStorage(Context context) {
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
        categoryDao = DaoFactoryCreator.getInstance().requestCategoryDao();
        hashtagDao = DaoFactoryCreator.getInstance().requestHashtagDao();
        categoryHashtagDao = DaoFactoryCreator.getInstance().requestCategoryHashtagDao();

        categoryMap = MemoryMap.getInstance().getCategoryMap();
    }

    @Override
    public String create(Category category) {
        int dogamId = category.getId();
        CategoryNode rootNode = category.getRootNode();
        createHashTag(rootNode.getHashtags());
        int rootId = (int) categoryDao.rootInsert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), dogamId, dogamId);
        createCategoryHashTag(dogamId, rootId, rootNode.getHashtags());
        rootNode.setId(rootId);
        rootNode.setParent(rootNode);
        for (CategoryNode secondNode : rootNode.getLowLayer()) {
            createHashTag(secondNode.getHashtags());
            int secondId = (int) categoryDao.insert(dbHelper, secondNode.getLevel(), rootId, secondNode.getTitle(), dogamId, dogamId);
            createCategoryHashTag(dogamId, secondId, secondNode.getHashtags());
            secondNode.setId(secondId);
            secondNode.setParent(rootNode);
            for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                createHashTag(thirdNode.getHashtags());
                int thirdId = (int) categoryDao.insert(dbHelper, thirdNode.getLevel(), secondId, thirdNode.getTitle(), dogamId, dogamId);
                createCategoryHashTag(dogamId, thirdId, thirdNode.getHashtags());
                thirdNode.setId(thirdId);
                thirdNode.setParent(secondNode);
            }
        }
        categoryMap.get(dogamId).setRootNode(rootNode);
        Log.d("create category", categoryMap.get(dogamId).toString());
        return "create category" + dogamId;
    }

    @Override
    public CategoryNode retrieveById(int id) {
//        Iterator<CategoryNode> iterator = categoryNodeMap.values().iterator();
//        while(iterator.hasNext()) {
//            CategoryNode rootNode = iterator.next();
//            if(rootNode.getId() == id) return rootNode;
//            else {
//                for (CategoryNode secondNode : rootNode.getLowLayer()) {
//                    if (secondNode.getId() == id) return secondNode;
//                    else {
//                        for (CategoryNode thirdNode : secondNode.getLowLayer()) {
//                            if (thirdNode.getId() == id) return thirdNode;
//                        }
//                    }
//                }
//            }
//        }

        return null;
    }

    @Override
    public CategoryNode retrieveByDogamId(int id) {
//        if(!categoryMap.containsKey(id)) {
        // find root node
        CategoryNode rootNode = categoryDao.selectByDogamId(dbHelper, id);
        if (rootNode == null) return null;

        // find hash tags
        for (CategoryNode secondNode : rootNode.getLowLayer()) {
            List<CategoryHashTagMapping> mappings = categoryHashtagDao.selectByCategoryId(dbHelper, secondNode.getId());
            for (int i = 0; i < mappings.size(); i++) {
                secondNode.getHashtags().add(mappings.get(i).getHashtag());
            }
            for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                List<CategoryHashTagMapping> mappings1 = categoryHashtagDao.selectByCategoryId(dbHelper, thirdNode.getId());
                for (int j = 0; j < mappings1.size(); j++)
                    thirdNode.getHashtags().add(mappings1.get(j).getHashtag());
            }
        }
        categoryMap.get(id).setRootNode(rootNode);
        return rootNode;
//        } else {
//            return categoryMap.get(id).getRootNode();
    }

    @Override
    public String update(Category category) {
        int dogamId = category.getId();
        CategoryNode rootNode = category.getRootNode();

        boolean result = categoryDao.rootUpdate(dbHelper, rootNode.getId(), rootNode.getLevel(), rootNode.getId(), rootNode.getTitle(), dogamId, dogamId);
        if(result != true) return "fail to update";
        createHashTag(rootNode.getHashtags());
        updateCategoryHashTag(dogamId, rootNode.getId(), rootNode.getHashtags());

        for(CategoryNode secondNode : rootNode.getLowLayer()) {
            result = categoryDao.update(dbHelper, secondNode.getId(), secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), dogamId, dogamId);
            if(result != true) return "fail to update";
            createHashTag(secondNode.getHashtags());
            updateCategoryHashTag(dogamId, secondNode.getId(), secondNode.getHashtags());

            for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                result = categoryDao.update(dbHelper, thirdNode.getId(), thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), dogamId, dogamId);
                if(result != true) return "fail to update";
                createHashTag(thirdNode.getHashtags());
                updateCategoryHashTag(dogamId, thirdNode.getId(), thirdNode.getHashtags());
            }
        }
        return "success to update";
    }

    @Override
    public String remove(int id) {
        String result = "";
        boolean re = categoryDao.delete(dbHelper, id);
        if(re != true) result = "fail to delete node";
        else result = "success to delete node";
        return result;
    }

    private void createHashTag(List<String> hashtags) {
        for(String hashtag : hashtags) {
            boolean exist = hashtagDao.isExist(dbHelper, hashtag);
            if(exist == true) continue;
            int result = (int) hashtagDao.insert(dbHelper, new HashTagMapping(hashtag));
        }
    }

    private void createCategoryHashTag(int dogamId, int categoryId, List<String> hashtags) {
        for(String hashtag : hashtags) {
            categoryHashtagDao.insert(dbHelper, new CategoryHashTagMapping(dogamId, categoryId, hashtag));
        }
    }

    private void updateCategoryHashTag(int dogamId, int nodeId, List<String> hashtags) {
        List<CategoryHashTagMapping> mappingList = categoryHashtagDao.selectByCategoryId(dbHelper, nodeId);

        for(CategoryHashTagMapping mapping : mappingList) {
            if(!hashtags.contains(mapping.getHashtag())) {
                categoryHashtagDao.delete(dbHelper, mapping);
            }
        }
        for(String hashtag : hashtags)
            categoryHashtagDao.replace(dbHelper, new CategoryHashTagMapping(dogamId, nodeId, hashtag));
    }
}

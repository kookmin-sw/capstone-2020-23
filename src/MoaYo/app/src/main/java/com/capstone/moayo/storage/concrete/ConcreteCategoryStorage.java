package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;


import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.CategoryHashtagDao;
import com.capstone.moayo.dao.HashtagDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.CategoryHashTagMapping;
import com.capstone.moayo.dao.mapping.CategoryMapping;
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
        return "create category" + dogamId;
    }

    @Override
    public CategoryNode retrieveById(int dogamId, int nodeId) {
//        if(!categoryMap.containsKey(dogamId)) {
            CategoryMapping categoryMapping = categoryDao.selectById(dbHelper, nodeId);
            CategoryNode foundNode = new CategoryNode(categoryMapping.getTitle(), null, categoryMapping.getLevel());
            foundNode.setId(categoryMapping.getId());
            return foundNode;
//        } else {
//            CategoryNode rootNode = categoryMap.get(dogamId).getRootNode();
//            Iterator<CategoryNode> secondIterator = rootNode.getLowLayer().iterator();
//            while (secondIterator.hasNext()) {
//                CategoryNode secondNode = secondIterator.next();
//                if (secondNode.getId() == nodeId) return secondNode;
//                Iterator<CategoryNode> thirdIterator = secondNode.getLowLayer().iterator();
//                while (thirdIterator.hasNext()) {
//                    CategoryNode thirdNode = thirdIterator.next();
//                    if (thirdNode.getId() == nodeId) return thirdNode;
//                }
//            }
//        }
//        return null;
    }

    @Override
    public CategoryNode retrieveByDogamId(int id) {
//        if(!categoryMap.containsKey(id) || categoryMap.get(id).getRootNode() == null) {
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
//            categoryMap.get(id).setRootNode(rootNode);
//        }
        return rootNode;
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

        categoryMap.get(dogamId).setRootNode(rootNode);
        return "success to update";
    }

    @Override
    public String remove(int dogamId, int id) {
        String result = "";
        boolean re = categoryDao.delete(dbHelper, id);
        if(re != true)
            result = "fail to delete node";
         else  {
             result = "success to delete node";
             CategoryNode rootNode = categoryMap.get(dogamId).getRootNode();
             if(rootNode.getId() == id) {
                 categoryMap.get(dogamId).setRootNode(null);
                 return result;
             }
             for(CategoryNode secondNode : rootNode.getLowLayer()) {
                 if(secondNode.getId() == id) {
                     rootNode.getLowLayer().remove(secondNode);
                     return result;
                 }
                 for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                     if(thirdNode.getId() == id) {
                         secondNode.getLowLayer().remove(thirdNode);
                         return result;
                     }
                 }
             }

        }
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

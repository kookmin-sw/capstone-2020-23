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
    private Map<Integer, CategoryNode> categoryNodeMap;

    public ConcreteCategoryStorage(Context context) {
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
        categoryDao = DaoFactoryCreator.getInstance().requestCategoryDao();
        hashtagDao = DaoFactoryCreator.getInstance().requestHashtagDao();
        categoryHashtagDao = DaoFactoryCreator.getInstance().requestCategoryHashtagDao();

        categoryMap = MemoryMap.getInstance().getCategoryMap();
        categoryNodeMap = MemoryMap.getInstance().getCategoryNodeMap();
    }

    @Override
    public String create(Category category) {
        int dogamId = category.getId();

        CategoryNode rootNode = category.getRootNode();
        createHashTag(rootNode.getHashtags());
        //insert rootNode
        int rootId = (int) categoryDao.rootInsert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), dogamId, dogamId);
        createCategoryHashTag(dogamId, rootId, rootNode.getHashtags());
        rootNode.setId(rootId);
        categoryNodeMap.put(rootId, rootNode);

        for (CategoryNode secondNode : rootNode.getLowLayer()) {
            createHashTag(secondNode.getHashtags());
            //insert secondNode
            int secondId = (int) categoryDao.insert(dbHelper, secondNode.getLevel(), rootId, secondNode.getTitle(), dogamId, dogamId);
            createCategoryHashTag(dogamId, secondId, secondNode.getHashtags());
            secondNode.setId(secondId);
            secondNode.setParent(rootNode);
            categoryNodeMap.put(secondId, secondNode);

            for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                createHashTag(thirdNode.getHashtags());

                //insert thirdNode
                int thirdId = (int) categoryDao.insert(dbHelper, thirdNode.getLevel(), secondId, thirdNode.getTitle(), dogamId, dogamId);
                createCategoryHashTag(dogamId, thirdId, thirdNode.getHashtags());
                thirdNode.setId(thirdId);
                thirdNode.setParent(secondNode);
                categoryNodeMap.put(thirdId, thirdNode);
            }
        }
        categoryMap.get(dogamId).setRootNode(rootNode);
        return "create category" + dogamId;
    }

    @Override
    public CategoryNode retrieveById(int dogamId, int nodeId) {
        CategoryNode foundNode;

        //Map에 노드가 존재한다면?
        if(!categoryNodeMap.containsKey(nodeId)) {
            CategoryMapping categoryMapping = categoryDao.selectById(dbHelper, nodeId);
            foundNode = new CategoryNode(categoryMapping.getTitle(), null, categoryMapping.getLevel());
            foundNode.setId(categoryMapping.getId());
            return foundNode;
        } else {
            foundNode = categoryNodeMap.get(nodeId);
        }
        Log.d("storage found result", foundNode.toString());
        return foundNode;
    }

    @Override
    public CategoryNode retrieveByDogamId(int id) {
        if(categoryMap.get(id).getRootNode() == null) {
            // find root node
            CategoryNode rootNode = categoryDao.selectByDogamId(dbHelper, id);
            if (rootNode == null) return null;
            categoryNodeMap.put(rootNode.getId(), rootNode);
            // find hash tags
            for (CategoryNode secondNode : rootNode.getLowLayer()) {
                List<CategoryHashTagMapping> mappings = categoryHashtagDao.selectByCategoryId(dbHelper, secondNode.getId());
                for (int i = 0; i < mappings.size(); i++) {
                    secondNode.getHashtags().add(mappings.get(i).getHashtag());
                }
                categoryNodeMap.put(secondNode.getId(), secondNode);
                for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                    List<CategoryHashTagMapping> mappings1 = categoryHashtagDao.selectByCategoryId(dbHelper, thirdNode.getId());
                    for (int j = 0; j < mappings1.size(); j++)
                        thirdNode.getHashtags().add(mappings1.get(j).getHashtag());
                    categoryNodeMap.put(thirdNode.getId(), thirdNode);
                }
            }
            categoryMap.get(id).setRootNode(rootNode);
        }
        return categoryMap.get(id).getRootNode();
    }

    @Override
    public String update(Category category) {
        int dogamId = category.getId();
        CategoryNode rootNode = category.getRootNode();

        //update root Node
        if(rootNode.getId() == 0) {
            int rootId = (int) categoryDao.rootInsert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), dogamId, dogamId);
            rootNode.setId(rootId);
        } else {
            boolean result = categoryDao.rootUpdate(dbHelper, rootNode.getId(), rootNode.getLevel(), rootNode.getId(), rootNode.getTitle(), dogamId, dogamId);
            if (result != true) return "fail to update in " + rootNode.toString();
        }

        //update hashtag
        createHashTag(rootNode.getHashtags());
        updateCategoryHashTag(dogamId, rootNode.getId(), rootNode.getHashtags());
        categoryNodeMap.put(rootNode.getId(), rootNode);

        //update second node
        for(CategoryNode secondNode : rootNode.getLowLayer()) {
            if(secondNode.getId() == 0) {
                int secondId = (int) categoryDao.insert(dbHelper, secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), dogamId, dogamId);
                secondNode.setId(secondId);
            } else {
                boolean result = categoryDao.update(dbHelper, secondNode.getId(), secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), dogamId, dogamId);
                if(result != true) return "fail to update in " + secondNode.toString();
            }

            //update hashtag
            createHashTag(secondNode.getHashtags());
            updateCategoryHashTag(dogamId, secondNode.getId(), secondNode.getHashtags());
            categoryNodeMap.put(secondNode.getId(), secondNode);

            //update thirdnode
            for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                if(thirdNode.getId() == 0) {
                    int thirdId = (int) categoryDao.insert(dbHelper, thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), dogamId, dogamId);
                    thirdNode.setId(thirdId);
                } else {
                    boolean result = categoryDao.update(dbHelper, thirdNode.getId(), thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), dogamId, dogamId);
                    if(result != true) return "fail to update in " + thirdNode.toString();
                }

                //update hashtag
                createHashTag(thirdNode.getHashtags());
                updateCategoryHashTag(dogamId, thirdNode.getId(), thirdNode.getHashtags());
                categoryNodeMap.put(thirdNode.getId(), thirdNode);
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

            CategoryNode removeNode = categoryNodeMap.get(id);
            if(removeNode.getParent() == null) {
                categoryNodeMap.remove(id);
                categoryMap.get(dogamId).setRootNode(null);
            } else {
                CategoryNode removeParentNode = removeNode.getParent();

                //부모 리스트에서 삭제
                removeParentNode.getLowLayer().remove(removeNode);
                categoryNodeMap.put(removeParentNode.getId(), removeParentNode);

                //자식 리스트 노드 삭제
                for(CategoryNode node : removeNode.getLowLayer()) {
                    categoryNodeMap.remove(node.getId());
                }

                //노드 삭제
                categoryNodeMap.remove(id);
            }


        }
        return result;
    }

    @Override
    public void init() {
        for(Category category : categoryMap.values()) {
            CategoryNode rootNode = categoryDao.selectByDogamId(dbHelper, category.getId());
            if (rootNode == null) continue;

            categoryNodeMap.put(rootNode.getId(), rootNode);
            // find hash tags
            for (CategoryNode secondNode : rootNode.getLowLayer()) {
                List<CategoryHashTagMapping> mappings = categoryHashtagDao.selectByCategoryId(dbHelper, secondNode.getId());
                for (int i = 0; i < mappings.size(); i++) {
                    secondNode.getHashtags().add(mappings.get(i).getHashtag());
                }
                categoryNodeMap.put(secondNode.getId(), secondNode);

                for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                    List<CategoryHashTagMapping> mappings1 = categoryHashtagDao.selectByCategoryId(dbHelper, thirdNode.getId());
                    for (int j = 0; j < mappings1.size(); j++)
                        thirdNode.getHashtags().add(mappings1.get(j).getHashtag());
                    categoryNodeMap.put(thirdNode.getId(), thirdNode);
                }
            }
            categoryMap.get(category.getId()).setRootNode(rootNode);
        }
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

    private void updateHashTag(int dogamId, int nodeId, List<String> hashtags) {

    }


}

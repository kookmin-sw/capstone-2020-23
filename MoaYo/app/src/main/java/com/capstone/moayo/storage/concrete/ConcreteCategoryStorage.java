package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.os.AsyncTask;

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

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcreteCategoryStorage implements CategoryStorage {
    private CategoryDao categoryDao;
    private HashtagDao hashtagDao;
    private CategoryHashtagDao categoryHashtagDao;
    private DBHelper dbHelper;

    public ConcreteCategoryStorage(Context context) {
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
        categoryDao = DaoFactoryCreator.getInstance().requestCategoryDao();
        hashtagDao = DaoFactoryCreator.getInstance().requestHashtagDao();
        categoryHashtagDao = DaoFactoryCreator.getInstance().requestCategoryHashtagDao();
    }

    @Override
    public String create(Category category) {
        AsyncTask<Category, Void, String> thread = new AsyncTask<Category, Void, String>() {
            @Override
            protected String doInBackground(Category... categories) {
                Category selectCategory = categories[0];
                int dogamId = selectCategory.getId();
                CategoryNode rootNode = selectCategory.getRootNode();
                createHashTag(rootNode.getHashtags());
                int rootId = (int) categoryDao.rootInsert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), dogamId , dogamId);
                createCategoryHashTag(dogamId, rootId, rootNode.getHashtags());
                for(CategoryNode secondNode : rootNode.getLowLayer()) {
                    createHashTag(secondNode.getHashtags());
                    int secondId = (int) categoryDao.insert(dbHelper, secondNode.getLevel(), rootId, secondNode.getTitle(), dogamId, dogamId);
                    createCategoryHashTag(dogamId, secondId, secondNode.getHashtags());
                    for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                        createHashTag(thirdNode.getHashtags());
                        int thirdId = (int) categoryDao.insert(dbHelper, thirdNode.getLevel(), secondId, thirdNode.getTitle(), dogamId, dogamId);
                        createCategoryHashTag(dogamId, thirdId, thirdNode.getHashtags());
                    }
                }
                return "success to create category";
            }
        };
        try {
            String result = thread.execute(category).get(1, TimeUnit.SECONDS);
            return result;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return "fail to create";
    }

    @Override
    public CategoryNode retrieveByTitle(String title) {
        return null;
    }

    @Override
    public CategoryNode retrieveById(int id) {
        AsyncTask<Integer, Void, CategoryNode> thread = new AsyncTask<Integer, Void, CategoryNode>() {
            @Override
            protected CategoryNode doInBackground(Integer... integers) {
                int dogamId = integers[0];
                // find root node
                CategoryNode rootNode = categoryDao.selectByDogamId(dbHelper, dogamId);
                if(rootNode == null) return null;

                // find hash tags
                for(CategoryNode secondNode : rootNode.getLowLayer()) {
                    List<CategoryHashTagMapping> mappings = categoryHashtagDao.selectByCategoryId(dbHelper, secondNode.getId());
                    for(int i = 0; i < mappings.size(); i++)
                        secondNode.getHashtags().add(mappings.get(i).getHashtag());
                    for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                        List<CategoryHashTagMapping> mappings1 = categoryHashtagDao.selectByCategoryId(dbHelper, thirdNode.getId());
                        for(int j = 0; j < mappings1.size(); j++)
                            thirdNode.getHashtags().add(mappings1.get(j).getHashtag());
                    }
                }
                return rootNode;
            }
        };

        try {
            CategoryNode rootNode = thread.execute(id).get(1, TimeUnit.SECONDS);

            return rootNode;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String update(Category category) {
        AsyncTask<Category, Void, String> thread = new AsyncTask<Category, Void, String>() {
            @Override
            protected String doInBackground(Category... categories) {
                int dogamId = categories[0].getId();
                CategoryNode rootNode = categories[0].getRootNode();
                boolean result = categoryDao.rootUpdate(dbHelper, rootNode.getId(), rootNode.getLevel(), 0, rootNode.getTitle(), dogamId, dogamId);
                if(result != true) return "fail to update";
                updateCategoryHashTag(dogamId, rootNode.getId(), rootNode.getHashtags());
                for(CategoryNode secondNode : rootNode.getLowLayer()) {
                    result = categoryDao.update(dbHelper, secondNode.getId(), secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), dogamId, dogamId);
                    if(result != true) return "fail to update";
                    updateCategoryHashTag(dogamId, secondNode.getId(), secondNode.getHashtags());
                    for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                        result = categoryDao.update(dbHelper, thirdNode.getId(), thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), dogamId, dogamId);
                        if(result != true) return "fail to update";
                        updateCategoryHashTag(dogamId, thirdNode.getId(), thirdNode.getHashtags());
                    }
                }
                return "success to update";
            }
        };

        try {
            String result = thread.execute(category).get(3, TimeUnit.SECONDS);
            return result;
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
        return "fail to update";
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

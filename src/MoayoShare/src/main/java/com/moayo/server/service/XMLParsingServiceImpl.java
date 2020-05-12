package com.moayo.server.service;


import com.moayo.server.dao.CategoryDao;
import com.moayo.server.dao.DogamListDao;
import com.moayo.server.model.CategoryModel;
import com.moayo.server.model.DogamListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

@Service
public class XMLParsingServiceImpl implements XMLParsingService{

    @Autowired
    DogamListDao dogamListDao;
    @Autowired
    CategoryDao categoryDao;
    // hash tag add plz.
    public void insertData(Document document){
        DogamListModel dogamListModel = new DogamListModel(
                document.getElementsByTagName("title").item(0).getTextContent(),
                document.getElementsByTagName("description").item(0).getTextContent(),
                Integer.valueOf(document.getElementsByTagName("status").item(0).getTextContent()),
                document.getElementsByTagName("password").item(0).getTextContent());

        dogamListDao.insertDogam(dogamListModel);

        NodeList categories = document.getElementsByTagName("category");
        Map<Integer, CategoryModel> categoryMap = new HashMap<Integer,CategoryModel>();
        for(int i = 0;i<categories.getLength();i++){
            Element category = (Element) categories.item(i);
            int parentId = Integer.valueOf(category.getElementsByTagName("categoryParentId").item(0).getTextContent());
            int id = Integer.valueOf(category.getAttribute("id"));
            if(id == parentId){
                CategoryModel model = new CategoryModel(
                        dogamListModel.getCo_dogamId(),
                        category.getElementsByTagName("categoryName").item(0).getTextContent(),
                        Integer.valueOf(category.getElementsByTagName("categoryLevel").item(0).getTextContent()),
                        0
                );
                categoryDao.foreignKeyOFF();
                categoryDao.rootInsert(model);
                categoryDao.foreignKeyON();
                model.setCo_parentCategoryId(model.getCo_categoryId());
                categoryDao.updateCategory(model);
                categoryMap.put(id,model);
            }else {
                CategoryModel model = new CategoryModel(
                        dogamListModel.getCo_dogamId(),
                        category.getElementsByTagName("categoryName").item(0).getTextContent(),
                        Integer.valueOf(category.getElementsByTagName("categoryLevel").item(0).getTextContent()),
                        categoryMap.get(parentId).getCo_categoryId()
                );
                categoryDao.insertCategory(model);
                categoryMap.put(id,model);
            }
        }
    }
}

package com.moayo.server.controller;


import com.moayo.server.dao.*;
import com.moayo.server.model.CategoryModel;
import com.moayo.server.model.DogamListModel;
import com.moayo.server.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    ShareService service;
    @Autowired
    HashtagDao hashtagDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryHashDao categoryHashDao;
    @Autowired
    CategoryPostDao categoryPostDao;
    @Autowired
    DogamListDao dogamListDao;
    @Autowired
    PostDao postDao;
//
//    @RequestMapping(value="/bookshare",method = RequestMethod.POST)
//    public void bookShare(HttpServletRequest req ,HttpServletResponse res,@RequestBody String body){
//        JSONObject jsonObject = service.jsonParser(body);
//
//        JSONArray jsonArray = (JSONArray)jsonObject.get("book");
//        DogamListModel newModel = service.createBookModel(jsonObject);
//        // json create
//        // .json url input newModel.setUrl();
//        service.uploadBook(newModel);
//    }
//
//    @RequestMapping(value="/getbook",method = RequestMethod.POST)
//    public void getBook(HttpServletResponse res,HttpServletRequest req,@RequestBody String body){
//        // JSONObject jsonObject = service.jsonParser(body);
//        service.loadBook();
//    }

    @RequestMapping(value = "/daoTest",method = RequestMethod.GET)
    public void dbTest(HttpServletRequest req,HttpServletResponse res){
//        DogamListModel m1 = new DogamListModel("NewDogam2","this is new dogam2",1,"1234");
//        DogamListModel m2 = new DogamListModel("NewDogam3","this is new dogam3",2,"12344");
//        dogamListDao.insertDogam(m1);
//        dogamListDao.insertDogam(m2);

        List<DogamListModel> dogams = dogamListDao.getAllDogam();
        for(DogamListModel m : dogams){
            System.out.println(m.toString());
            if(m.getCo_title().equals("NewDogam3")){
                m.setCo_description("Modify New Dogam");
                dogamListDao.updateDogam(m);
            }
        }

//        dogamListDao.deleteDogamById(m2.getCo_dogamId());
//        dogamListDao.deleteDogamByModel(m1);

        CategoryModel cm1 = new CategoryModel(dogams.get(1).getCo_dogamId(),"음식",1);
        categoryDao.foreignKeyOFF();
        categoryDao.rootInsert(cm1);
        categoryDao.foreignKeyON();
        cm1.setCo_parentCategoryId(cm1.getCo_categoryId());
        categoryDao.updateCategory(cm1);

        CategoryModel cm2 = new CategoryModel(dogams.get(1).getCo_dogamId(),"중화요리",2, cm1.getCo_categoryId());
        categoryDao.insertCategory(cm2);
        CategoryModel cm3 = new CategoryModel(dogams.get(1).getCo_dogamId(),"유산슬",3, cm2.getCo_categoryId());
        categoryDao.insertCategory(cm3);

        List<CategoryModel> categories = categoryDao.getCategoryByDogamId(dogams.get(1).getCo_dogamId());
        categoryDao.deleteCategoryById(categories.get(2).getCo_categoryId(),categories.get(2).getCo_dogamId());
        categoryDao.deleteCategoryByDogamId(categories.get(0).getCo_dogamId());

//        hashtagDao.insertHashtag(new HashtagModel("요리"));
//
//        List<HashtagModel> allHash = hashtagDao.getAllHashtag();
//        for(HashtagModel m : allHash){
//            System.out.println(m.toString());
//        }
//
//        hashtagDao.deleteHashtagByString("요리");
    }
}

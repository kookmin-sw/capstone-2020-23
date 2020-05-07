package com.moayo.server.controller;


import com.moayo.server.model.DogamListModel;
import com.moayo.server.service.ShareService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    @Autowired
    ShareService service;

    @RequestMapping(value="/bookshare",method = RequestMethod.POST)
    public void bookShare(HttpServletRequest req ,HttpServletResponse res,@RequestBody String body){
        JSONObject jsonObject = service.jsonParser(body);

        JSONArray jsonArray = (JSONArray)jsonObject.get("book");
        DogamListModel newModel = service.createBookModel(jsonObject);
        // json create
        // .json url input newModel.setUrl();
        service.uploadBook(newModel);
    }

    @RequestMapping(value="/getbook",method = RequestMethod.POST)
    public void getBook(HttpServletResponse res,HttpServletRequest req,@RequestBody String body){
        // JSONObject jsonObject = service.jsonParser(body);
        service.loadBook();
    }
}

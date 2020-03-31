package com.moayo.server.controller;


import com.moayo.server.Entity.Book;
import com.moayo.server.service.ShareService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
public class MainController {
    @Autowired
    ShareService service;

    @RequestMapping(value="/bookshare",method = RequestMethod.POST)
    public void bookShare(HttpServletRequest req ,HttpServletResponse res,@RequestBody String body){
        JSONObject jsonObject = service.jsonParser(body);

//        Document doc = service.xmlParser(body);
//        NodeList bookElement = doc.getElementsByTagName("book");
//
//        Node n = bookElement.item(1);
//        System.out.println(n.getTextContent());

        JSONArray jsonArray = (JSONArray)jsonObject.get("book");
        Book book = new Book((String)jsonObject.get("tag"),(String)jsonObject.get("password"),jsonArray);
        try{
            service.uploadBook(book);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/getbook",method = RequestMethod.POST)
    public void getBook(HttpServletResponse res,HttpServletRequest req,@RequestBody String body){
        JSONObject jsonObject = service.jsonParser(body);
    }
}

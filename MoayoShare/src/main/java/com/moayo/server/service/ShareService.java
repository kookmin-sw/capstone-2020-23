package com.moayo.server.service;

import com.moayo.server.dao.DogamListDao;
import com.moayo.server.model.DogamListModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class ShareService {

    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;

    @Autowired
    private DogamListDao dao;
//
//    public JSONObject jsonParser(String body){
//        try{
//            JSONParser jsonparser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) jsonparser.parse(body);
//
//            return jsonObject;
//        }catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public DogamListModel createBookModel(JSONObject obj){
//        DogamListModel newBook = new DogamListModel();
//        newBook.setCo_writer((String)obj.get("writer"));
//        newBook.setCo_title((String)obj.get("title"));
//        newBook.setCo_tag((String)obj.get("tag"));
//        newBook.setCo_password((String)obj.get("password"));
//
//        return newBook;
//    }
//    public void uploadBook(DogamListModel model){
//        dao.insertBook(model);
//    }
//    public void loadBook(){
//        // load test
//        DogamListModel model = dao.getBook(1);}
}

package com.moayo.server.service;

import com.moayo.server.dao.BookDao;
import com.moayo.server.model.BookModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShareService {

    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;

    @Autowired
    private BookDao dao;

    public JSONObject jsonParser(String body){
        try{
            JSONParser jsonparser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonparser.parse(body);

            return jsonObject;
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookModel createBookModel(JSONObject obj){
        BookModel newBook = new BookModel();
        newBook.setCo_writer((String)obj.get("writer"));
        newBook.setCo_title((String)obj.get("title"));
        newBook.setCo_tag((String)obj.get("tag"));
        newBook.setCo_password((String)obj.get("password"));

        return newBook;
    }
    public void uploadBook(BookModel model){
        dao.insertBook(model);
    }
    public void loadBook(){
        // load test
        BookModel model = dao.getBook(1);}
}
